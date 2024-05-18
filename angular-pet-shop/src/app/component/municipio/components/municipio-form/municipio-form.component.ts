import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Location ,NgIf } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSelectModule } from '@angular/material/select';

import { municipioService } from '../../../../services/municipio.service';
import { EstadoService } from '../../../../services/estado.service';
import { Estado } from '../../../../models/estado.model';
import { municipio } from '../../../../models/municipio';

@Component({
  selector: 'app-municipio-form',
  standalone: true,
  imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
            MatInputModule, MatButtonModule, MatCardModule,
            MatToolbarModule, RouterModule, MatSelectModule],
  templateUrl: './municipio-form.component.html',
  styleUrls: ['./municipio-form.component.css']
})
export class MunicipioFormComponent implements OnInit {
  formGroup: FormGroup;
  estados: Estado[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private municipioService: municipioService,
    private estadoService: EstadoService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private location: Location
  ) {
    this.formGroup = this.formBuilder.group({
      id: [null],
      nome: ['', Validators.required],
      estado: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadEstados();
    this.initializeForm();
  }

  

  voltarPagina() {
    this.location.back();
  }


  loadEstados() {
    this.estadoService.findAll().subscribe(
      (data: Estado[]) => {
        this.estados = data;
      },
      (error) => {
        console.error('Erro ao carregar estados:', error);
      }
    );
  }

  initializeForm(): void {
    const municipio: municipio | null = this.activatedRoute.snapshot.data['municipio'];
    const estado = this.estados.find(estado => estado.id === municipio?.estado?.id);
    this.formGroup.patchValue({
      id: municipio?.id ?? null,
      nome: municipio?.nome ?? '',
      estado: estado ?? null
    });
  }

  salvar() {
    if (this.formGroup.valid) {
      const municipio = this.formGroup.value;
      if (municipio.id == null) {
        this.municipioService.insertMunicipio(municipio).subscribe({
          next: () => {
            console.log('Município inserido com sucesso.');
            this.voltarPagina();
          },
          error: (err) => {
            console.log('Erro ao incluir município:', err);
          }
        });
      } else {
        this.municipioService.updateMunicipio(municipio).subscribe({
          next: () => {
            console.log('Município atualizado com sucesso.');
            this.voltarPagina();
          },
          error: (err) => {
            console.log('Erro ao editar município:', err);
          }
        });
      }
    }
  }

  excluir() {
    const municipio = this.formGroup.value;
    if (municipio.id != null) {
        this.municipioService.deleteMunicipio(municipio.id).subscribe({
          next: () => {
            console.log('Município excluído com sucesso.');
            this.voltarPagina();
          },
          error: (err) => {
            console.log('Erro ao excluir município:', err);
          }
        });
    }
  }
}
