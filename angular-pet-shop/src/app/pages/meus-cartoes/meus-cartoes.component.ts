import { Component, OnInit, Pipe, PipeTransform } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Cartao, UsuarioLogadoService } from '../../services/usuarioLogado.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-meus-cartoes',
  standalone: true,
  templateUrl: './meus-cartoes.component.html',
  styleUrls: ['./meus-cartoes.component.css'],
  imports: [NgFor, NgIf, CommonModule, RouterModule, MatIcon]
})
export class MeusCartoesComponent implements OnInit {
  cartoes!: Observable<Cartao[]>;

  // Injete o serviço UsuarioLogadoService no construtor
  constructor(private usuarioLogadoService: UsuarioLogadoService, private router: Router) { }

  ngOnInit(): void {
    this.cartoes = this.usuarioLogadoService.getCartoesUsuario().pipe(
      map(cartoes => {
        // Ordena os cartões colocando o cartão principal primeiro
        return cartoes.sort((a, b) => {
          if (a.isPrincipal) return -1; // cartão principal primeiro
          if (b.isPrincipal) return 1;
          return 0;
        });
      })
    );
  }

  adicionarCartao(): void {
    this.router.navigate(['/add-cartao']); // Navegar para a página de adicionar cartão
  }

  editarCartao(id: number): void {
    this.router.navigate(['/edit-cartao', id]);
  }  

  deletarCartao(id: number): void {
    this.usuarioLogadoService.deleteCartao(id).subscribe(() => {
      this.atualizarCartoes(); // Atualiza a lista após deletar o cartão
    });
  }

  setPrincipal(cartao: Cartao): void {
    if (!cartao.isPrincipal) {
      this.usuarioLogadoService.setCartaoPrincipal(cartao.id).subscribe(() => {
        this.atualizarCartoes(); // Atualiza a lista após definir o principal
      });
    }
  }

  atualizarCartoes(): void {
    this.cartoes = this.usuarioLogadoService.getCartoesUsuario().pipe(
      map(cartoes => {
        // Ordena os cartões colocando o cartão principal primeiro
        return cartoes.sort((a, b) => {
          if (a.isPrincipal) return -1; // cartão principal primeiro
          if (b.isPrincipal) return 1;
          return 0;
        });
      })
    );
  }
}
