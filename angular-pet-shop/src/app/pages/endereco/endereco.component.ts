import { Component } from '@angular/core';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-endereco',
  standalone:true,
  imports: [MatTableModule, MatPaginatorModule, MatSortModule, MatFormFieldModule,CommonModule,FormsModule],
  templateUrl: './endereco.component.html',
  styleUrls: ['./endereco.component.css']
})
export class EnderecoComponent {
  logradouro!: string;
  numero!: string;
  complemento!: string;
  bairro!: string;
  idCidade!: string;
  cep!: string;

  constructor(
    private usuarioLogadoService: UsuarioLogadoService
  ) {}

  submitForm() {
    const endereco = {
      logradouro: this.logradouro,
      numero: this.numero,
      complemento: this.complemento,
      bairro: this.bairro,
      idCidade: this.idCidade,
      cep: this.cep
    };
    
    this.usuarioLogadoService.insertEndereco(endereco).subscribe(
      response => {
        console.log('Endereço criado com sucesso!', response);
        // Reinicie os valores dos campos do formulário ou redirecione para outra página, se necessário
      },
      error => {
        console.error('Erro ao criar endereço:', error);
        // Lide com o erro de acordo com sua lógica de aplicativo
      }
    );
  }
}