import { Component, OnInit } from '@angular/core';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Login } from '../../models/login';

@Component({
  selector: 'app-alterar-senha',
  standalone: true,
  templateUrl: './update-senha.component.html',
  styleUrls: ['./update-senha.component.css'],
  imports: [ReactiveFormsModule, RouterModule]
})
export class AlterarSenhaComponent implements OnInit {
  senhaAtual: string = '';
  novaSenha: string = '';
  formGroup: FormGroup;
  loginForm!: FormGroup;

  constructor(private usuarioLogadoService: UsuarioLogadoService, private formBuilder: FormBuilder, private authService: AuthService) {
    this.formGroup = this.formBuilder.group({
      senhaAtual: ['', Validators.required],
      novaSenha: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }


  alterarSenha(): void {

    const {senhaAtual, novaSenha} = this.formGroup.value;

    this.usuarioLogadoService.updateSenha({senhaAtual, novaSenha})
      .subscribe(
        () => {
          console.log('Senha alterada com sucesso');
          alert('Senha alterada com sucesso');
        },
        (error) => {
          console.error('Erro ao alterar a senha:', error);
          alert('A senha não foi alterada');
        }
      );
  }


}
