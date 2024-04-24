import { HttpClient } from "@angular/common/http";

import { Injectable } from "@angular/core";
import { TipoAnimal } from "../models/tipoAnimal";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class TipoAnimalService {
    private baseUrl = 'http://localhost:8080/tipos';

    constructor(private httpClient: HttpClient) {}

    findAll(): Observable<TipoAnimal[]> {
        return this.httpClient.get<TipoAnimal[]>(this.baseUrl);
    }

    findById(id: string): Observable<TipoAnimal> {
        return this.httpClient.get<TipoAnimal>(`${this.baseUrl}/${id}`);
    }

    insert(tipoAnimal: TipoAnimal): Observable<TipoAnimal> {
        return this.httpClient.post<TipoAnimal>(`${this.baseUrl}/insert`, tipoAnimal);
    }

    update(tipoAnimal: TipoAnimal): Observable<TipoAnimal> {
        return this.httpClient.put<TipoAnimal>(`${this.baseUrl}/update/${tipoAnimal.id}`, tipoAnimal);
    }

    delete(id: number): Observable<void> {
        return this.httpClient.delete<void>(`${this.baseUrl}/delete/${id}`);
    }
}
