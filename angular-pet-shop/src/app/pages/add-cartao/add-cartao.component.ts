import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule, Location } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { UsuarioLogadoService, Cartao } from '../../services/usuarioLogado.service';

@Component({
  selector: 'app-add-cartao',
  templateUrl: './add-cartao.component.html',
  styleUrls: ['./add-cartao.component.css'],
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    RouterModule
  ]
})
export class AddCartaoComponent implements OnInit {
  cartaoForm!: FormGroup;
  cartaoId!: number;
  meses = [
    { value: 1, name: 'Janeiro' },
    { value: 2, name: 'Fevereiro' },
    { value: 3, name: 'Março' },
    { value: 4, name: 'Abril' },
    { value: 5, name: 'Maio' },
    { value: 6, name: 'Junho' },
    { value: 7, name: 'Julho' },
    { value: 8, name: 'Agosto' },
    { value: 9, name: 'Setembro' },
    { value: 10, name: 'Outubro' },
    { value: 11, name: 'Novembro' },
    { value: 12, name: 'Dezembro' }
  ];
  anos = Array.from({ length: 21 }, (_, i) => new Date().getFullYear() + i);

  constructor(
    private fb: FormBuilder,
    private usuarioLogadoService: UsuarioLogadoService,
    private router: Router,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.cartaoForm = this.fb.group({
      nome: ['', Validators.required],
      numero: ['', [Validators.required, Validators.pattern('^[0-9]{16}$')]],
      codigoSeguranca: ['', [Validators.required, Validators.maxLength(3), Validators.pattern('^[0-9]{3}$')]],
      mesValidade: ['', Validators.required],
      anoValidade: ['', Validators.required],
      isPrincipal: [false]
    });

    this.route.params.subscribe(params => {
      this.cartaoId = +params['id'];
      if (this.cartaoId) {
        this.carregarDadosCartao(this.cartaoId);
      }
    });
  }

  carregarDadosCartao(id: number): void {
    this.usuarioLogadoService.getCartaoById(id).subscribe(
      cartao => {
        this.cartaoForm.patchValue({
          nome: cartao.nome,
          //numero: cartao.numero,
          codigoSeguranca: cartao.codigoSeguranca,
          mesValidade: cartao.mesValidade,
          anoValidade: cartao.anoValidade,
          isPrincipal: cartao.isPrincipal
        });
      },
      error => {
        console.error('Erro ao carregar dados do cartão', error);
      }
    );
  }

  onSubmit(): void {
    if (this.cartaoForm.valid) {
      const novoCartao: Cartao = this.cartaoForm.value;
      this.usuarioLogadoService.insertCartao(novoCartao).subscribe(
        response => {
          console.log('Cartão adicionado com sucesso', response);
          this.router.navigate(['/meus-cartoes']);
        },
        error => {
          console.error('Erro ao adicionar cartão', error);
        }
      );
    }
  }

  voltar(): void {
    this.location.back(); // Retorna para a página anterior
  }
}
