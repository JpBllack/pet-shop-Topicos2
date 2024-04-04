import { pet } from '../models/pet';
import { usuario } from '../models/Usuario';

export class Consulta {
  id: number;
  data: Date;
  motivo: string;
  veterinario: usuario;
  pet: pet;

  constructor(
    id: number,
    data: Date,
    motivo: string,
    veterinario: usuario,
    pet: pet
  ) {
    this.id = id;
    this.data = data;
    this.motivo = motivo;
    this.veterinario = veterinario;
    this.pet = pet;
  }
}
