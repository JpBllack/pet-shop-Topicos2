import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Racao } from '../../../models/racao.model';


@Injectable({
  providedIn: 'root'
})
export class RacaoService {
  private apiUrl = 'http://localhost:8080/racoes'; // URL da sua API de racoes

  constructor(private http: HttpClient) { }

  // Método para obter todas as racoes do backend
  getAllRacoes(): Observable<Racao[]> {
    return this.http.get<Racao[]>(this.apiUrl);
  }

  // Método para obter uma racao pelo seu ID
  getRacaoById(id: number): Observable<Racao> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Racao>(url);
  }

  // Método para criar uma nova racao no backend
  createRacao(racao: Racao): Observable<Racao> {
    return this.http.post<Racao>(this.apiUrl, racao);
  }

  // Método para atualizar uma racao existente no backend
  updateRacao(racao: Racao): Observable<Racao> {
    const url = `${this.apiUrl}/${racao.id}`;
    return this.http.put<Racao>(url, racao);
  }

  // Método para excluir uma racao do backend pelo seu ID
  deleteRacao(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
export { Racao };

