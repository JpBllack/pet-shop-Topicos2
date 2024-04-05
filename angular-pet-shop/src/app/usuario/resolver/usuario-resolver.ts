import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from "@angular/router";
import { usuario } from "../../models/Usuario";
import { UsuarioService } from "../../services/usuario.service";
import { inject } from "@angular/core";

export const petResolver: ResolveFn<usuario> = 
(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    return inject(UsuarioService).getUserById(route.paramMap.get('id')!);
}