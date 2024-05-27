import { Component, OnInit } from '@angular/core';
import { Compra, CompraService, ItemCompra, StatusCompra } from '../../services/compra.service';

import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { Status, StatusLabel } from '../../models/status';

@Component({
  selector: 'app-compras-usuario',
  standalone: true,
  templateUrl: './meus-pedidos.component.html',
  styleUrls: ['./meus-pedidos.component.css'],
  imports: [CommonModule, RouterModule],
})
export class MeusPedidosComponent implements OnInit {
  compras: Compra[] = [];
  itensExpandidos: { [key: number]: boolean } = {};

  constructor(private compraService: CompraService, private router: Router) { }

  ngOnInit(): void {
    this.loadComprasByUserId();
  }

  loadComprasByUserId(): void {
    this.compraService.getComprasByUserId().subscribe(
      (compras: Compra[]) => {
        this.compras = compras;
        console.log('Compras carregadas:', this.compras);
        // Para cada compra, carrega os itens
        this.compras.forEach(compra => {
          this.loadItensCompra(compra);
        });
      },
      (error) => {
        console.error('Erro ao carregar compras:', error);
      }
    );
  }

  // Método para carregar os itens de compra de uma compra específica
  loadItensCompra(compra: Compra): void {
    this.compraService.getItensByCompraId(compra.id).subscribe(
      (itensCompra: ItemCompra[]) => {
        // Mapeia os itens de compra para itens de carrinho
        const itensCarrinho = itensCompra.map(itemCompra => ({
          id: 0, // Preencha com o valor apropriado se houver um ID disponível
          nome: itemCompra.nome,
          preco: itemCompra.preco,
          quantidade: itemCompra.quantidade,
          imagem: '', // Preencha com a imagem apropriada, se estiver disponível
          frequencia: 0
        }));
        compra.itens = itensCarrinho;
      },
      (error) => {
        console.error(`Erro ao carregar itens da compra ${compra.id}:`, error);
      }
    );
  }

  getStatusLabel(statusCompra: StatusCompra[]): string {
    console.log('Status Compra:', statusCompra);
    if (statusCompra && statusCompra.length > 0) {
      const ultimoStatus = statusCompra[statusCompra.length - 1].status;
      return StatusLabel[ultimoStatus] || 'Status desconhecido';
    } else {
      return 'Status desconhecido';
    }
  }


  // Método para alternar entre expandir e recolher os itens
  toggleItens(compra: Compra): void {
    if (this.itensExpandidos[compra.id]) {
      this.itensExpandidos[compra.id] = false; // Recolher os itens
    } else {
      this.itensExpandidos[compra.id] = true; // Expandir os itens
    }
  }
}
