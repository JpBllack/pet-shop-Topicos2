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

    logout(): void {
        // Remover o token JWT do armazenamento local ao fazer logout
        localStorage.removeItem(this.jwtTokenKey);
    }
}
