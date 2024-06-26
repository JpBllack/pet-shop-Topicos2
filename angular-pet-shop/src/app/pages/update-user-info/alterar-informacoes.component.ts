import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors, ReactiveFormsModule } from '@angular/forms';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';
import { Usuario } from '../../models/Usuario';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { RouterModule } from '@angular/router';
import { ConfirmarSenhaComponent } from '../confirmar-senha/confirmar-senha.component';
import { Nome } from '../../models/nome';
import { NgxMaskDirective, provideNgxMask } from 'ngx-mask';

@Component({
  standalone: true,
  selector: 'app-alterar-informacoes',
  templateUrl: './alterar-informacoes.component.html',
  styleUrls: ['./alterar-informacoes.component.css'],
  imports: [CommonModule, ReactiveFormsModule, RouterModule, ConfirmarSenhaComponent, NgxMaskDirective],
  providers: [provideNgxMask()]
})
export class AlterarInformacoesComponent implements OnInit {
  formAlterarInformacoes!: FormGroup;
  usuario!: Usuario;
  modalAberto: boolean = false;

  constructor(
    private formBuilder: FormBuilder, 
    private usuarioLogadoService: UsuarioLogadoService, 
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.formAlterarInformacoes = this.formBuilder.group({
      nome: ['', Validators.required],
      sobrenome: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      cpf: ['', [Validators.required, this.cpfValidator]]
    });

    this.carregarInformacoesUsuario();
  }

  carregarInformacoesUsuario() {
    this.usuarioLogadoService.getUsuarioLogado().subscribe(
      (usuario) => {
        this.usuario = usuario;
        this.formAlterarInformacoes.patchValue({
          nome: usuario.nome,
          sobrenome: usuario.sobrenome,
          username: usuario.username,
          email: usuario.email,
          cpf: usuario.cpf,
        });
      },
      (error) => {
        console.error('Erro ao carregar informações do usuário:', error);
      }
    );
  }

  abrirModalConfirmacao() {
    this.modalAberto = true;
  }

  fecharModal() {
    this.modalAberto = false;
  }

  salvarAlteracoes() {
    const { username, email, cpf, nome, sobrenome } = this.formAlterarInformacoes.value;
    const cpfSemFormatacao = cpf.replace(/\D/g, ''); // Remove pontos e traço

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

    if (cpfSemFormatacao !== this.usuario.cpf) {
      this.usuarioLogadoService.updateCPF({ cpf: cpfSemFormatacao }).subscribe(
        () => {
          console.log('CPF atualizado com sucesso!');
          this.usuario.cpf = cpfSemFormatacao; 
        },
        (error) => {
          console.error('Erro ao atualizar CPF:', error);
        }
      );
    }

    if (nome !== this.usuario.nome || sobrenome !== this.usuario.sobrenome) {
      const nomeCompleto: Nome = {
        nome: nome,
        sobrenome: sobrenome,
      };

      this.usuarioLogadoService.updateNome(nomeCompleto).subscribe(
        () => {
          console.log('Nome atualizado com sucesso!');
          this.usuario.nome = nome; 
          this.usuario.sobrenome = sobrenome;
        },
        (error) => {
          console.error('Erro ao atualizar nome:', error);
        }
      );
    }
    this.fecharModal(); // Fechar o modal após salvar as alterações
    this.atualizarUsuarioEToken();
  }

  atualizarUsuarioEToken() {
    this.usuarioLogadoService.getUsuarioLogado().subscribe(novoUsuario => {
      const novoToken = this.authService.getToken();
      if (novoToken) {
        this.authService.updateUsuarioLogado(novoUsuario);
        this.authService.updateToken(novoToken);
      } else {
        console.error('Erro: Token é nulo.');
      }
    });
  }

  cpfValidator(control: AbstractControl): ValidationErrors | null {
    const cpf = control.value.replace(/\D/g, ''); // Remove pontos e traço
    return cpf.length === 11 ? null : { 'invalidCpf': true };
  }
}
