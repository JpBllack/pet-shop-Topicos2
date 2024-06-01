import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ItemCarrinho } from "../models/itemcarrinho.model";
import { Status } from "../models/status";
import { Compra } from "../models/compra";
import { ItemCompra } from "../models/itemCompra";

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