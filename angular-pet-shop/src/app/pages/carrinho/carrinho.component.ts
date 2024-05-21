import { NgFor, NgIf, CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ItemCarrinho } from '../../models/itemcarrinho.model';
import { CarrinhoService } from '../../services/carrinho.service';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatOptionModule } from '@angular/material/core';
import { MatSelect } from '@angular/material/select';

@Component({
  selector: 'app-carrinho',
  standalone: true,
  imports: [NgFor, NgIf, CommonModule,MatFormField,MatOptionModule,MatSelect,MatLabel],
  templateUrl: './carrinho.component.html',
  styleUrl: './carrinho.component.css'
})
export class CarrinhoComponent implements OnInit {

  carrinhoItens: ItemCarrinho[] = [];

  constructor(private carrinhoService: CarrinhoService
  ) { }

  ngOnInit(): void {
    this.carrinhoService.carrinho$.subscribe( itens => {
      this.carrinhoItens = itens;
    })
  }

  removerItem(item: ItemCarrinho): void {
    this.carrinhoService.remover(item);
  }
  
  finalizarCompra(): void {

  }

  calcularTotal(): number {
    return this.carrinhoItens.reduce((total, item) => total + (item.preco * item.quantidade), 0);
  }

}