import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { Location ,NgIf } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Estado } from '../../../../models/estado.model';
import { EstadoService } from '../../../../services/estado.service';



@Component({
  selector: 'app-estado-form',
  templateUrl: './estado-form.component.html',
  styleUrls: ['./estado-form.component.css'],
  imports: [NgIf, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule],
  standalone: true
})
export class EstadoFormComponent {

  formGroup: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private estadoService: EstadoService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private location: Location
  ) {
    const estado: Estado = activatedRoute.snapshot.data['estado'];

    this.formGroup = formBuilder.group({
      id: [estado?.id || null],
      nome: [estado?.nome || '', Validators.required],
      sigla: [estado?.sigla || '', Validators.required]
    });
  }


  voltarPagina() {
    this.location.back();
  }

  createEstado() {
    if (this.formGroup.valid) {
      const estado = this.formGroup.value;
      if (estado.id == null) {
        this.estadoService.createEstado(estado).subscribe({
          next: () => {
            this.voltarPagina();
          },
          error: (err) => {
            console.error('Erro ao incluir estado:', err);
          }
        });
      } else {
        this.estadoService.updateEstado(estado).subscribe({
          next: () => {
            this.voltarPagina();
          },
          error: (err) => {
            console.error('Erro ao editar estado:', err);
          }
        });
      }
    }
  }

  excluir() {
    if (this.formGroup.valid) {
      const estado = this.formGroup.value;
      if (estado.id != null) {
        console.log ('excluindo  o estado');
        this.estadoService.deleteEstado(estado.id).subscribe({
          next: () => {
            this.voltarPagina();
          },
          error: (err) => {
            console.error('Erro ao excluir estado:', err);
          }
        });
      }
    }
  }
}
