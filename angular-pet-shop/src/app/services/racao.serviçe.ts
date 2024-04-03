import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Racao } from '../models/racao.model';

@Injectable({
  providedIn: 'root'
})
export class RacaoService {
  private apiUrl = 'http://localhost:8080/racoes';

  constructor(private http: HttpClient) { }

  getAllRacoes(): Observable<Racao[]> {
    return this.http.get<Racao[]>(this.apiUrl);
  }

  getRacaoById(id: number): Observable<Racao> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Racao>(url);
  }

  createRacao(racao: Racao): Observable<Racao> {
    return this.http.post<Racao>(this.apiUrl, racao);
  }

  updateRacao(racao: Racao): Observable<Racao> {
    const url = `${this.apiUrl}/${racao.id}`;
    return this.http.put<Racao>(url, racao);
  }

  deleteRacao(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
