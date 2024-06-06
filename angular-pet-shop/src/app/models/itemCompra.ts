import { Racao } from "./racao.model";

export interface ItemCompra {
    nome: string;
    precoUnitario: number; // Renomeando para corresponder ao modelo do backend
    quantidade: number;
    racao: number;
}