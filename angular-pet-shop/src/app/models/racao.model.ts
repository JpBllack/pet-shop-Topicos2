import { TipoAnimal } from './tipoAnimal';
import { Peso } from './peso';
import { Idade } from './idade';

export class Racao {
  id!: number;
  sabor!: string;
  animal!: TipoAnimal;
  peso!: Peso;
  idade!: Idade;
  imagem!: String
}
