import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Usuario } from "../models/Usuario";
import { AuthService } from "./auth.service";

@Injectable({
    providedIn: 'root'
})
export class UsuarioLogadoService{
    private apiUrl = 'http://localhost:8080/usuariologado';

    constructor(private http: HttpClient, private authService: AuthService) {}

    updateCpf(cpf: String): Observable <Usuario>{
        return this.http.patch<Usuario>(`${this.apiUrl}/update/cpf`, cpf);
    }

    updateNome(usuario: Usuario): Observable <Usuario>{
        return this.http.patch<Usuario>(`${this.apiUrl}/update/nome`, usuario);
    }
    
    updateUsername(usuario: Usuario): Observable <Usuario>{
        return this.http.patch<Usuario>(`${this.apiUrl}/update/username`, usuario);
    }

    updateEmail(usuario: Usuario): Observable <Usuario>{
        return this.http.patch<Usuario>(`${this.apiUrl}/update/email`, usuario);
    }

    updateSenha(usuario: Usuario): Observable <Usuario>{
        return this.http.patch<Usuario>(`${this.apiUrl}/update/senha`, usuario);
    }

}