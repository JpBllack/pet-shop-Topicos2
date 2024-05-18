import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Estado } from '../models/estado.model';

@Injectable({
  providedIn: 'root'
})
export class EstadoService {
  private baseUrl = 'http://localhost:8080/estados';

  constructor(private httpClient: HttpClient) {}

  findAll(): Observable<Estado[]> {
    return this.httpClient.get<Estado[]>(`${this.baseUrl}`);
  }

  findById(id: string): Observable<Estado> {
    return this.httpClient.get<Estado>(`${this.baseUrl}/${id}`);
  }

  createEstado(estado: Estado): Observable<Estado> {
    return this.httpClient.post<Estado>(`${this.baseUrl}/insert`, estado);
  }

  
  updateEstado(estado: Estado): Observable<Estado> {
    const url = `${this.baseUrl}/update/${estado.id}`;
    return this.httpClient.put<Estado>(url, estado);
}

deleteEstado(id: number): Observable<void> {
  const url = (`${this.baseUrl}/delete/${id}`);
  return this.httpClient.delete<void>(url);
}
}
