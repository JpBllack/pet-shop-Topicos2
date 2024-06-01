import { Component, OnInit } from "@angular/core";
import { UsuarioLogadoService } from "../../services/usuarioLogado.service";
import { AuthService } from "../../services/auth.service";
import { CommonModule } from "@angular/common";
import { MatBadge } from "@angular/material/badge";
import { MatButton, MatIconButton } from "@angular/material/button";
import { MatIcon } from "@angular/material/icon";
import { MatToolbar } from "@angular/material/toolbar";
import { RouterModule } from "@angular/router";
import { catchError } from "rxjs";

@Component({
    selector: 'app-dashboard',
    standalone: true,
    imports: [MatToolbar, MatIcon, MatBadge, MatButton, MatIconButton, RouterModule, CommonModule],
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  usuario: any;
  selectedFile: File | null = null;

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

  onImageSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      console.log('Arquivo selecionado:', file);
      this.selectedFile = file;
    } else {
      console.error('Nenhum arquivo selecionado');
      this.selectedFile = null;
    }
  }

  onUpload() {
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('imagem', this.selectedFile);
      formData.append('usuarioId', this.usuario.id);

      formData.forEach((value, key) => {
        console.log(`${key}: ${value}`);
      });

      this.usuarioLogadoService.uploadImage(formData).pipe(
        catchError((error) => {
          console.error('Erro ao fazer upload da imagem:', error);
          throw error;
        })
      ).subscribe(
        (response) => {
          console.log('Upload bem-sucedido:', response);
          this.usuario.imagem = response.imagemUrl;
        }
      );
    } else {
      console.error('Nenhuma imagem selecionada');
    }
  }
}
