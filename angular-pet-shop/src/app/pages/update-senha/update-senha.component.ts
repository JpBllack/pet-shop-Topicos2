import { Component, OnInit } from '@angular/core';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Login } from '../../models/login';
import { Usuario } from '../../models/Usuario';

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


  constructor(private usuarioLogadoService: UsuarioLogadoService, private formBuilder: FormBuilder, private authService: AuthService, private router: Router) {
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

          const usuarioLogado = this.authService.getUsuarioLogadoAsValue();
          if (usuarioLogado && usuarioLogado.email) {
            const loginData: Login = {
              email: usuarioLogado.email,
              senha: novaSenha
            };

            this.authService.login(loginData).subscribe({
              next: () => {
                this.router.navigateByUrl('/dashboard');
              },
              error: (erro) => {
                console.log('Erro ao fazer login automaticamente:', erro);
                alert('A senha foi alterada, mas houve um problema ao logar novamente.');
              }
            });
          } else {
            console.error('Usuário não encontrado ou email não definido.');
            alert('A senha foi alterada, mas não foi possível obter o usuário logado para relogar.');
          }
        },
        (error) => {
          console.error('Erro ao alterar a senha:', error);
          alert('A senha não foi alterada');
        }
      );
  }


}
