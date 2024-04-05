import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Usuario } from "../models/Usuario";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })
export class UsuarioService{
    private apiUrl= 'http//localhost:8080/users'

    constructor(private http: HttpClient){}

    getAllUsers(): Observable<Usuario[]>{
        return this.http.get<Usuario[]>(this.apiUrl);
    }
    getUserById(id: string): Observable<Usuario>{
        const url = `${this.apiUrl}/${id}`;
        return this.http.get<Usuario>(url);
    }

    getUserByName(nome: string): Observable<Usuario>{
        const url = `${this.apiUrl}/${nome}`;
        return this.http.get<Usuario>(url);
    }

    insertUser(usuario: Usuario): Observable<Usuario>{
        return this.http.post<Usuario>(this.apiUrl, usuario);
    }

    updateUser(usuario: Usuario): Observable<Usuario>{
        const url = `${this.apiUrl}/${usuario.id}`;
        return this.http.put<Usuario>(url, usuario);
    }

    deleteUser(id: number): Observable<void>{
        const url = `${this.apiUrl}/${id}`;
        return this.http.delete<void>(url);
    }
}