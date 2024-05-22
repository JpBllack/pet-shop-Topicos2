import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { ItemCarrinho } from '../models/itemcarrinho.model';

@Injectable({
  providedIn: 'root'
})
export class CarrinhoService {

  private carrinhoSubject = new BehaviorSubject<ItemCarrinho[]>([]);
  carrinho$ = this.carrinhoSubject.asObservable();

  constructor(private localStorageService: LocalStorageService) {
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
}