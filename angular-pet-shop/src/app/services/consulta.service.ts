import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { Consulta } from '../models/consulta.model';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {
  private baseUrl = 'http://localhost:8080/consultas';

  constructor(private httpClient: HttpClient) {}

  listarTodasConsultas(): Observable<Consulta[]> {
    return this.httpClient.get<Consulta[]>(this.baseUrl);
  }

  buscarConsultaPorId(id: number): Observable<Consulta> {
    return this.httpClient.get<Consulta>(`${this.baseUrl}/search/${id}`);
  }

  atualizarConsulta(id: number, consulta: Consulta): Observable<Consulta> {
    return this.httpClient.put<Consulta>(`${this.baseUrl}/atualizar/${id}`, consulta);
  }

  criarConsulta(consulta: any): Observable<any> {
    const url = `${this.baseUrl}/create`;
    return this.httpClient.post<Consulta>(url, consulta);
  }

  excluirConsulta(id: number): Observable<any> {
    const url = `${this.baseUrl}/delete/${id}`; // ajuste para o caminho de exclusão correto
    return this.httpClient.delete<any>(url);
  }
}
