import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { TipoAnimal } from "../models/tipoAnimal";
import { pet } from "../models/pet";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })
export class TipoAnimalService{

    private baseUrl = 'http://localhost:8080/tipos'

    constructor(private httpClient: HttpClient){}

    findAll(): Observable<TipoAnimal[]>{
        return this.httpClient.get<TipoAnimal[]>(this.baseUrl);
    }

    findById(id: string): Observable<TipoAnimal>{
        return this.httpClient.get<TipoAnimal>(`${this.baseUrl}/${id}`);
    }

    insert(tipoAnimal: TipoAnimal): Observable<TipoAnimal>{
        return this.httpClient.post<TipoAnimal>(this.baseUrl, pet);
    }

    update(tipoAnimal: TipoAnimal): Observable<TipoAnimal>{
        return this.httpClient.put<TipoAnimal>(`${this.baseUrl}/${tipoAnimal.nome}`, tipoAnimal);
    }
     
    delete(id: number): Observable<void>{
        const url = `${this.baseUrl}/${id}`;
        return this.httpClient.delete<void>(url);
    }
}