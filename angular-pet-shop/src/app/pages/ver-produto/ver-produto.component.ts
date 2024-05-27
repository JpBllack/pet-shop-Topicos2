import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RacaoService } from '../../services/racao.service';
import { Racao } from '../../models/racao.model';
import { CarrinhoService } from '../../services/carrinho.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ItemCarrinho } from '../../models/itemcarrinho.model';
import { FooterComponent } from '../template/footer/footer.component';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-ver-produto',
  standalone: true,
  imports: [FormsModule, CommonModule, FooterComponent,MatIcon,MatIconModule],
  templateUrl: './ver-produto.component.html',
  styleUrls: ['./ver-produto.component.css']
})
export class VerProdutoComponent implements OnInit {
  produto: Racao | undefined;
  quantidade: number = 1; // Default to 1

  constructor(
    private route: ActivatedRoute,
    private racaoService: RacaoService,
    private carrinhoService: CarrinhoService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.racaoService.getRacaoById(+id).subscribe((data: Racao) => {
        this.produto = data;
      });
    }
  }

  getImagemPath(imagem: string): string {
    return `http://localhost:8080/quarkus/images/produto/${imagem}`;
  }

  adicionarAoCarrinho() {
    if (this.produto && this.quantidade > 0) {
      const itemCarrinho: ItemCarrinho = {
        id: this.produto.id,
        nome: this.produto.nome,
        imagem: this.getImagemPath(this.produto.imagem),
        preco: this.produto.preco,
        quantidade: this.quantidade,
        frequencia: 0 // ou outro valor padrão para a frequência
      };
      this.carrinhoService.adicionar(itemCarrinho);
      this.snackBar.open('Produto adicionado ao carrinho!', 'Fechar', {
        duration: 2000,
        verticalPosition: 'top',
        horizontalPosition: 'center'
      });
    } else {
      this.snackBar.open('A quantidade deve ser maior que zero', 'Fechar', {
        duration: 2000,
        verticalPosition: 'top',
        horizontalPosition: 'center'
      });
    }
  }

  aumentarQuantidade() {
    this.quantidade += 1;
  }

  diminuirQuantidade() {
    if (this.quantidade > 1) {
      this.quantidade -= 1;
    }
  }
}
