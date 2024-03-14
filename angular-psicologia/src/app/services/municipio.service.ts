import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { municipio } from '../models/municipio.model';

@Injectable({
  providedIn: 'root'
})
export class municipioService {
  private baseUrl = 'http://localhost:8080/municipios';

  constructor(private httpClient: HttpClient) {  }

  findAll(): Observable<municipio[]> {
    return this.httpClient.get<municipio[]>(this.baseUrl);
  }

  findById(id: string): Observable<municipio> {
    return this.httpClient.get<municipio>(`${this.baseUrl}/${id}`);
  }

  insert(municipio: municipio): Observable<municipio> {
    return this.httpClient.post<municipio>(this.baseUrl, municipio);
  }
  
  update(municipio: municipio): Observable<municipio> {
    const data =
    {
      nome: municipio.nome,
      idEstado : municipio.estado.id
    }

    return this.httpClient.put<municipio>(`${this.baseUrl}/${municipio.id}`, data);
  }

  delete(municipio: municipio): Observable<any> {
    return this.httpClient.delete<any>(`${this.baseUrl}/${municipio.id}`);
  }

}
