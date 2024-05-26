import { Component, OnInit } from '@angular/core';
import { Compra, CompraService } from '../../services/compra.service';
import { ItemCarrinho } from '../../models/itemcarrinho.model';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-compras-usuario',
  standalone: true,
  templateUrl: './meus-pedidos.component.html',
  styleUrls: ['./meus-pedidos.component.css'],
  imports: [CommonModule, RouterModule],
})
export class MeusPedidosComponent implements OnInit {
  compras: Compra[] = [];

  constructor(private compraService: CompraService, private router: Router) {}

  ngOnInit(): void {
    this.loadComprasByUserId();
  }

  loadComprasByUserId(): void {
    this.compraService.getComprasByUserId().subscribe(
      (compras: Compra[]) => {
        this.compras = compras;
      },
      (error) => {
        console.error('Erro ao carregar compras:', error);
      }
    );
  }

  abrirDetalhesCompra(compra: Compra): void {
    const compraId = compra.id;
    this.router.navigate(['/detalhes-pedido', compraId]); // Redireciona para a rota de detalhes do pedido
  }
}
