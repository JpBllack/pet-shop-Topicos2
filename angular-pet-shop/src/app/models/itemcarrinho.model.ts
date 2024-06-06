import { Racao } from "./racao.model";

export interface ItemCarrinho {
  id: number;
  nome: string;
  preco: number;
  quantidade: number;
  frequencia:number;
  imagem: string;
  racao: Racao;
}
