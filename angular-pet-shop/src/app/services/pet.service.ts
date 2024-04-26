import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { pet } from '../models/pet';

@Injectable({
    providedIn: 'root'
})
export class petService {
    private baseUrl = 'http://localhost:8080/pets';

    constructor(private http: HttpClient) {}

    // Retorna todos os pets
    getAllpets(): Observable<pet[]> {
        return this.http.get<pet[]>(`${this.baseUrl}/all`);
    }

    // Encontra um pet pelo ID
    getpetById(id: string): Observable<pet> {
        return this.http.get<pet>(`${this.baseUrl}/byId/${id}`);
    }

    // Insere um novo pet
    createpet(pet: pet): Observable<pet> {
        return this.http.post<pet>(`${this.baseUrl}/create`, pet);
    }

    // Atualiza um pet existente pelo ID
    updatepet(pet: pet): Observable<pet> {
        return this.http.put<pet>(`${this.baseUrl}/update/${pet.id}`, pet);
    }

    // Deleta um pet pelo ID
    deletepetById(id: number): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
    }
}
