import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from "@angular/router";
import { TipoAnimal } from "../../models/tipoAnimal";
import { inject } from "@angular/core";
import { PetService } from "../../services/pet.service";

export const tipoAnimalResolver: ResolveFn<TipoAnimal> =
(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(PetService).findById(route.paramMap.get('id')!);
}