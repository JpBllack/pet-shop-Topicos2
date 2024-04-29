import { ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";
import { ResolveFn } from "@angular/router";
import { inject } from "@angular/core";
import { Racao } from "../../../models/racao.model";
import { RacaoService } from "../../../services/racao.service";

export const racaoResolver: ResolveFn<Racao> =
    (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
        return inject(RacaoService).findById(route.paramMap.get('id')!);
    }
