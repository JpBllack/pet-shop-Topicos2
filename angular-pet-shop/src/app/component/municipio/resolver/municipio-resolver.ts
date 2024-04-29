import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from "@angular/router";

import { inject } from "@angular/core";
import { municipio } from "../../../models/municipio";
import { municipioService } from "../../../services/municipio.service";

export const municipioResolver: ResolveFn<municipio> =
    (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
        return inject(municipioService).findById(route.paramMap.get('id')!);
    }