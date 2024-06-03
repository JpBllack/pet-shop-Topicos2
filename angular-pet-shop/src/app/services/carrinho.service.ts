import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { ItemCarrinho } from '../models/itemcarrinho.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {
  private apiUrl = 'http://localhost:8080/compra';

  private carrinhoSubject = new BehaviorSubject<ItemCarrinho[]>([]);
  carrinho$ = this.carrinhoSubject.asObservable();

  constructor(private localStorageService: LocalStorageService, private http: HttpClient) {
    const carrinhoArmazenado = localStorageService.getItem('carrinho') || [];
    this.carrinhoSubject.next(carrinhoArmazenado);
  }

  adicionar(consulta: ItemCarrinho): void {
    const carrinhoAtual = this.carrinhoSubject.value;
    const itemExistente = carrinhoAtual.find(item => item.id === consulta.id);

    if (itemExistente) {
      itemExistente.quantidade += consulta.quantidade || 1;
    } else {
      carrinhoAtual.push({ ...consulta });
    }

    this.carrinhoSubject.next(carrinhoAtual);
    this.atualizarArmazenamentoLocal();
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
    localStorage.setItem('carrinho', JSON.stringify(this.carrinhoSubject.value));
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
    localStorage.removeItem('carrinho');
  }

}