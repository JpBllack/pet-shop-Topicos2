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

  constructor(private authService: AuthService, private usuarioLogadoService: UsuarioLogadoService) { }

  ngOnInit(): void {
    this.carregarUsuario();
  }

  carregarUsuario() {
    this.usuarioLogadoService.getUsuarioLogado().subscribe(
      (usuario) => {
        this.usuario = usuario;
        // Carregar a imagem do usuário quando o usuário for carregado
        this.carregarImagemUsuario();
      },
      (error) => {
        console.error('Erro ao carregar dados do usuário:', error);
      }
    );
  }

  carregarImagemUsuario() {
    if (this.usuario && this.usuario.imagem) {
      const nomeImagem = this.extractFileName(this.usuario.imagem);
      console.log(nomeImagem);
  
      this.getImagemUsuario(nomeImagem);
    }
  }
  
  // Função para extrair o nome do arquivo da URL
  extractFileName(url: string): string {
    return url.substring(url.lastIndexOf('/') + 1);
  }
  
  // Obter a imagem do usuário
  getImagemUsuario(nomeImagem: string) {
    this.usuarioLogadoService.getImagemUsuario(nomeImagem).subscribe(
      (imagemBlob) => {
        // Aqui você pode processar a imagem, se necessário
        console.log('Imagem carregada:', imagemBlob);
      },
      (error) => {
        console.error('Erro ao carregar imagem do usuário:', error);
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
            // Carregar a nova imagem do usuário após o upload
            this.carregarImagemUsuario();
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
