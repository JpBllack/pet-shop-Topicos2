import { Component, OnInit, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms'; // Importe o módulo de formulários se necessário
import { RacaoService } from '../../services/racao.service';
import { CarrinhoService } from '../../services/carrinho.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatButton } from '@angular/material/button';
import { MatCard, MatCardActions, MatCardContent, MatCardTitle, MatCardFooter } from '@angular/material/card';
import { Racao } from '../../models/racao.model';

type Card = {
  idConsulta: number;
  nome: string;
  preco: number;
  imagem: string;
  racao: Racao;
}

@Component({
  selector: 'app-busca',
  templateUrl: './busca.component.html',
  styleUrls: ['./busca.component.css'],
  standalone: true, // Defina como true para indicar que é um componente independente
  imports: [CommonModule, FormsModule, MatCard, MatCardActions, MatCardContent, MatCardTitle, MatCardFooter, NgFor, MatButton] // Importe os módulos necessários aqui
})
export class BuscaComponent implements OnInit {
  resultados: any[] = [];
  cards = signal<Card[]>([]);

  constructor(private route: ActivatedRoute,
    private racaoService: RacaoService,
    private carrinhoService: CarrinhoService,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      // @ts-ignore
      if (params['racoes']) {
        this.resultados = JSON.parse(params['racoes']);
        this.carregarProdutos();
      }
    });
  }

  PATH_USER = 'Users/Micro/quarkus/images/produto';

  carregarProdutos() {
    const cards: Card[] = this.resultados.map(racao => ({
      idConsulta: racao.id,
      nome: racao.nome,
      preco: racao.preco,
      imagem: this.getImagemPath(racao.imagem),
      racao: racao
    }));
    this.cards.set(cards);
  }


  getImagemPath(imagem: string): string {
    return `http://localhost:8080/quarkus/images/produto/${imagem}`;
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
    });
  }

  navegarParaProduto(id: number) {
    this.router.navigate(['/ver-produto', id]);
  }

  showSnackbarTopPosition(content: any, action: any) {
    this.snackBar.open(content, action, {
      duration: 2000,
      verticalPosition: "top",
      horizontalPosition: "center"
    });
  }

}
