import { Component, OnInit } from '@angular/core';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { CommonModule, Location } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { Estado } from '../../models/estado.model';
import { municipio } from '../../models/municipio';
import { EstadoService } from '../../services/estado.service';
import { municipioService } from '../../services/municipio.service';


@Component({
  selector: 'app-endereco',
  standalone: true,
  imports: [MatTableModule, MatPaginatorModule, MatSortModule, MatFormFieldModule, CommonModule, FormsModule, RouterModule],
  templateUrl: './endereco.component.html',
  styleUrls: ['./endereco.component.css'],
})
export class EnderecoComponent implements OnInit{
  logradouro!: string;
  numero!: string;
  complemento!: string;
  bairro!: string;
  estadoId!: number;
  idCidade!: number;
  cep!: string;
  enderecoPrincipal: boolean = false;

  estados: Estado[] = [];
  municipios: municipio[] = [];

  constructor(
    private usuarioLogadoService: UsuarioLogadoService,
    private router: Router,
    private location: Location,
    private estadoService: EstadoService,
    private municipioService: municipioService,
  ) {}

  ngOnInit() {
    this.loadEstados();
  }

  loadEstados() {
    this.estadoService.findAll().subscribe(
      (data) => {
        this.estados = data.sort((a, b) => a.nome.localeCompare(b.nome));
      },
      (error) => {
        console.error('Erro ao carregar estados:', error);
      }
    );
  }

  onEstadoChange() {
    console.log('Estado ID selecionado:', this.estadoId); // Adicionado para depuração
    if (this.estadoId) {
      this.municipioService.findByEstadoId(this.estadoId).subscribe(
        (data) => {
          console.log('Municípios carregados:', data); // Adicionado para depuração
          this.municipios = data.sort((a, b) => a.nome.localeCompare(b.nome));
        },
        (error) => {
          console.error('Erro ao carregar municípios:', error);
        }
      );
    }
  }

  submitForm() {
    const endereco = {
      logradouro: this.logradouro,
      numero: this.numero,
      complemento: this.complemento,
      bairro: this.bairro,
      idCidade: this.idCidade,
      isPrincipal: this.enderecoPrincipal
    };
    
    this.usuarioLogadoService.insertEndereco(endereco).subscribe(
      response => {
        console.log('Endereço criado com sucesso!', response);
        // Reinicie os valores dos campos do formulário se necessário
        this.location.back();
      },
      error => {
        console.error('Erro ao criar endereço:', error);
        // Lide com o erro de acordo com sua lógica de aplicativo
      }
    );
  }

  resetForm() {
    this.logradouro = '';
    this.numero = '';
    this.complemento = '';
    this.bairro = '';
    this.idCidade = 0;
    this.cep = '';
    this.enderecoPrincipal = false;
  }

  voltar(): void {
    this.location.back();
  }
   
}
