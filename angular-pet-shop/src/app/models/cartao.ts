export interface Cartao{
    id: number;
    numero: string;
    codigoSeguranca: string;
    mesValidade: number;
    anoValidade: number;
    isPrincipal: boolean;
    nome: string;
  }