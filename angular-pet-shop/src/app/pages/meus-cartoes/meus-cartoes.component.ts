import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Cartao, UsuarioLogadoService } from '../../services/usuarioLogado.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';


@Component({
  selector: 'app-meus-cartoes',
  standalone: true,
  templateUrl: './meus-cartoes.component.html',
  styleUrls: ['./meus-cartoes.component.css'],
  imports: [NgFor, NgIf, CommonModule]
})
export class MeusCartoesComponent implements OnInit {
  cartoes!: Observable<Cartao[]>;

  // Injete o serviço UsuarioLogadoService no construtor
  constructor(private usuarioLogadoService: UsuarioLogadoService) { }

  ngOnInit(): void {
    this.cartoes = this.usuarioLogadoService.getCartoesUsuario();
  }

  adicionarCartao(): void {
    
  }
}
