import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from "@angular/router";
import { inject } from "@angular/core";
import { Estado } from "../../../models/estado.model";
import { EstadoService } from "../../../services/estado.service";

export const estadoResolver: ResolveFn<Estado> =
    (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
        return inject(EstadoService).findById(route.paramMap.get('id')!);
    }