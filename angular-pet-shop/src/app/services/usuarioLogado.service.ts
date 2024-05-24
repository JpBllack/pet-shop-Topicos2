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
    return this.http.get<Usuario>(this.apiUrl);
  }

  updateCPF(cpf: { cpf: string }): Observable<Usuario> {
    return this.http.patch<Usuario>(`${this.apiUrl}/update/cpf`, cpf);
  }

  updateNome(nome: { nome: string }): Observable<Usuario> {
    return this.http.patch<Usuario>(`${this.apiUrl}/update/nome`, nome);
  }

  updateUsername(username: { username: string }): Observable<Usuario> {
    return this.http.patch<Usuario>(`${this.apiUrl}/update/username`, username);
  }

  updateEmail(email: { email: string }): Observable<Usuario> {
    return this.http.patch<Usuario>(`${this.apiUrl}/update/email`, email);
  }

  updateSenha(senhas: { senhaAtual: string, novaSenha: string }): Observable<Usuario> {
    return this.http.patch<Usuario>(`${this.apiUrl}/update/password`, senhas);
  }

  getPets(): Observable<Usuario[]> {
    return this.http.post<Usuario[]>(`${this.apiUrl}/pets`, { headers: this.getHeaders() });
  }
}
