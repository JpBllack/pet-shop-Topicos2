import { TipoAnimal } from './tipoAnimal';
import { Peso } from './peso';
import { Idade } from './idade';
import { Marca } from './marca';

export class Racao {
  id!: number;
  sabor!: string;
  animal!: TipoAnimal;
  peso!: Peso;
  idade!: Idade;
  imagem!: String;
  marca!: Marca;
}
