import { HttpClient } from "@angular/common/http";
import { Usuario } from "../models/Usuario";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class UsuarioService {
    private apiUrl = 'http://localhost:8080/users'; // Corrigido: adição dos dois pontos após "http"

    constructor(private http: HttpClient) {}

    getAllUsers(): Observable<Usuario[]> {
        return this.http.get<Usuario[]>(this.apiUrl);
    }

    getUserById(id: string): Observable<Usuario> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.get<Usuario>(url);
    }

    getUserByName(nome: string): Observable<Usuario> {
        const url = `${this.apiUrl}/search/name/${nome}`; // Corrigido: caminho para busca por nome
        return this.http.get<Usuario>(url);
    }

    insertUser(usuario: Usuario): Observable<Usuario> {
        const url = `${this.apiUrl}/insert/user`; // Corrigido: caminho para inserção de usuário
        return this.http.post<Usuario>(url, usuario);
    }

    updateUser(usuario: Usuario): Observable<Usuario> {
        const url = `${this.apiUrl}/update/user/${usuario.id}`; // Corrigido: caminho para atualização de usuário
        return this.http.put<Usuario>(url, usuario);
    }

    deleteUser(id: number): Observable<void> {
        const url = `${this.apiUrl}/delete/user/${id}`; // Corrigido: caminho para exclusão de usuário
        return this.http.delete<void>(url);
    }

    getAllVeterinarios(): Observable<Usuario[]> {
        return this.http.get<Usuario[]>(`${this.apiUrl}/search/veterinarios`);
    }
}
