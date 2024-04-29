import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from "@angular/router";

import { inject } from "@angular/core";
import { TipoAnimal } from "../../../models/tipoAnimal";
import { TipoAnimalService } from "../../../services/TipoAnimal.service";


export const tipoAnimalResolver: ResolveFn<TipoAnimal> =
(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(TipoAnimalService).findById(route.paramMap.get('id')!);
}