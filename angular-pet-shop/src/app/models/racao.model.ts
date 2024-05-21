import { TipoAnimal } from './tipoAnimal';
import { Peso } from './peso';
import { Idade } from './idade';
import { Marca } from './marca';

export class Racao {
  id!: number;
  nome!: string;
  sabor!: string;
  preco!: number;
  animal!: TipoAnimal;
  peso!: Peso;
  idade!: Idade;
  imagem!: string;
  marca!: Marca;
  estoque!: number;
}
