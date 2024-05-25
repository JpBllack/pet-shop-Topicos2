import { Component, OnInit } from '@angular/core';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';

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

  constructor(private usuarioLogadoService: UsuarioLogadoService, private formBuilder: FormBuilder) { 
    this.formGroup = this.formBuilder.group({ 
      senhaAtual: ['', Validators.required],
      novaSenha: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  
  alterarSenha(): void {

    /*
    const senha = {
      senha: this.formGroup.get('senhaAtual').value,
      novaSenha: this.formGroup.get('novaSenha').value
    };
  
    this.usuarioLogadoService.updateSenha(senha)
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
      */
  }
  
  
  
  

}
