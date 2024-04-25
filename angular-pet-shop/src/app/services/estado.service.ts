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
    return this.httpClient.get<Estado[]>(`${this.baseUrl}/all`);
  }

  findById(id: string): Observable<Estado> {
    return this.httpClient.get<Estado>(`${this.baseUrl}/${id}`);
  }

  insert(estado: Estado): Observable<Estado> {
    return this.httpClient.post<Estado>(`${this.baseUrl}/insert`, estado);
  }

  update(estado: Estado): Observable<Estado> {
    return this.httpClient.put<Estado>(`${this.baseUrl}/${estado.id}`, estado);
  }

  delete(id: number): Observable<Estado> {
    return this.httpClient.get<Estado>(`${this.baseUrl}/delete/${id}`);
  }
}
