import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { Login } from "../models/login";
import { JwtHelperService } from "@auth0/angular-jwt";
import { Usuario } from "../models/Usuario";
import { LocalStorageService } from "./local-storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseURL: string = 'http://localhost:8080/auth';
  private tokenKey = 'jwt_token';
  private usuarioLogadoKey = 'usuario_logado';
  private usuarioLogadoSubject = new BehaviorSubject<Usuario | null>(null);

  constructor(private http: HttpClient, 
              private localStorageService: LocalStorageService, 
              private jwtHelper: JwtHelperService) {

    this.initUsuarioLogado();

  }

  private initUsuarioLogado() {
    const usuario = localStorage.getItem(this.usuarioLogadoKey);
    if (usuario) {
      const usuarioLogado = JSON.parse(usuario);

      this.setUsuarioLogado(usuarioLogado);
      this.usuarioLogadoSubject.next(usuarioLogado);
    }
  }


  login(loginData: Login): Observable<any> {

    return this.http.post(`${this.baseURL}`, loginData, {observe: 'response'}).pipe(
      tap((res: any) => {
        const authToken = res.headers.get('Authorization') ?? '';
        if (authToken) {
          this.setToken(authToken);
          const usuarioLogado = res.body;
          console.log('usuario logado: ', usuarioLogado);
          if (usuarioLogado) {
            this.setUsuarioLogado(usuarioLogado);
            this.usuarioLogadoSubject.next(usuarioLogado);
          }
        }
      })
    );
  }


  setUsuarioLogado(usuario: Usuario): void {
    this.localStorageService.setItem(this.usuarioLogadoKey, usuario);
  }

  setToken(token: string): void {
    console.log('Token definido:', token);
    this.localStorageService.setItem(this.tokenKey, token);
  }

  getUsuarioLogado() {
    return this.usuarioLogadoSubject.asObservable();
  }

  getUsuarioLogadoAsValue(): Usuario | null{
    return this.usuarioLogadoSubject.getValue();
  }

  getToken(): string | null {
    return this.localStorageService.getItem(this.tokenKey);
  }

  removeToken(): void {
    this.localStorageService.removeItem(this.tokenKey);
  }

  removeUsuarioLogado(): void {
    this.localStorageService.removeItem(this.usuarioLogadoKey);
    this.usuarioLogadoSubject.next(null);
  }

  /*
  isTokenExpired(): boolean {
    const token = this.getToken();
    return !token || this.jwtHelper.isTokenExpired(token);
  }
  */

  isTokenExpired(): boolean {
    const token = this.getToken();
    console.error('token: ' + token);
    if (!token) {
      return true;
    }
    
    try {
      console.error('jwtHelper: ' + this.jwtHelper.isTokenExpired(token));
      return this.jwtHelper.isTokenExpired(token);
    } catch (error) {
      console.error('Token inválido:', error);
      return true; 
    }
  }

  updateUsuarioLogado(novoUsuario: Usuario): void {
    this.setUsuarioLogado(novoUsuario);
    this.usuarioLogadoSubject.next(novoUsuario);
  }

  updateToken(novoToken: string): void {
    this.setToken(novoToken);
  }
}