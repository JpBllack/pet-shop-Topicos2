import { Component, OnInit } from "@angular/core";
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

  constructor(private authService: AuthService, private usuarioLogadoService: UsuarioLogadoService) { }

  ngOnInit(): void {
    this.carregarUsuario();
  }

  carregarUsuario() {
    this.usuarioLogadoService.getUsuarioLogado().subscribe(
      (usuario) => {
        this.usuario = usuario;
      },
      (error) => {
        console.error('Erro ao carregar dados do usuário:', error);
      }
    );
  }

  formatarCPF(cpf: string): string {
    if (!cpf) return '';
    return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
  }
}
