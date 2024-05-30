import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Endereco } from '../../models/endereco';
import { EnderecoService } from '../../services/endereco.service';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';


@Component({
  selector: 'app-ver-endereco',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ver-endereco.component.html',
  styleUrls: ['./ver-endereco.component.css']
})
export class VerEnderecoComponent implements OnInit {
  enderecosUsuario: Endereco[] = [];
  cep: string = '';
  idCidade: number | null = null;
  bairro: string = '';
  logradouro: string = '';
  numero: string = '';
  complemento: string = '';
  enderecoPrincipal: boolean = false;

  constructor(private enderecoService: EnderecoService, private router: Router,private usuarioLogadoService: UsuarioLogadoService
  ) {}

  ngOnInit(): void {
    this.getEnderecosUsuario();
  }

  getEnderecosUsuario(): void {
    this.enderecoService.getEnderecos().subscribe((enderecos: Endereco[]) => {
      this.enderecosUsuario = enderecos;
    });
  }

  submitForm(): void {
    const novoEndereco: Endereco = {
      cep: this.cep,
      idCidade: this.idCidade = 0,
      bairro: this.bairro,
      logradouro: this.logradouro,
      numero: this.numero,
      complemento: this.complemento,
      enderecoPrincipal: this.enderecoPrincipal,
    };

    this.usuarioLogadoService.getUsuarioLogado().subscribe(usuario => {
      const userId = usuario.id; // Obtenha o ID do usuário logado do objeto Usuario
      this.enderecoService.addEndereco(novoEndereco, userId).subscribe(() => {
        this.getEnderecosUsuario();
        this.resetForm();
      });
    });
  }


  resetForm(): void {
    this.cep = '';
    this.idCidade = null;
    this.bairro = '';
    this.logradouro = '';
    this.numero = '';
    this.complemento = '';
    this.enderecoPrincipal = false;
  }

  voltar(): void {
    this.router.navigate(['/dashboard']);
  }

  marcarComoPrincipal(endereco: Endereco): void {
    // Verifique se o endereço já está marcado como principal
    if (endereco.enderecoPrincipal) {
      console.log('Endereço já está marcado como principal:', endereco);
      return; // Não é necessário fazer nada se já estiver marcado como principal
    }
  
    // Atualize o status de endereço principal no backend
    this.enderecoService.marcarComoPrincipal(endereco.enderecoPrincipal).subscribe(() => {
      console.log('Endereço marcado como principal:', endereco);
      // Atualize a lista de endereços após a marcação como principal
      this.getEnderecosUsuario();
    }, error => {
      console.error('Erro ao marcar como principal:', error);
      // Lógica para lidar com erros, se necessário
    });
  }
  
}