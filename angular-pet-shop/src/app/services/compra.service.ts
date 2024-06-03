import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ItemCarrinho } from "../models/itemcarrinho.model";
import { Status } from "../models/status";
import { Compra } from "../models/compra";
import { ItemCompra } from "../models/itemCompra";
import { AuthService } from "./auth.service";
import { Endereco } from "../models/endereco";
import { Cartao } from "../models/cartao";


@Injectable({
    providedIn: 'root'
})
export class CompraService {
    private apiUrl = 'http://localhost:8080/compra';


    constructor(private http: HttpClient, private authService: AuthService) { }

    private getHeaders() {
        const token = this.authService.getToken();
        return new HttpHeaders({
          'Authorization': `Bearer ${token}`
        });
      }
    

    getComprasByUserId(): Observable<Compra[]> {
        const url = `${this.apiUrl}/usuario/compras`;
        console.log(this.http.get<Compra[]>(url));
        return this.http.get<Compra[]>(url, { headers: this.getHeaders() });
    }

    getItensByCompraId(compraId: number): Observable<ItemCompra[]> {
        const url = `${this.apiUrl}/${compraId}/itens`;
        return this.http.get<ItemCompra[]>(url, { headers: this.getHeaders() });
    }

    concluirCompra(itensCompra: ItemCompra[], endereco: Endereco, cartao: Cartao): Observable<Compra>{
        const url = `${this.apiUrl}/concluir`;
        const body = { itensCompra, endereco, cartao };
        console.log('Cartao no Service: ' + cartao.numero)
        console.log('Endereco no Service: ' + endereco.complemento)
        return this.http.post<Compra>(url, body, { headers: this.getHeaders() });
    }

}