import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule, NgIf,  Location } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput, MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSelectModule } from '@angular/material/select';
import { ReactiveFormsModule, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Usuario } from '../../../../models/Usuario';
import { Consulta } from '../../../../models/consulta.model';
import { ConsultaService } from '../../../../services/consulta.service';
import { UsuarioService } from '../../../../services/usuario.service';
import { MatDatepickerModule } from '@angular/material/datepicker';;
import { MatNativeDateModule } from '@angular/material/core';




@Component({
  selector: 'app-consulta-form',
  standalone: true,
  templateUrl: './consulta-form.component.html',
  imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
    MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule, CommonModule, MatInput, MatDatepickerModule, MatDatepickerModule,
    MatNativeDateModule],
  styleUrls: ['./consulta-form.component.css']
})
export class ConsultaFormComponent implements OnInit {
  picker: any;
  veterinarios: Usuario[] = [];
  formGroup: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private consultaService: ConsultaService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private usuarioService: UsuarioService,
    private location: Location
  ) {
    this.formGroup = this.formBuilder.group({
      id: [null],
      data: ['', Validators.required],
      motivo: ['', Validators.required],
      veterinario: ['', Validators.required],
      pet: ['null', Validators.required]
    });
  }

  
  voltarPagina() {
    this.location.back();
  }


  ngOnInit(): void {
    const consultaId = this.activatedRoute.snapshot.params['id'];
    this.carregarVeterinarios();
    
    if (consultaId) {
      this.consultaService.buscarConsultaPorId(consultaId).subscribe(
        (consulta) => {
          this.formGroup.patchValue({
            id: consulta.id,
            data: consulta.data,
            motivo: consulta.motivo,
            veterinario: consulta.veterinario.id,
            pet: consulta.pet.id
          });
        },
        (error) => {
          console.error('Erro ao buscar consulta:', error);
        }
      );
    }
  }


  salvar() {
    if (this.formGroup.valid) {
      const consulta = this.formGroup.value;
      if (consulta.id == null) {
        this.consultaService.criarConsulta(consulta).subscribe({
          next: () => {
            this.voltarPagina();
          },
          error: (err) => {
            console.log('Erro ao criar consulta' + JSON.stringify(err));
          }
        });
      } else {
        this.consultaService.atualizarConsulta(consulta.id, consulta).subscribe({
          next: () => {
            this.voltarPagina();
          },
          error: (err) => {
            console.log('Erro ao atualizar consulta' + JSON.stringify(err));
          }
        });
      }
    }
  }

  excluir() {
    if (this.formGroup.valid) {
      const consulta = this.formGroup.value;
      if (consulta.id != null) {
        this.consultaService.excluirConsulta(consulta.id).subscribe({
          next: () => {
            this.voltarPagina();
          },
          error: (err) => {
            console.log('Erro ao excluir' + JSON.stringify(err));
          }
        });
      }
    }
  }

  carregarVeterinarios() {
    this.usuarioService.getAllVeterinarios().subscribe(
      (vet: Usuario[]) => {
        this.veterinarios = vet;
      },
      (error) => {
        console.error('Erro ao carregar veterinários:', error);
      }
    );
  }


}
