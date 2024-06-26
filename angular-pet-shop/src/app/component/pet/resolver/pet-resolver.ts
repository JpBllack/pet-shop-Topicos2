import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from "@angular/router";

import { inject } from "@angular/core";
import { pet } from "../../../models/pet";
import { PetService } from "../../../services/pet.service";


export const petResolver: ResolveFn<pet> = 
(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(PetService).findByIdPet(route.paramMap.get('id')!);
}