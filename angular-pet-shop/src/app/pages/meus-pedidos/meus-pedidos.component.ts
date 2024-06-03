import { Component, OnInit } from '@angular/core';
import { CompraService } from '../../services/compra.service';

import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { Status, StatusLabel } from '../../models/status';
import { Compra } from '../../models/compra';
import { ItemCompra } from '../../models/itemCompra';
import { StatusCompra } from '../../models/statusCompra';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';
import { Endereco } from '../../models/endereco';
import { Cartao } from '../../models/cartao';

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

  constructor(private compraService: CompraService, private router: Router, private usuarioLogadoService: UsuarioLogadoService) { }

  ngOnInit(): void {
    this.loadComprasByUserId();
  }

  loadComprasByUserId(): void {
    this.compraService.getComprasByUserId().subscribe(
      (compras: Compra[]) => {
        this.compras = compras;
        this.compras.forEach(compra => {
          this.loadItensCompra(compra);
          this.loadEnderecoEntrega(compra);
          this.loadCartaoCredito(compra);
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

  loadEnderecoEntrega(compra: Compra): void {
    this.usuarioLogadoService.getEnderecoUsuario().subscribe(
      (enderecos: Endereco[]) => {
        if (compra.endereco && compra.endereco.id) { // Verifica se o endereço já está definido
          const enderecoEntrega = enderecos.find(endereco => endereco.id === compra.endereco.id);
          if (enderecoEntrega) {
            compra.endereco = enderecoEntrega;
          } else {
            console.error(`Endereço de entrega não encontrado para a compra ${compra.id}`);
          }
        }
      },
      (error) => {
        console.error('Erro ao carregar endereço de entrega:', error);
      }
    );
  }
  
  loadCartaoCredito(compra: Compra): void {
    if (compra.cartao) {
      this.usuarioLogadoService.getCartaoById(compra.cartao.id).subscribe(
        (cartao: Cartao) => {
          // Faça algo com o cartão retornado
        },
        (error) => {
          console.error(`Erro ao carregar cartão de crédito:`, error);
        }
      );
    } else {
      console.error(`Cartão de crédito não definido para a compra ${compra.id}`);
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
