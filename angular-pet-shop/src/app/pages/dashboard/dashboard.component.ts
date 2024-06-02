import { Component, OnInit } from "@angular/core";
import { UsuarioLogadoService } from "../../services/usuarioLogado.service";
import { AuthService } from "../../services/auth.service";
import { catchError } from "rxjs/operators";
import { of } from 'rxjs';
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
  selectedFile: File | null = null;
  urlImagem: string = 'assets/login.png';

  constructor(private authService: AuthService, private usuarioLogadoService: UsuarioLogadoService) { }

  ngOnInit(): void {
    this.carregarUsuario();
    this.carregarImagemUsuario();
  }

  carregarUsuario() {
    this.usuarioLogadoService.getUsuarioLogado().subscribe(
      (usuario) => {
        this.usuario = usuario;
        if (usuario && usuario.imagem) {
          const nomeImagem = this.extractFileName(usuario.imagem);
          this.urlImagem = `http://localhost:8080/quarkus/images/usuario/${nomeImagem}`;
        }
      },
      (error) => {
        console.error('Erro ao carregar dados do usuário:', error);
      }
    );
  }

  carregarImagemUsuario() {
    if (this.usuario && this.usuario.imagem) {
      const nomeImagem = this.extractFileName(this.usuario.imagem);
      this.getImagemUsuario(nomeImagem);
    }
  }
  
  extractFileName(url: string): string {
    return url.substring(url.lastIndexOf('/') + 1);
  }
  
  getImagemUsuario(nomeImagem: string) {
    this.usuarioLogadoService.getImagemUsuario(nomeImagem).subscribe(
      (imagemBlob) => {
        // Criar a URL da imagem
        this.urlImagem = URL.createObjectURL(imagemBlob);
      },
      (error) => {
        console.error('Erro ao carregar imagem do usuário:', error);
        // Em caso de erro, define a imagem padrão de login
        this.urlImagem = 'assets/login.png';
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
      formData.append('nomeImagem', this.selectedFile.name); // adicionando o nome do arquivo

      console.log('FormData antes do upload:', formData);

      this.usuarioLogadoService.uploadImage(formData).pipe(
        catchError((error) => {
          console.error('Erro ao fazer upload da imagem:', error);
          return of(null);
        })
      ).subscribe(
        (response) => {
          if (response) {
            console.log('Upload bem-sucedido:', response);
            // Atualizando a propriedade de imagem do usuário
            this.usuario.imagem = response; // Se o serviço de upload retornar o caminho da imagem
            // Atualizar a URL da imagem
            const nomeImagem = this.extractFileName(response);
            this.urlImagem = `http://localhost:8080/quarkus/images/usuario/${nomeImagem}`;
          } else {
            console.error('Resposta do upload é nula.');
          }
        }
      );
    } else {
      console.error('Nenhuma imagem selecionada');
    }
  }  
}

