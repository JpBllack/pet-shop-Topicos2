import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { pet } from "../models/pet";




export class PetService{

    private baseUrl = 'http://localhost:8080/pets';

    constructor(private httpClient: HttpClient){}

    findAll(): Observable<pet[]>{
        return this.httpClient.get<pet[]>(this.baseUrl);
    }

    findById(id: number): Observable<pet>{
        return this.httpClient.get<pet>(`${this.baseUrl}/${id}`);
    }

    insert(pet: pet): Observable<pet>{
        return this.httpClient.post<pet>(this.baseUrl, pet);
    }

    update(pet: pet): Observable<pet>{
        return this.httpClient.put<pet>(`${this.baseUrl}/${pet.id}`, pet);
    }
     
    delete(id: number): Observable<void>{
        const url = `${this.baseUrl}/${id}`;
        return this.httpClient.delete<void>(url);
    }
    
}