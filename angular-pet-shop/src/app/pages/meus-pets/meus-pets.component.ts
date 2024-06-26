import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';

@Component({
  selector: 'app-meus-pets',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './meus-pets.component.html',
  styleUrls: ['./meus-pets.component.css']
})
export class MeusPetsComponent implements OnInit {
  petsUsuario: any[] = [];

  constructor(private usuarioLogadoService: UsuarioLogadoService) { }

  ngOnInit(): void {
    this.loadPetsUsuario();
  }

  loadPetsUsuario(): void {
    this.usuarioLogadoService.getPetsUsuario().subscribe(data => {
      this.petsUsuario = data;
    }, error => {
      console.error('Erro ao carregar os pets do usuário:', error);
    });
  }

  calcularIdade(anoNascimento: number): number {
    const hoje = new Date();
    const anoAtual = hoje.getFullYear();
    const idade = anoAtual - anoNascimento;
    return idade;
  }
}
