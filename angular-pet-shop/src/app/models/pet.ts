import { TipoAnimal } from "./tipoAnimal";
import { usuario } from "./Usuario";

export class pet{
    id!: number;
    anoNascimento!: number;
    nome!: string;
    usuario!: usuario;
    tipoAnimal!: TipoAnimal;
}