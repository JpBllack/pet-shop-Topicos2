import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { tap } from "rxjs/operators";
import { Login } from "../models/login";

@Injectable({
    providedIn: 'root'
})
export class LoginService {
    private apiUrl = 'http://localhost:8080/auth';
    private jwtTokenKey = 'jwt_token';

    constructor(private http: HttpClient) { }

    login(login: Login): Observable<any> {
        const url = `${this.apiUrl}/login`;
        return this.http.post<any>(url, login).pipe(
            tap(response => {
                if (response && response.token) {
                    // Armazenar o token JWT no armazenamento local
                    localStorage.setItem(this.jwtTokenKey, response.token);
                }
            })
        );
    }

    getToken(): string | null {
        // Recuperar o token JWT do armazenamento local
        return localStorage.getItem(this.jwtTokenKey);
    }

    isLoggedIn(): boolean {
        // Lógica para verificar se o usuário está autenticado
        // Por exemplo, verificar se há um token JWT armazenado no localStorage
        const token = localStorage.getItem('jwt_token');
        return token !== null; // Retorna true se houver um token, caso contrário, retorna false
      }

      logout(): void {
        // Lógica para fazer logout
        // Por exemplo, remover o token JWT do localStorage
        localStorage.removeItem('jwt_token');
      }
}
