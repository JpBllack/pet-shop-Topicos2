import { Component, OnInit, signal } from '@angular/core';
import { MatCard, MatCardActions, MatCardContent, MatCardFooter, MatCardTitle } from '@angular/material/card';
import { Consulta } from '../../models/consulta.model';
import { CarrinhoService } from '../../services/carrinho.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConsultaService } from '../../services/consulta.service';
import { NgFor } from '@angular/common';
import { MatButton } from '@angular/material/button';
import { RacaoService } from '../../services/racao.service';
import { Racao } from '../../models/racao.model';

// tipo personalizado de dados, como classes e interfaces, porém mais simples.
type Card = {
  idConsulta: number;
  sabor: string;
  preco: number;
  imagem: String;
}

@Component({
  selector: 'app-consulta-card-list',
  standalone: true,
  imports: [MatCard, MatCardActions, MatCardContent, MatCardTitle, MatCardFooter, NgFor, MatButton],
  templateUrl: './racao-card-list.component.html',
  styleUrl: './racao-card-list.component.css'
})
export class RacaoCardListComponent implements OnInit {

  cards = signal<Card[]> ([]);
  racoes: Racao[] = [];


  constructor(private racaoService: RacaoService, 
              private carrinhoService: CarrinhoService,
              private snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.carregarConsultas();
  }

  PATH_USER = 'Users/Micro/quarkus/images/produto';

  carregarConsultas() {
    // buscando todos as consultas
    this.racaoService.getAllRacoes().subscribe(data => {
      this.racoes = data;
      this.carregarCards();
    });
  }

  getImagemPath(imagem: string): string {
    return `http://localhost:8080/quarkus/images/produto/${imagem}`;
  }

  carregarCards() {
    const cards: Card[] = [];
    this.racoes.forEach(racao => {
      const caminhoImagem = this.getImagemPath(racao.imagem);
      console.log('Conteúdo da raçao:', racao); // Adicione este log para verificar o conteúdo da raçao
      cards.push({
        idConsulta: racao.id,
        sabor: racao.sabor,
        preco: racao.preco,
        imagem: caminhoImagem
      });
    });
    console.log('Conteúdo dos cards:', cards); // Adicione este log para verificar o conteúdo do array de cards
    this.cards.set(cards);
  }
 

  adicionarAoCarrinho(card: Card) {
    this.showSnackbarTopPosition('Produto adicionado ao carrinho!', 'Fechar');
    this.carrinhoService.adicionar({
      id: card.idConsulta,
      nome: card.sabor,
      preco: card.preco,
      quantidade: 1
    })

  }

  

  showSnackbarTopPosition(content:any, action:any) {
    this.snackBar.open(content, action, {
      duration: 2000,
      verticalPosition: "top", // Allowed values are  'top' | 'bottom'
      horizontalPosition: "center" // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
    });
  }
}