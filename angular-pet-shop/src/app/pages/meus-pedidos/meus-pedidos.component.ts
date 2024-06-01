import { Component, OnInit } from '@angular/core';
import { CompraService } from '../../services/compra.service';

import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { Status, StatusLabel } from '../../models/status';
import { Compra } from '../../models/compra';
import { ItemCompra } from '../../models/itemCompra';
import { StatusCompra } from '../../models/statusCompra';

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
        console.log(compra.itens)
        compra.itens = itensCompra;
      },
      (error) => {
        console.error(`Erro ao carregar itens da compra ${compra.id}:`, error);
      }
    );
  }

  getStatusLabel(statusCompra: StatusCompra[]): string {
    //console.log('Status Compra:', statusCompra);
    if (statusCompra && statusCompra.length > 0) {
      const ultimoStatus = statusCompra[statusCompra.length - 1].status;
      if (typeof ultimoStatus === 'string') {
        return ultimoStatus;
      } else if (typeof ultimoStatus === 'object' && 'label' in ultimoStatus) {
        return ultimoStatus;
      } else {
        return 'Status desconhecido';
      }
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
