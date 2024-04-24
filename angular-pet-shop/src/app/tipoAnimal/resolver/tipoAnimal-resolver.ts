import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from "@angular/router";
import { TipoAnimal } from "../../models/tipoAnimal";
import { inject } from "@angular/core";
import { TipoAnimalService } from "../../services/TipoAnimal.service";

export const tipoAnimalResolver: ResolveFn<TipoAnimal> =
(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(TipoAnimalService).findById(route.paramMap.get('id')!);
}