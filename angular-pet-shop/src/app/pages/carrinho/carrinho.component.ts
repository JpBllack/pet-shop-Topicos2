import { NgFor, NgIf, CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ItemCarrinho } from '../../models/itemcarrinho.model';
import { CarrinhoService } from '../../services/carrinho.service';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatOptionModule } from '@angular/material/core';
import { MatSelect } from '@angular/material/select';
import { Router } from '@angular/router';
import { CompraService } from '../../services/compra.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RacaoService } from '../../services/racao.service'; // Importar RacaoService

@Component({
  selector: 'app-carrinho',
  standalone: true,
  imports: [NgFor, NgIf, CommonModule, MatFormField, MatOptionModule, MatSelect, MatLabel],
  templateUrl: './carrinho.component.html',
  styleUrls: ['./carrinho.component.css']
})
export class CarrinhoComponent implements OnInit {

  carrinhoItens: ItemCarrinho[] = [];

  constructor(
    private carrinhoService: CarrinhoService, 
    private route: Router, 
    private compraService: CompraService,
    private snackBar: MatSnackBar,
    private racaoService: RacaoService // Injetar RacaoService
  ) { }

  ngOnInit(): void {
    this.carrinhoService.carrinho$.subscribe(itens => {
      this.carrinhoItens = itens;
    });
  }

  getImagemPath(imagem: string): string {
    return `http://localhost:8080/quarkus/images/produto/${imagem}`;
  }

  removerItem(item: ItemCarrinho): void {
    this.carrinhoService.remover(item);
  }

  aumentarQuantidade(item: ItemCarrinho): void {
    this.racaoService.getRacaoById(item.id).subscribe(racao => {
      if (item.quantidade < racao.estoque) {
        item.quantidade += 1;
        this.carrinhoService.atualizarCarrinho(this.carrinhoItens);
      } else {
        this.snackBar.open(`Quantidade máxima do item ${item.nome} excedida. Estoque disponível: ${racao.estoque}.`, 'Fechar', {
          duration: 2000,
          verticalPosition: 'top',
          horizontalPosition: 'center'
        });
      }
    });
  }

  diminuirQuantidade(item: ItemCarrinho): void {
    if (item.quantidade > 1) {
      item.quantidade -= 1;
      this.carrinhoService.atualizarCarrinho(this.carrinhoItens);
    }
  }

  confirmarCompra(): void {
    this.route.navigate(['/confirmar-compra']);
  }
  
  finalizarCompra(): void {
    this.carrinhoService.concluirCompra(this.carrinhoItens);
  }

  navegarParaProduto(id: number) {
    this.route.navigate(['/ver-produto', id]); 
  }

  calcularTotal(): number {
    return this.carrinhoItens.reduce((total, item) => {
      if (item.preco !== null && item.quantidade !== null) {
        total += item.preco * item.quantidade;
      }
      return total;
    }, 0);
  }
}
