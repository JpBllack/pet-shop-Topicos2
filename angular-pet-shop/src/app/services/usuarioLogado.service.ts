import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Usuario } from "../models/Usuario";
import { AuthService } from "./auth.service";
import { Endereco } from "../models/endereco";
import { Cartao } from "../models/cartao";


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

  //Endereco
  

  insertEndereco(endereco: any): Observable<Usuario[]> {
    console.log('Dados do endereço:', endereco);
    return this.http.post<Usuario[]>(`${this.apiUrl}/insert/endereco`, endereco, { headers: this.getHeaders() });
  }

  updateEndereco(endereco: Endereco): Observable<Endereco>{
    return this.http.put<Endereco>(`${this.apiUrl}/update/endereco`, endereco);
  }  
  deleteEndereco(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/delete/endereco/${id}`)
  }

  getEnderecoUsuario(): Observable<Endereco[]>{
    return this.http.get<Endereco[]>(`${this.apiUrl}/search/endereco`);
  }

  setEnderecoPrincipal(id: number): Observable<Endereco>{
    return this.http.patch<Endereco>(`${this.apiUrl}/update/endereco/principal/${id}`, {}, { headers: this.getHeaders() });
  }
  

  //Pet

  getPetsUsuario(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.apiUrl}/search/pet`, { headers: this.getHeaders() });
  }

  insertPets(petData: any): Observable<Usuario[]> {
    return this.http.post<Usuario[]>(`${this.apiUrl}/insert/pet`, petData, { headers: this.getHeaders() });
  }



  // Cartao

  getCartaoById(id: number): Observable<Cartao>{
    return this.http.get<Cartao>(`${this.apiUrl}/search/cartao/${id}`);
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

  getCartoesUsuario(): Observable<Cartao[]>{
    return this.http.get<Cartao[]>(`${this.apiUrl}/search/cartao`);
  }

  setCartaoPrincipal(id: number): Observable<Cartao>{
    return this.http.patch<Cartao>(`${this.apiUrl}/update/cartao/principal/${id}`, {}, { headers: this.getHeaders() });
  }

  // Upload Imagem
uploadImage(formData: FormData): Observable<any> {
  console.log("O método uploadImage foi utilizado. Dados recebidos:", formData); // Adiciona o log aqui
  const headers = this.getHeaders();
  return this.http.patch(`${this.apiUrl}/upload/image`, formData, { headers });
}


// Método para obter a imagem do usuário
getImagemUsuario(imageName: string): Observable<Blob> {
  const headers = this.getHeaders();
  const apiUrl = 'http://localhost:8080'; // ou a URL do seu backend
  return this.http.get(`${apiUrl}/quarkus/images/usuario/${imageName}`, {
    headers,
    responseType: 'blob'
  });
  

}


}
