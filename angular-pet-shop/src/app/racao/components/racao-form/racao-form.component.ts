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


@Component({
  selector: 'app-racao-form',
  standalone: true,
  imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
    MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule],
  templateUrl: './racao-form.component.html',
  styleUrl: './racao-form.component.css'
})

export class RacaoFormComponent implements OnInit {

  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private racaoService: RacaoService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {

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
    if (racao) {
      this.formGroup.patchValue(racao);
    }
  }

  salvar() {
    if (this.formGroup.valid) {
      const racao = this.formGroup.value;
      if (racao.id == null) {
        this.racaoService.createRacao(racao).subscribe({
          next: () => {
            this.router.navigateByUrl('/racoes');
          },
          error: (err) => {
            console.log('Erro ao Incluir' + JSON.stringify(err));
          }
        });
      } else {
        this.racaoService.updateRacao(racao).subscribe({
          next: () => {
            this.router.navigateByUrl('/racoes');
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
            this.router.navigateByUrl('/racoes');
          },
          error: (err) => {
            console.log('Erro ao Excluir' + JSON.stringify(err));
          }
        });
      }
    }
  }

}