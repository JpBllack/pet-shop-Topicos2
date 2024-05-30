import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Endereco } from '../models/endereco';

@Injectable({
  providedIn: 'root'
})
export class EnderecoService {
  private apiUrl = 'http://localhost:8080/enderecos';

  constructor(private http: HttpClient) {}

  getEnderecos(): Observable<Endereco[]> {
    return this.http.get<Endereco[]>(this.apiUrl).pipe(
      catchError(this.handleError)
    );
  }

  getEnderecoById(id: number): Observable<Endereco> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Endereco>(url).pipe(
      catchError(this.handleError)
    );
  }

  addEndereco(endereco: Endereco, userId: number): Observable<Endereco> {
    const params = new HttpParams().set('userId', userId.toString());
    const url = `${this.apiUrl}/insert`;
    return this.http.post<Endereco>(url, endereco, { params }).pipe(
      catchError(this.handleError)
    );
  }

  updateEndereco(id: number, endereco: Partial<Endereco>): Observable<void> {
    const url = `${this.apiUrl}/update/${id}`;
    return this.http.put<void>(url, endereco).pipe(
      catchError(this.handleError)
    );
  }

  deleteEndereco(id: number): Observable<void> {
    const url = `${this.apiUrl}/delete/${id}`;
    return this.http.delete<void>(url).pipe(
      catchError(this.handleError)
    );
  }

  marcarComoPrincipal(id: number): Observable<void> {
    const url = `${this.apiUrl}/update/principal/${id}`;
    return this.http.patch<void>(url, {}).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: any): Observable<never> {
    console.error('An error occurred', error);
    return throwError(error.message || 'Server error');
  }
}
