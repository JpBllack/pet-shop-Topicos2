import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { municipio } from '../models/municipio';

@Injectable({
  providedIn: 'root'
})
export class municipioService {
  private baseUrl = 'http://localhost:8080/municipios';

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<municipio[]> {
    return this.httpClient.get<municipio[]>(`${this.baseUrl}`);
  }

  findById(id: string): Observable<municipio> {
    return this.httpClient.get<municipio>(`${this.baseUrl}/${id}`);
  }

  insertMunicipio(municipio: municipio): Observable<municipio> {
    return this.httpClient.post<municipio>(`${this.baseUrl}/insert`, municipio);
  }

  updateMunicipio(municipio: municipio): Observable<municipio> {
    const url = `${this.baseUrl}/update/${municipio.id}`;
    return this.httpClient.put<municipio>(url, municipio);
  }

  // Adicione o método findByEstadoId
  findByEstadoId(estadoId: number): Observable<municipio[]> {
    return this.httpClient.get<municipio[]>(`${this.baseUrl}/search/estado/${estadoId}`);
  }


  deleteMunicipio(id: number): Observable<void> {
    const url = `${this.baseUrl}/delete/${id}`;
    return this.httpClient.delete<void>(url);
  }
}
