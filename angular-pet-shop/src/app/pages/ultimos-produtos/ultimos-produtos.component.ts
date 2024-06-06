import { Component, OnInit, signal } from '@angular/core';
import { MatCard, MatCardActions, MatCardContent, MatCardFooter, MatCardTitle } from '@angular/material/card';
import { CarrinhoService } from '../../services/carrinho.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RacaoService } from '../../services/racao.service';
import { Racao } from '../../models/racao.model';
import { Router } from '@angular/router';
import { CommonModule, NgFor } from '@angular/common';
import { MatButton } from '@angular/material/button';

type Card = {
  idConsulta: number;
  nome: string;
  preco: number;
  imagem: string;
}

@Component({
  selector: 'app-ultimos-produtos',
  standalone: true,
  imports: [MatCard, MatCardActions, MatCardContent, MatCardTitle, MatCardFooter, NgFor, MatButton, CommonModule],
  templateUrl: './ultimos-produtos.component.html',
  styleUrls: ['./ultimos-produtos.component.css']
})
export class UltimosProdutosComponent implements OnInit {

  cards = signal<Card[]>([]);

  constructor(
    private racaoService: RacaoService,
    private carrinhoService: CarrinhoService,
    private snackBar: MatSnackBar,
    private route: Router
  ) {}

  ngOnInit(): void {
    this.carregarUltimosProdutos();
  }

  carregarUltimosProdutos() {
    this.racaoService.getUltimasRacoes().subscribe(data => {
      // Limitar os produtos aos últimos quatro
      const limitedData = data.slice(-4);
      const cards: Card[] = limitedData.map(racao => ({
        idConsulta: racao.id,
        nome: racao.nome,
        preco: racao.preco,
        imagem: this.getImagemPath(racao.imagem)
      }));
      this.cards.set(cards);
    });
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
      frequencia: 0
    });
  }

  navegarParaProduto(id: number) {
    this.route.navigate(['/ver-produto', id]);
  }

  showSnackbarTopPosition(content: any, action: any) {
    this.snackBar.open(content, action, {
      duration: 2000,
      verticalPosition: "top",
      horizontalPosition: "center"
    });
  }
}
