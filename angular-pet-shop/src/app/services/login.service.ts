import { HttpClient } from "@angular/common/http";
import { Inject, Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { tap } from "rxjs/operators";
import { Login } from "../models/login";
import { Usuario } from "../models/Usuario";
import { LocalStorageService } from "./localStorage.service";
import { JwtHelperService } from '@auth0/angular-jwt';
import { UsuarioService } from "./usuario.service";

@Injectable({
    providedIn: 'root'
})
export class LoginService {
    private apiUrl = 'http://localhost:8080/auth';
    private tokenKey = 'jwt_token';
    private usuarioLogadoKey = 'usuario_logado';
    private usuarioLogadoSubject = new BehaviorSubject<Usuario | null>(null);

    constructor(private http: HttpClient,
        @Inject(LocalStorageService) private localStorageService: LocalStorageService,
        private jwtHelper: JwtHelperService,
        private usuarioService: UsuarioService
    ) { }

    login(login: Login): Observable<any> {
        const url = `${this.apiUrl}/login`;
        return this.http.post<any>(url, login, { observe: 'response' }).pipe(
            tap((res: any) => {
                const authToken = res.headers.get('Authorization') ?? '';
                if (authToken) {
                  this.setToken(authToken);
                  const usuarioLogado = res.body;
                  console.log(usuarioLogado);
                  if (usuarioLogado) {
                    this.setUsuarioLogado(usuarioLogado);
                    this.usuarioLogadoSubject.next(usuarioLogado);
                  }
                }
              })
        );
    }

    private initUsuarioLogado() {
    const usuario = localStorage.getItem(this.usuarioLogadoKey);
    if (usuario) {
      const usuarioLogado = JSON.parse(usuario);

      this.setUsuarioLogado(usuarioLogado);
      this.usuarioLogadoSubject.next(usuarioLogado);
    }
  }

    setUsuarioLogado(usuario: Usuario): void {

        this.localStorageService.setItem(this.usuarioLogadoKey, usuario);
      }
    
      updateUsuarioLogado(usuario: any): void {
    
        this.localStorageService.setItem(this.usuarioLogadoKey, usuario);
        this.usuarioLogadoSubject.next(usuario);
      }
    
      setToken(token: string): void {
        this.localStorageService.setItem(this.tokenKey, token);
      }
    
      getUsuarioLogado() {
        return this.usuarioLogadoSubject.asObservable();
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
    
      isTokenExpired(): boolean {
        const token = this.getToken();
        return !token || this.jwtHelper.isTokenExpired(token);
      }
}
