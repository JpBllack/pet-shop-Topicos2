import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { UsuarioLogadoService } from "../../services/usuarioLogado.service";
import { AuthService } from "../../services/auth.service";
import { CommonModule } from "@angular/common";
import { MatBadge } from "@angular/material/badge";
import { MatButton, MatIconButton } from "@angular/material/button";
import { MatIcon } from "@angular/material/icon";
import { MatToolbar } from "@angular/material/toolbar";
import { RouterModule } from "@angular/router";

@Component({
    selector: 'app-dashboard',
    standalone: true,
  imports: [MatToolbar, MatIcon, MatBadge, MatButton, MatIconButton, RouterModule, CommonModule],
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  usuario: any;

  // Propriedades para atualizar os dados do usuário
  novoCpf: string = '';
  novoNome: string = '';
  novoUsername: string = '';
  novoEmail: string = '';
  novaSenha: string = '';

  constructor(private authService: AuthService, private usuarioLogadoService: UsuarioLogadoService) { }

  ngOnInit(): void {
    // Inicializa os dados do usuário ao carregar o componente
    this.carregarUsuario();
  }

  // Método para carregar os dados do usuário
  carregarUsuario() {
    this.authService.getUsuarioLogado().subscribe(
      (usuario) => {
        this.usuario = usuario;
      },
      (error) => {
        console.error('Erro ao carregar dados do usuário:', error);
      }
    );
  }

  // Métodos para atualizar os dados do usuário
  atualizarCpf() {
    this.usuarioLogadoService.updateCpf({ cpf: this.novoCpf }).subscribe(
      (usuario) => {
        this.usuario = usuario;
        this.novoCpf = ''; // Limpa o campo após a atualização
      },
      (error) => {
        console.error('Erro ao atualizar CPF:', error);
      }
    );
  }

  atualizarNome() {
    this.usuarioLogadoService.updateNome({ nome: this.novoNome }).subscribe(
      (usuario) => {
        this.usuario = usuario;
        this.novoNome = ''; // Limpa o campo após a atualização
      },
      (error) => {
        console.error('Erro ao atualizar nome:', error);
      }
    );
  }

  atualizarUsername() {
    this.usuarioLogadoService.updateUsername({ username: this.novoUsername }).subscribe(
      (usuario) => {
        this.usuario = usuario;
        this.novoUsername = ''; // Limpa o campo após a atualização
      },
      (error) => {
        console.error('Erro ao atualizar username:', error);
      }
    );
  }

  atualizarEmail() {
    this.usuarioLogadoService.updateEmail({ email: this.novoEmail }).subscribe(
      (usuario) => {
        this.usuario = usuario;
        this.novoEmail = ''; // Limpa o campo após a atualização
      },
      (error) => {
        console.error('Erro ao atualizar email:', error);
      }
    );
  }

  atualizarSenha() {
    this.usuarioLogadoService.updateSenha({ senha: this.novaSenha }).subscribe(
      (usuario) => {
        this.usuario = usuario;
        this.novaSenha = ''; // Limpa o campo após a atualização
      },
      (error) => {
        console.error('Erro ao atualizar senha:', error);
      }
    );
  }
}
