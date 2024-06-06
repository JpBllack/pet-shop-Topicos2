import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { ItemCarrinho } from '../models/itemcarrinho.model';
import { HttpClient } from '@angular/common/http';
import { RacaoService } from './racao.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {
  private apiUrl = 'http://localhost:8080/compra';
  private carrinhoSubject = new BehaviorSubject<ItemCarrinho[]>([]);
  carrinho$ = this.carrinhoSubject.asObservable();

  constructor(
    private localStorageService: LocalStorageService,
    private http: HttpClient,
    private racaoService: RacaoService,
    private snackBar: MatSnackBar
  ) {
    const carrinhoArmazenado = this.localStorageService.getItem('carrinho') || [];
    this.carrinhoSubject.next(carrinhoArmazenado);
  }

  adicionar(item: ItemCarrinho): void {
    this.racaoService.getRacaoById(item.id).subscribe(racao => {
      const estoque = racao.estoque;
      const carrinhoAtual = this.carrinhoSubject.value;
      const itemExistente = carrinhoAtual.find(i => i.id === item.id);

      if (itemExistente) {
        if (estoque < itemExistente.quantidade + item.quantidade) {
          this.snackBar.open('Estoque insuficiente para adicionar mais deste produto: ' + item.nome, 'Fechar', {
            duration: 3000,
          });
          return;
        }
        itemExistente.quantidade += item.quantidade || 1;
      } else {
        if (estoque < item.quantidade) {
          this.snackBar.open('Estoque insuficiente para adicionar este produto: ' + item.nome, 'Fechar', {
            duration: 3000,
          });
          return;
        }
        carrinhoAtual.push({ ...item });
      }

      this.carrinhoSubject.next(carrinhoAtual);
      this.atualizarArmazenamentoLocal();
    });
  }

  removerTudo(): void {
    this.localStorageService.removeItem('carrinho');
    window.location.reload(); // reload na página
  }

  remover(item: ItemCarrinho): void {
    const carrinhoAtual = this.carrinhoSubject.value;
    const carrinhoAtualizado = carrinhoAtual.filter(itemCarrinho => itemCarrinho !== item);

    this.carrinhoSubject.next(carrinhoAtualizado);
    this.atualizarArmazenamentoLocal();
  }

  obter(): ItemCarrinho[] {
    return this.carrinhoSubject.value;
  }

  atualizarCarrinho(itens: ItemCarrinho[]): void {
    this.carrinhoSubject.next(itens);
  }

  private atualizarArmazenamentoLocal(): void {
    this.localStorageService.setItem('carrinho', JSON.stringify(this.carrinhoSubject.value));
  }

  concluirCompra(itensCarrinho: ItemCarrinho[]): void {
    const itensCompra = itensCarrinho.map(itemCarrinho => ({
      nome: itemCarrinho.nome,
      precoUnitario: itemCarrinho.preco,
      quantidade: itemCarrinho.quantidade
    }));

    this.http.post(`${this.apiUrl}/concluir`, itensCompra).subscribe(
      () => {
        this.limparCarrinho();
      },
      (error) => {
        console.error('Erro ao concluir compra:', error);
      }
    );
  }
  
  limparCarrinho(): void {
    this.carrinhoSubject.next([]);
    this.localStorageService.removeItem('carrinho');
  }
}
