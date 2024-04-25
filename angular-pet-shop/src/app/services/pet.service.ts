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

    findAll(): Observable<pet[]>{
        return this.httpClient.get<pet[]>(`${this.baseUrl}/all`);
    }

    findById(id: string): Observable<pet>{
        const url = `${this.baseUrl}/${id}`;
        return this.httpClient.get<pet>(url);
    }

    insert(pet: pet): Observable<pet>{
        return this.httpClient.post<pet>(`${this.baseUrl}/insert`, pet);
    }

    update(pet: pet): Observable<pet>{
        const url = `${this.baseUrl}/update/${pet.id}`;
        return this.httpClient.put<pet>(url, pet);
    }
     
    delete(id: number): Observable<void>{
        const url = `${this.baseUrl}/delete/${id}`;
        return this.httpClient.delete<void>(url);
    }
    
}