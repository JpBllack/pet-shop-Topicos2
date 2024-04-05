import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { usuario } from "../models/Usuario";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })
export class UsuarioService{
    private apiUrl= 'http//localhost:8080/users'

    constructor(private http: HttpClient){}

    getAllUsers(): Observable<usuario[]>{
        return this.http.get<usuario[]>(this.apiUrl);
    }
    getUserById(id: string): Observable<usuario>{
        const url = `${this.apiUrl}/${id}`;
        return this.http.get<usuario>(url);
    }

    getUserByName(nome: string): Observable<usuario>{
        const url = `${this.apiUrl}/${nome}`;
        return this.http.get<usuario>(url);
    }

    insertUser(usuario: usuario): Observable<usuario>{
        return this.http.post<usuario>(this.apiUrl, usuario);
    }

    updateUser(usuario: usuario): Observable<usuario>{
        const url = `${this.apiUrl}/${usuario.id}`;
        return this.http.put<usuario>(url, usuario);
    }

    deleteUser(id: number): Observable<void>{
        const url = `${this.apiUrl}/${id}`;
        return this.http.delete<void>(url);
    }
}