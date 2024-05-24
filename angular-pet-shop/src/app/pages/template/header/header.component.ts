import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatIcon } from '@angular/material/icon';
import { MatToolbar } from '@angular/material/toolbar';
import { MatBadge } from '@angular/material/badge';

import { AuthService } from '../../../services/auth.service';
import { LocalStorageService } from '../../../services/local-storage.service';
import { SidebarService } from '../../../services/sidebar.service';
import { CarrinhoService } from '../../../services/carrinho.service';
import { Subscription } from 'rxjs';
import { MatButton, MatIconButton } from '@angular/material/button';
import { Router, RouterModule } from '@angular/router';
import { Usuario } from '../../../models/Usuario';
import { CommonModule } from '@angular/common';
import { RacaoService } from '../../../services/racao.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [MatToolbar, MatIcon, MatBadge, MatButton, MatIconButton, RouterModule, CommonModule, FormsModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit, OnDestroy {

  usuarioLogado: Usuario | null = null;
  private subscription = new Subscription();
  showLoginOptions: boolean = false;
  qtdItensCarrinho: number = 0;
  searchQuery: string = '';

  constructor(private sidebarService: SidebarService,
    private carrinhoService: CarrinhoService,
    private authService: AuthService,
    private racaoService: RacaoService,
    private localStorageService: LocalStorageService,
    private router: Router
  ) {

  }

  ngOnInit(): void {
    this.obterQtdItensCarrinho();
    this.obterUsuarioLogado();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  clickMenu() {
    this.sidebarService.toggle();
  }

  obterQtdItensCarrinho() {
    this.carrinhoService.carrinho$.subscribe(itens => {
      this.qtdItensCarrinho = itens.length
    });
  }

  obterUsuarioLogado() {
    this.subscription.add(this.authService.getUsuarioLogado().subscribe(
      usuario => this.usuarioLogado = usuario
    ));
  }

  deslogar() {
    this.authService.removeToken()
    this.authService.removeUsuarioLogado();
  }

  dashboard() {
    this.router.navigate(['/dashboard']);
  }

  buscarRacao() {
    if (this.searchQuery.trim()) {
      this.racaoService.findByNome(this.searchQuery).subscribe(racoes => {
        console.log('Rações encontradas:', racoes);
        // Aqui você pode fazer algo com os resultados, como navegar para uma página de resultados de busca
        // this.router.navigate(['/resultados-busca'], { queryParams: { racoes: JSON.stringify(racoes) } });
      });
    }
  }
}