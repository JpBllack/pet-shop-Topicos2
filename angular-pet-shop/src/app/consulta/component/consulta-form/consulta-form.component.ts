import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Consulta } from '../../../models/consulta.model';
import { ConsultaService } from '../../../services/consulta.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { NgIf } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSelectModule } from '@angular/material/select';


@Component({
  selector: 'app-consulta-form',
  standalone : true,
  templateUrl: './consulta-form.component.html',
  imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,MatSelectModule,
    MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule],
  styleUrls: ['./consulta-form.component.css']
})
export class ConsultaFormComponent implements OnInit {

  formGroup: FormGroup;
  consulta: Consulta | undefined;

  constructor(
    private formBuilder: FormBuilder,
    private consultaService: ConsultaService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.formGroup = this.formBuilder.group({
      data: ['', Validators.required],
      motivo: ['', Validators.required],
      veterinarioId: ['', Validators.required],
      petId: ['', Validators.required]
    });
  }
  
  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(): void {
    this.formGroup = this.formBuilder.group({
      data: ['', Validators.required],
      motivo: ['', Validators.required],
      veterinarioId: ['', Validators.required],
      petId: ['', Validators.required]
    });

    const consultaIdString = this.activatedRoute.snapshot.paramMap.get('id');
    if (consultaIdString) {
      const consultaId = Number(consultaIdString);
      this.consultaService.buscarConsultaPorId(consultaId).subscribe(data => {
        this.consulta = data;
        this.formGroup.patchValue({
          data: data.data,
          motivo: data.motivo,
          veterinarioId: data.veterinario,
          petId: data.pet
        });
      });
    }
  }

  salvar(): void {
    if (this.formGroup.valid) {
      const consultaData = this.formGroup.value;
      if (this.consulta) {
        this.consultaService.atualizarConsulta(this.consulta.id, consultaData).subscribe(() => {
          this.router.navigate(['/consultas']);
        });
      } else {
        this.consultaService.criarConsulta(consultaData).subscribe(() => {
          this.router.navigate(['/consultas']);
        });
      }
    }
  }

  excluir(): void {
    if (this.consulta) {
      this.consultaService.excluirConsulta(this.consulta.id).subscribe(() => {
        this.router.navigate(['/consultas']);
      });
    }
  }
}
