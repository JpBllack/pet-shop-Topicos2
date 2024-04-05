import { Usuario } from './Usuario';
import { pet } from '../models/pet';

export interface Consulta {
  id: number;
  data: Date;
  motivo: string;
  veterinario: Usuario;
  pet: pet;
}
