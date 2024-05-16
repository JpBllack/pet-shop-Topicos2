import { CommonModule, NgIf } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatSelectModule } from "@angular/material/select";
import { MatToolbarModule } from "@angular/material/toolbar";
import { ActivatedRoute, Router, RouterModule } from "@angular/router";
import { UsuarioLogadoService } from "../../services/usuarioLogado.service";
import { Usuario } from "../../models/Usuario";
import { AuthService } from "../../services/auth.service";

@Component({
    selector: 'app-dashboard',
    standalone: true,
    imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
        MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule, CommonModule, FormsModule],
        templateUrl: './dashboard.component.html',
        styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{

  usuario: any;

  // Propriedades para atualizar os dados do usuário
  novoCpf: string = '';
  novoNome: string = '';
  novoUsername: string = '';
  novoEmail: string = '';
  novaSenha: string = '';

  constructor(private authService: AuthService) { }

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
    this.authService.updateCpf(this.novoCpf).subscribe(
      (usuario) => {
        this.usuario = usuario;
        this.novoCpf = ''; // Limpa o campo após a atualização
      },
      (error) => {
        console.error('Erro ao atualizar CPF:', error);
      }
    );
  }
}