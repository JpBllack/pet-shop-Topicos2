import { Component, OnInit } from '@angular/core';
import { Consulta } from '../../../models/consulta.model';
import { ConsultaService } from '../../../services/consulta.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule, NgIf } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSelectModule } from '@angular/material/select';
import { ReactiveFormsModule, FormGroup, FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-consulta-form',
  standalone : true,
  templateUrl: './consulta-form.component.html',
  imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
    MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule, CommonModule],
  styleUrls: ['./consulta-form.component.css']
})
export class ConsultaFormComponent implements OnInit {

  formGroup: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private consultaService: ConsultaService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.formGroup = this.formBuilder.group({
      id: [null],
      data: ['', Validators.required],
      motivo: ['', Validators.required],
      veterinario: ['null', Validators.required],
      pet: ['null', Validators.required]
    });
  }
  
  ngOnInit(): void {
    const consulta: Consulta = this.activatedRoute.snapshot.data['consulta'];
    if(consulta){
      this.formGroup.patchValue(consulta);
    }
  }


  salvar(){
    if(this.formGroup.valid){
      const consulta = this.formGroup.value;
      if(consulta.id == null){
        this.consultaService.criarConsulta(consulta).subscribe({
          next: () => {
            this.router.navigateByUrl('/consultas/all');
          },
          error: (err) => {
            console.log('Erro ao criar consulta' + JSON.stringify(err));
          }
        });
      } else{
          this.consultaService.atualizarConsulta(consulta.id, consulta).subscribe({
            next: () => {
              this.router.navigateByUrl('/consultas/all');
            },
            error: (err) => {
              console.log('Erro ao atualizar consulta' + JSON.stringify(err));
            }
          });
      }
    }
  }

  excluir(){
    if(this.formGroup.valid){
      const consulta = this.formGroup.value;
      if(consulta.id != null){
        this.consultaService.excluirConsulta(consulta.id).subscribe({
          next: () => {
            this.router.navigateByUrl('/consultas/all');
          },
          error: (err) => {
            console.log('Erro ap exvçior' + JSON.stringify(err));
          }
        });
      }
    }
  }
}
