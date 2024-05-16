import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Usuario } from "../models/Usuario";

@Injectable({
    providedIn: 'root'
})
export class UsuarioLogadoService{
    private apiUrl = 'http://localhost:8080/usuariologado';

    constructor(private http: HttpClient) {}

    getUsuario(): Observable<Usuario>{
        return this.http.get<Usuario>(`${this.apiUrl}/user`);
    }

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