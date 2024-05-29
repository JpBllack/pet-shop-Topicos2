export class Endereco
{
    logradouro!: string;
    numero!: string;
    complemento: string = '%';
    bairro!: string;
    idCidade!: number;
    cep!: string;
    enderecoPrincipal: boolean = false;
  
}