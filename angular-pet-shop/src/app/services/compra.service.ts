import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ItemCarrinho } from "../models/itemcarrinho.model";

@Injectable({
    providedIn: 'root'
})
export class CompraService {
    private apiUrl = 'http://localhost:8080/compra';


    constructor(private http: HttpClient) { }

    getComprasByUserId(): Observable<Compra[]> {
        const url = `${this.apiUrl}/usuario/compras`;
        console.log(this.http.get<Compra[]>(url));
        return this.http.get<Compra[]>(url);
    }

}

export interface Compra {
    id: number;
    dataCompra: Date;
    precoTotal: number;
    itens: ItemCarrinho[];
}

export interface ItemCompra {
    nome: string;
    precoUnitario: number;
    quantidade: number;
}