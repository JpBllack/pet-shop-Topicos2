import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Marca } from "../models/marca";

@Injectable({
    providedIn: 'root'
})
export class MarcaService{
    private baseUrl = 'http://localhost:8080/marcas';

    constructor(private httpClient: HttpClient){}

    findAll(): Observable<Marca[]> {
        return this.httpClient.get<Marca[]>(`${this.baseUrl}`);
    }

    findById(id: string): Observable<Marca>{
        return this.httpClient.get<Marca>(`${this.baseUrl}/${id}`);
    }

    insert(marca: Marca): Observable<Marca> {
        return this.httpClient.post<Marca>(`${this.baseUrl}/insert`, marca);
    }

    update(marca: Marca): Observable<Marca> {
        return this.httpClient.put<Marca>(`${this.baseUrl}/update/${marca.id}`, marca);
    }

    delete(id: number): Observable<void>{
        return this.httpClient.delete<void>(`${this.baseUrl}/delete/${id}`);
    }
}