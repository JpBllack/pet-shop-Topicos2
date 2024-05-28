import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Racao } from '../../models/racao.model';
import { RacaoService } from '../../services/racao.service';
import { CarrinhoService } from '../../services/carrinho.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgFor, CommonModule } from '@angular/common';
import { MatButton } from '@angular/material/button';
import { MatCard, MatCardActions, MatCardContent, MatCardTitle, MatCardFooter } from '@angular/material/card';

// Definição do tipo Card
type Card = {
  idConsulta: number;
  nome: string;
  preco: number;
  imagem: string;
};

@Component({
  selector: 'app-busca',
  standalone: true,
  templateUrl: './busca.component.html',
  styleUrls: ['./busca.component.css'],
  imports: [MatCard, MatCardActions, MatCardContent, MatCardTitle, MatCardFooter, NgFor, MatButton,CommonModule],
})
export class BuscaComponent implements OnInit {

  racoes: Racao[] = [];
  cards: Card[] = []; // Inicializa a variável cards

  constructor(
    private route: ActivatedRoute,
    private racaoService: RacaoService,
    private snackBar: MatSnackBar,
    private carrinhoService: CarrinhoService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const nome = params['nome']; // Obtém o parâmetro 'nome' da URL
      if (nome) {
        this.carregarRacoes(nome);
      }
    });
  }

  // Método para carregar as rações com base no nome
  carregarRacoes(nome: string) {
    this.racaoService.findByNome(nome).subscribe(data => {
      this.racoes = data;
      this.carregarCards();
    });
  }

  // Método para construir os cards a partir das rações
  carregarCards() {
    this.cards = this.racoes.map(racao => ({
      idConsulta: racao.id,
      nome: racao.nome,
      preco: racao.preco,
      imagem: this.getImagemPath(racao.imagem)
    }));
  }

  // Método para obter o caminho da imagem
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
    })
  }

  navegarParaProduto(id: number) {
    this.router.navigate(['/ver-produto', id]); 
  }

  showSnackbarTopPosition(content:any, action:any) {
    this.snackBar.open(content, action, {
      duration: 2000,
      verticalPosition: "top", // Allowed values are  'top' | 'bottom'
      horizontalPosition: "center" // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
    });
  }
}
