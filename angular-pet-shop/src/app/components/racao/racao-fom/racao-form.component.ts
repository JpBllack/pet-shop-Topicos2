import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RacaoService } from '../../../services/racao.serviçe';
import { ActivatedRoute, Router } from '@angular/router';
import { Racao } from '../../../models/racao.model';
import { MatToolbarModule } from '@angular/material/toolbar';


@Component({
  selector: 'app-racao-form',
  templateUrl: './racao-form.component.html',
  styleUrls: ['./racao-form.component.css']
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
