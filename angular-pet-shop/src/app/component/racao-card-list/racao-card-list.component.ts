import { Component, Input, OnInit, signal } from '@angular/core';
import { MatCard, MatCardActions, MatCardContent, MatCardFooter, MatCardTitle } from '@angular/material/card';
import { Consulta } from '../../models/consulta.model';
import { CarrinhoService } from '../../services/carrinho.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConsultaService } from '../../services/consulta.service';
import { CommonModule, NgFor } from '@angular/common';
import { MatButton } from '@angular/material/button';
import { RacaoService } from '../../services/racao.service';
import { Racao } from '../../models/racao.model';
import { ActivatedRoute, Router } from '@angular/router';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';

// tipo personalizado de dados, como classes e interfaces, porém mais simples.
type Card = {
  idConsulta: number;
  nome: string;
  preco: number;
  imagem: string;
  racao: Racao;
}

@Component({
  selector: 'app-consulta-card-list',
  standalone: true,
  imports: [MatCard, MatCardActions, MatCardContent, MatCardTitle, MatCardFooter, NgFor, MatButton, CommonModule, MatPaginatorModule],
  templateUrl: './racao-card-list.component.html',
  styleUrl: './racao-card-list.component.css'
})
export class RacaoCardListComponent implements OnInit {

  cards = signal<Card[]>([]);
  racoes: Racao[] = [];
  paginaAtual = 1;
  tamanhoPagina = 12;
  totalRacoes: number = 0;


  constructor(private racaoService: RacaoService,
    private carrinhoService: CarrinhoService,
    private snackBar: MatSnackBar,
    private route: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      const pagina = params['pagina'] || 1;
      this.carregarConsultas(pagina);
    });
  }


  PATH_USER = 'Users/Micro/quarkus/images/produto';

  carregarConsultas(pagina: number) {
    const inicio = (pagina - 1) * this.tamanhoPagina;
    const fim = inicio + this.tamanhoPagina;
    // buscando todos as consultas
    this.racaoService.getAllRacoes().subscribe(data => {
      this.racoes = data.slice(inicio, fim); // Ajuste aqui para carregar apenas as consultas da página atual
      this.carregarTotalRacoes(); // Carregar o total de racoes após carregar as consultas
      this.carregarCards();
      window.scrollTo(0, 0);
    });
  }

  carregarTotalRacoes() {
    this.racaoService.getTotalRacoes().subscribe(total => {
      this.totalRacoes = total;
    });
  }


  getImagemPath(imagem: string): string {
    return `http://localhost:8080/quarkus/images/produto/${imagem}`;
  }

  carregarCards() {
    const cards: Card[] = [];
    this.racoes.forEach(racao => {
      if (racao.estoque > 0) { // Verifica se o estoque é maior que zero
        const caminhoImagem = this.getImagemPath(racao.imagem);
        cards.push({
          idConsulta: racao.id,
          nome: racao.nome,
          preco: racao.preco,
          imagem: caminhoImagem,
          racao: racao
        });
      }
    });
    this.cards.set(cards);
  }
  


  adicionarAoCarrinho(card: Card) {
    this.showSnackbarTopPosition('Produto adicionado ao carrinho!', 'Fechar');
    this.carrinhoService.adicionar({
      id: card.idConsulta,
      nome: card.nome,
      imagem: card.imagem,
      preco: card.preco,
      quantidade: 1,
      frequencia: 0,
      racao: card.racao
    })

  }

  navegarParaProduto(id: number) {
    this.route.navigate(['/ver-produto', id]);
  }

  navegarParaPagina(pagina: number) {
    this.route.navigate([], { queryParams: { pagina } });
  }


  showSnackbarTopPosition(content: any, action: any) {
    this.snackBar.open(content, action, {
      duration: 2000,
      verticalPosition: "top", // Allowed values are  'top' | 'bottom'
      horizontalPosition: "center" // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
    });
  }
}