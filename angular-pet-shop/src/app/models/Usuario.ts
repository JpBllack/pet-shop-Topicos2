import { Perfil } from "./perfil";

export class Usuario{
    id!: number;
    nome!: string;
    cpf!: string;
    username!: string;
    email!: string;
    senha!: string;
    perfil!: Perfil;
}