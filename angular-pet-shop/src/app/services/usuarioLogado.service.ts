import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Usuario } from "../models/Usuario";
import { AuthService } from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class UsuarioLogadoService {
  private apiUrl = 'http://localhost:8080/usuarioLogado';

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders() {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }

  getUsuarioLogado(): Observable<Usuario> {
    return this.http.get<Usuario>(this.apiUrl, { headers: this.getHeaders() });
  }

  updateCpf(cpf: { cpf: string }): Observable<Usuario> {
    return this.http.patch<Usuario>(`${this.apiUrl}/update/cpf`, cpf, { headers: this.getHeaders() });
  }

  updateNome(nome: { nome: string }): Observable<Usuario> {
    return this.http.patch<Usuario>(`${this.apiUrl}/update/nome`, nome, { headers: this.getHeaders() });
  }

  updateUsername(username: { username: string }): Observable<Usuario> {
    return this.http.patch<Usuario>(`${this.apiUrl}/update/username`, username, { headers: this.getHeaders() });
  }

  updateEmail(email: { email: string }): Observable<Usuario> {
    return this.http.patch<Usuario>(`${this.apiUrl}/update/email`, email, { headers: this.getHeaders() });
  }

  updateSenha(senha: { senha: string }): Observable<Usuario> {
    return this.http.patch<Usuario>(`${this.apiUrl}/update/senha`, senha, { headers: this.getHeaders() });
  }
}
