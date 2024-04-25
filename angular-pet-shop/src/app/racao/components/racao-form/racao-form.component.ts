import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RacaoService } from '../../../services/racao.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Racao } from '../../../models/racao.model';
import { MatToolbarModule } from '@angular/material/toolbar';
import { NgIf } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatOptionModule } from '@angular/material/core';
import { HttpClient } from '@angular/common/http';
import { TipoAnimal } from '../../../models/tipoAnimal';
import { TipoAnimalService } from '../../../services/TipoAnimal.service';
import { Peso } from '../../../models/peso';
import { Idade } from '../../../models/idade';



@Component({
  selector: 'app-racao-form',
  standalone: true,
  imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
    MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule, MatOptionModule, CommonModule],
  templateUrl: './racao-form.component.html',
  styleUrls: ['./racao-form.component.css']
})
export class RacaoFormComponent implements OnInit {

  formGroup: FormGroup;
  animais: TipoAnimal[] = [];
  pesos = Object.values(Peso).filter(value => isNaN(Number(value)));
  idades = Object.values(Idade).filter(value => isNaN(Number(value)));

  constructor(formBuilder: FormBuilder,
              private racaoService: RacaoService,
              private tipoAnimalService: TipoAnimalService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private http: HttpClient) {

                

    this.formGroup = formBuilder.group({
      id: [null],
      sabor: ['', Validators.required],
      animal: [null, Validators.required],
      peso: [null, Validators.required],
      idade: [null, Validators.required],
    });
  }

  ngOnInit(): void {
    const racao: Racao = this.activatedRoute.snapshot.data['racao'];
    this.carregarTipos();
    if (racao) {
      this.formGroup.patchValue(racao);
    };
  }

  salvar() {
    if (this.formGroup.valid) {
      const racao = this.formGroup.value;
      if (racao.id == null) {
        this.racaoService.createRacao(racao).subscribe({
          next: () => {
            this.router.navigateByUrl('/racoes/all');
          },
          error: (err) => {
            console.log('Erro ao Incluir' + JSON.stringify(err));
          }
        });
      } else {
        this.racaoService.updateRacao(racao).subscribe({
          next: () => {
            this.router.navigateByUrl('/racoes/all');
          },
          error: (err) => {
            console.log('Erro ao Editar' + JSON.stringify(err));
          }
        });
      }
    }
  }

  excluir() {
    if (this.formGroup.valid) {
      const racao = this.formGroup.value;
      if (racao.id != null) {
        this.racaoService.deleteRacao(racao.id).subscribe({
          next: () => {
            this.router.navigateByUrl(`/racoes/all'`);
          },
          error: (err) => {
            console.log('Erro ao Excluir' + JSON.stringify(err));
          }
        });
      }
    }
  }

  carregarTipos(){
    this.tipoAnimalService.findAll().subscribe(
      (tipo: TipoAnimal[]) => {
        this.animais = tipo;
      },
      (error) => {
        console.error('Erro ao carregar animais:', error);
      }
    )
  }
}

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatSelectModule,
    MatOptionModule,
  ]
})
export class RacaoFormModule { }