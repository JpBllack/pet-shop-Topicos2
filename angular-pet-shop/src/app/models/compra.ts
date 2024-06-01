import { Cartao } from "./cartao";
import { Endereco } from "./endereco";
import { ItemCompra } from "./itemCompra";
import { StatusCompra } from "./statusCompra";

export interface Compra {
    id: number;
    dataCompra: Date;
    statusCompra: StatusCompra[];
    precoTotal: number;
    cartao: Cartao;
    endereco: Endereco;
    itens: ItemCompra[];
}