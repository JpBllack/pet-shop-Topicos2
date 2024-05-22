import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';
import { Usuario } from '../../models/Usuario';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { RouterModule } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-alterar-informacoes',
  templateUrl: './alterar-informacoes.component.html',
  styleUrls: ['./alterar-informacoes.component.css'],
  imports: [CommonModule, ReactiveFormsModule,RouterModule]
})
export class AlterarInformacoesComponent implements OnInit {
  novoCpf: string = '';
  novoNome: string = '';
  novoUsername: string = '';
  novoEmail: string = '';
  formAlterarInformacoes!: FormGroup; // Usando o operador de "definitely assigned" para informar ao TypeScript que essa variável será inicializada no método ngOnInit()
  usuario!: Usuario;

  constructor(private formBuilder: FormBuilder, private usuarioLogadoService: UsuarioLogadoService, private authService: AuthService) { }

  ngOnInit(): void {
    // Inicializa o formulário
    this.formAlterarInformacoes = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      cpf: ['', Validators.required]
    });

    // Carrega as informações do usuário ao abrir a página
    this.carregarInformacoesUsuario();
  }

  carregarInformacoesUsuario() {
    this.usuarioLogadoService.getUsuarioLogado().subscribe(
      (usuario) => {
        this.usuario = usuario;
        // Preenche os campos do formulário com as informações do usuário
        this.formAlterarInformacoes.patchValue({
          username: usuario.username,
          email: usuario.email,
          cpf: usuario.cpf
        });
      },
      (error) => {
        console.error('Erro ao carregar informações do usuário:', error);
      }
    );
  }

  salvarAlteracoes() {
    if (this.formAlterarInformacoes.valid) {
      const { username, email, cpf } = this.formAlterarInformacoes.value;
  
      if (username !== this.usuario.username) {
        this.usuarioLogadoService.updateUsername({ username }).subscribe(
          () => {
            console.log('Username atualizado com sucesso!');
            this.usuario.username = username; 
          },
          (error) => {
            console.error('Erro ao atualizar username:', error);
          }
        );
      }
  
      if (email !== this.usuario.email) {
        this.usuarioLogadoService.updateEmail({ email }).subscribe(
          () => {
            console.log('Email atualizado com sucesso!');
            this.usuario.email = email; 
          },
          (error) => {
            console.error('Erro ao atualizar email:', error);
          }
        );
      }
  
      if (cpf !== this.usuario.cpf) {
        this.usuarioLogadoService.updateCPF({ cpf }).subscribe(
          () => {
            console.log('CPF atualizado com sucesso!');
            this.usuario.cpf = cpf; 
          },
          (error) => {
            console.error('Erro ao atualizar CPF:', error);
          }
        );
      }
    } else {
      console.error('Formulário inválido.');
    }
  }
  

  // Métodos para atualizar os dados do usuário
  atualizarCpf() {
    this.usuarioLogadoService.updateCPF({ cpf: this.novoCpf }).subscribe(
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

}
