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

// tipo personalizado de dados, como classes e interfaces, porém mais simples.
type Card = {
  idConsulta: number;
  nome: string;
  preco: number;
  imagem: string;
}

@Component({
  selector: 'app-filtro-racao',
  standalone: true,
  imports: [MatCard, MatCardActions, MatCardContent, MatCardTitle, MatCardFooter, NgFor, MatButton,CommonModule],
  templateUrl: './filtro-racao.component.html',
  styleUrl: './filtro-racao.component.css'
})
export class FiltroRacaoComponent implements OnInit {

 

  cards = signal<Card[]> ([]);
  racoes: Racao[] = [];


  constructor(private racaoService: RacaoService, 
              private carrinhoService: CarrinhoService,
              private snackBar: MatSnackBar,
              private route: Router,
              private router:ActivatedRoute
            )
               {}

 
               ngOnInit(): void {
                console.log('Entrando no ngOnInit do FiltroRacaoComponent');
                this.router.params.subscribe(params => {
                  console.log('Parâmetros da rota:', params);
                  const animalId = +params['id'] || 0;
                  console.log('animalId recebido:', animalId);
                  if (animalId) {
                    this.carregarRacoesPorAnimal(animalId);
                  } else {
                    this.carregarConsultas();
                  }
                });
              }
              

  PATH_USER = 'Users/Micro/quarkus/images/produto';

  carregarConsultas() {
    // buscando todos as consultas
    this.racaoService.getAllRacoes().subscribe(data => {
      this.racoes = data;
      this.carregarCards();
    });
  }

  carregarRacoesPorAnimal(animalId: number): void {
    this.racaoService.getRacoesByAnimal(animalId).subscribe(data => {
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
      //console.log('Conteúdo da raçao:', racao); // Adicione este log para verificar o conteúdo da raçao
      cards.push({
        idConsulta: racao.id,
        nome: racao.nome,
        preco: racao.preco,
        imagem: caminhoImagem
      });
    });
    //console.log('Conteúdo dos cards:', cards); // Adicione este log para verificar o conteúdo do array de cards
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
      frequencia: 0
    })

  }

  navegarParaProduto(id: number) {
    this.route.navigate(['/ver-produto', id]); 
  }

  showSnackbarTopPosition(content:any, action:any) {
    this.snackBar.open(content, action, {
      duration: 2000,
      verticalPosition: "top", // Allowed values are  'top' | 'bottom'
      horizontalPosition: "center" // Allowed values are 'start' | 'center' | 'end' | 'left' | 'right'
    });
  }
}