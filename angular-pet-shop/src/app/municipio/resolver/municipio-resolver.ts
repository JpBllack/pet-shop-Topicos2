import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from "@angular/router";
import { municipio } from "../../models/municipio.model";
import { municipioService } from "../../services/municipio.service";
import { inject } from "@angular/core";

export const municipioResolver: ResolveFn<municipio> =
    (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
        return inject(municipioService).findById(route.paramMap.get('id')!);
    }