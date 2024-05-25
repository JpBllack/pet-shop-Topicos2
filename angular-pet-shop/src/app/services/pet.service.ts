import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { pet } from "../models/pet";
import { Injectable } from "@angular/core";



@Injectable({
    providedIn: 'root'
  })
export class PetService{

    private baseUrl = 'http://localhost:8080/pets';

    constructor(private httpClient: HttpClient){}

    findAllPet(): Observable<pet[]>{
        return this.httpClient.get<pet[]>(`${this.baseUrl}`);
    }

    findByIdPet(id: string): Observable<pet>{
        const url = `${this.baseUrl}/${id}`;
        return this.httpClient.get<pet>(url);
    }

    insertPet(pet: pet): Observable<pet>{
        console.log('Dados do pet que estão sendo enviados para o insert:', pet);
        return this.httpClient.post<pet>(`${this.baseUrl}/insert/pet`, pet);
    }

    updatePet(pet: pet): Observable<pet>{
        const url = `${this.baseUrl}/update/${pet.id}`;
        return this.httpClient.put<pet>(url, pet);
    }
     
    deletePet(id: number): Observable<void>{
        const url = `${this.baseUrl}/delete/${id}`;
        return this.httpClient.delete<void>(url);
    }
    
}