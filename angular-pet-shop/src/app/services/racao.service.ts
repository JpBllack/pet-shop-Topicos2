import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Racao } from '../models/racao.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RacaoService {
  private apiUrl = 'http://localhost:8080/racoes';

  constructor(private http: HttpClient) { }

  getAllRacoes(): Observable<Racao[]> {
    return this.http.get<Racao[]>(`${this.apiUrl}/all`);
  }

  getRacaoById(id: number): Observable<Racao> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Racao>(url);
  }

  createRacao(racao: Racao): Observable<Racao> {
    return this.http.post<Racao>(`${this.apiUrl}/insert`, racao);
  }

  updateRacao(racao: Racao): Observable<Racao> {
    const url = `${this.apiUrl}/update/${racao.id}`;
    return this.http.put<Racao>(url, racao);
}


  deleteRacao(id: number): Observable<void> {
    const url = `${this.apiUrl}/delete/${id}`;
    return this.http.delete<void>(url);
  }

    findById(id: string): Observable<Racao> {
    return this.http.get<Racao>(`${this.apiUrl}/${id}`);
  }

  
  changeImage(id: number, imageFile: File): Observable<Racao> {
    const url = `${this.apiUrl}/upload/image/`;
    const formData = new FormData();
    formData.append('id', id.toString());
    formData.append('imagem', imageFile);
    return this.http.patch<Racao>(url, formData);
  }

  uploadImage(formData: FormData): Observable<any> {
    const url = `${this.apiUrl}/upload/image`;
    return this.http.patch<any>(url, formData);
}


  downloadImage(id: number): Observable<Blob> {
    const url = `${this.apiUrl}/download/image/${id}`;
    return this.http.get(url, { responseType: 'blob' });
  }
}
