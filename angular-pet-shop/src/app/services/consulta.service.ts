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

  salvarConsulta(consulta: Consulta): Observable<Consulta> {
    return this.httpClient.post<Consulta>(this.baseUrl, consulta);
  }

  buscarConsultaPorId(id: number): Observable<Consulta> {
    return this.httpClient.get<Consulta>(`${this.baseUrl}/${id}`);
  }

  removerConsulta(id: number): Observable<any> {
    const url = `${this.baseUrl}/delete/${id}`; // ajuste para o caminho de exclusão correto
    return this.httpClient.delete<any>(url).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 404) {
          return throwError('Consulta não encontrada para exclusão');
        }
        return throwError('Erro ao excluir consulta');
      })
    );
  }

  listarTodasConsultas(): Observable<Consulta[]> {
    return this.httpClient.get<Consulta[]>(this.baseUrl);
  }

  atualizarConsulta(id: number, consulta: Consulta): Observable<Consulta> {
    return this.httpClient.put<Consulta>(`${this.baseUrl}/${id}`, consulta);
  }

  criarConsulta(consultaData: any): Observable<any> {
    return this.httpClient.post<any>(this.baseUrl, consultaData);
  }

  excluirConsulta(id: number): Observable<any> {
    const url = `${this.baseUrl}/delete/${id}`; // ajuste para o caminho de exclusão correto
    return this.httpClient.delete<any>(url);
  }
}
