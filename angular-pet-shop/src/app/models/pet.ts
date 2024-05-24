import { TipoAnimal } from "./tipoAnimal";
import { Usuario } from "./Usuario";

export class pet{
    id!: number;
    anoNascimento!: number;
    nome!: string;
    usuario!: Usuario;
    tipoAnimal!: TipoAnimal;
}
