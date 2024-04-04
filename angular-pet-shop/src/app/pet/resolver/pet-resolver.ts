import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from "@angular/router";
import { pet } from "../../models/pet";
import { inject } from "@angular/core";
import { PetService } from "../../services/pet.service";

export const petResolver: ResolveFn<pet> = 
(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(PetService).findById(route.paramMap.get('id')!);
}