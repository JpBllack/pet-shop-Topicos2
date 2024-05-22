import { Component, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';

@Component({
  standalone: true,
  selector: 'app-confirmar-senha',
  templateUrl: './confirmar-senha.component.html',
  styleUrls: ['./confirmar-senha.component.css'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class ConfirmarSenhaComponent {
  @Output() confirmar: EventEmitter<void> = new EventEmitter();
  @Output() fechar: EventEmitter<void> = new EventEmitter();

  formConfirmarSenha: FormGroup;
  errorMessage: string = '';

  constructor(
    private formBuilder: FormBuilder, 
    private authService: AuthService, 
    private usuarioLogadoService: UsuarioLogadoService
  ) {
    this.formConfirmarSenha = this.formBuilder.group({
      senha: ['', Validators.required]
    });
  }

  confirmarSenha() {
    if (this.formConfirmarSenha.valid) {
      const senha = this.formConfirmarSenha.get('senha')?.value;
      this.usuarioLogadoService.getUsuarioLogado().subscribe(usuario => {
        const loginData = { email: usuario.email, senha: senha };
        this.authService.login(loginData).subscribe(
          () => {
            this.confirmar.emit();
            this.fechar.emit();
          },
          error => {
            this.errorMessage = 'Senha incorreta. Por favor, tente novamente.';
            console.error('Erro ao confirmar senha:', error);
          }
        );
      });
    } else {
      this.errorMessage = 'Por favor, preencha a senha.';
    }
  }

  onFechar() {
    this.fechar.emit();
  }
}
