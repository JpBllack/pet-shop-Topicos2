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

  insertPets(petData: any): Observable<Usuario[]> {
    return this.http.post<Usuario[]>(`${this.apiUrl}/insert/pet`, petData, { headers: this.getHeaders() });
  }
  
  insertEndereco(endereco: any): Observable<Usuario[]> {
    // Imprime os dados do endereço para o console antes de fazer a solicitação HTTP
    console.log('Dados do endereço:', endereco);

    // Faz a solicitação HTTP e retorna o Observable
    return this.http.post<Usuario[]>(`${this.apiUrl}/insert/endereco`, endereco, { headers: this.getHeaders() });
  }
  
  getPetsUsuario(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.apiUrl}/search/pet`, { headers: this.getHeaders() });
  }

  insertCartao(cartao: Cartao): Observable<Cartao>{
    return this.http.post<Cartao>(`${this.apiUrl}/insert/cartao`, cartao);
  }

  updateCartao(cartao: Cartao): Observable<Cartao>{
    return this.http.put<Cartao>(`${this.apiUrl}/update/cartao`, cartao);
  }

  deleteCartao(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/delete/cartao/${id}`)
  }

}

export interface Cartao{
  numero: string;
  codigoSeguranca: string;
  mesValidade: number;
  anovalidade: number;
}
