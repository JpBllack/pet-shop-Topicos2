import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Consulta } from '../models/consulta.model';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {
  private baseUrl = 'http://localhost:8080/consultas'; // Adapte conforme a sua URL de backend

  constructor(private httpClient: HttpClient) {}

  salvarConsulta(consulta: Consulta): Observable<Consulta> {
    return this.httpClient.post<Consulta>(this.baseUrl, consulta);
  }

  buscarConsultaPorId(id: number): Observable<Consulta> {
    return this.httpClient.get<Consulta>(`${this.baseUrl}/${id}`);
  }

  removerConsulta(id: number): Observable<any> {
    return this.httpClient.delete<any>(`${this.baseUrl}/${id}`);
  }

  listarTodasConsultas(): Observable<Consulta[]> {
    return this.httpClient.get<Consulta[]>(this.baseUrl);
  }

  // Outros métodos de serviço, como atualizar consulta, buscar por critérios específicos, etc.
}
