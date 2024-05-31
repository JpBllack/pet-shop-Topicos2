export class Endereco
{
    id!: number;
    logradouro!: string;
    numero!: string;
    complemento: string = '%';
    bairro!: string;
    idCidade!: number;
    cep!: string;
    isPrincipal!: boolean;
  
}