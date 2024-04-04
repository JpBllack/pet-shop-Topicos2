import { ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";
import { Racao } from "../../models/racao.model";
import { RacaoService } from "../../services/racao.serviçe";

import { ResolveFn } from "@angular/router";
import { inject } from "@angular/core";

export const racaoResolver: ResolveFn<Racao> =
    (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
        return inject(RacaoService).findById(route.paramMap.get('id')!);
    }
