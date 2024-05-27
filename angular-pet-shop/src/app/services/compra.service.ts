import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ItemCarrinho } from "../models/itemcarrinho.model";
import { Status } from "../models/status";

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

    getItensByCompraId(compraId: number): Observable<ItemCompra[]> {
        const url = `${this.apiUrl}/${compraId}/itens`;
        return this.http.get<ItemCompra[]>(url);
    }

}

export interface Compra {
    id: number;
    dataCompra: Date;
    statusCompra: StatusCompra[];
    precoTotal: number;
    itens: ItemCarrinho[];
}

export interface ItemCompra {
    id: number;
    nome: string;
    preco: number;
    quantidade: number;
}

export class StatusCompra{
    dataStatus!: Date;
    status!: Status;
}