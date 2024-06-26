import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Racao } from '../models/racao.model';
import { catchError, map, Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RacaoService {
  private apiUrl = 'http://localhost:8080/racoes';

  constructor(private http: HttpClient) { }

  getAllRacoes(): Observable<Racao[]> {
    return this.http.get<Racao[]>(`${this.apiUrl}`);
  }

  getAllRacoesDisponiveis(): Observable<Racao[]> {
    return this.http.get<Racao[]>(`${this.apiUrl}`).pipe(
      map(racoes => racoes.filter(racao => racao.estoque > 0)));
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

  findByNome(nome: string): Observable<Racao[]> {
    console.log(nome);
    const url = `${this.apiUrl}/search/${nome}`;
    console.log(url);
    return this.http.get<Racao[]>(url).pipe(
      catchError(error => {
        console.error('Erro na requisição:', error);
        return of([]); 
      })
    );
  }
  
  uploadImage(formData: FormData): Observable<any> {
    const url = `${this.apiUrl}/upload/image`;
    return this.http.patch<any>(url, formData);
  }


  downloadImage(id: number): Observable<Blob> {
    const url = `${this.apiUrl}/download/image/${id}`;
    return this.http.get(url, { responseType: 'blob' });
  }

  GetImage(imageName: string): Observable<Blob> {
    const url = `http://localhost:8080/quarkus/images/produto/${imageName}`;
    return this.http.get(url, { responseType: 'blob' });
  }


  getUltimasRacoes(limit: number = 4): Observable<Racao[]> {
    return this.http.get<Racao[]>(`${this.apiUrl}?_limit=${limit}&_sort=id&_order=desc`).pipe(
      map(racoes => racoes.filter(racao => racao.estoque > 0)));
  }


  getRacoesByAnimal(animalId: number): Observable<Racao[]> {
    const url = `${this.apiUrl}/animal/${animalId}`;
    return this.http.get<Racao[]>(url).pipe(
      catchError(error => {
        console.error('Erro na requisição:', error);
        return of([]); 
      })
    );
  }

  getRacoesByAnimalDisponivel(animalId: number): Observable<Racao[]> {
    const url = `${this.apiUrl}/animal/${animalId}`;
    return this.http.get<Racao[]>(url).pipe(
      map(racoes => racoes.filter(racao => racao.estoque > 0)), // Filtra as rações com estoque disponível
      catchError(error => {
        console.error('Erro na requisição:', error);
        return of([]); 
      })
    );
  }

  getTotalRacoes(): Observable<number> {
    return this.http.get<Racao[]>(this.apiUrl).pipe(
      map(racoes => racoes.length),
      catchError(error => {
        console.error('Erro na requisição:', error);
        return of(0); 
      })
    );
  }

}



