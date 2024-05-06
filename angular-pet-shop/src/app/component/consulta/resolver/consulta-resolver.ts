import { inject, Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, ResolveFn } from '@angular/router';
import { Consulta } from '../../../models/consulta.model';
import { ConsultaService } from '../../../services/consulta.service';


export const ConsultaResolver: ResolveFn<Consulta> = (
  route: ActivatedRouteSnapshot, state: RouterStateSnapshot
) => {
  const consultaId = Number(route.paramMap.get('id'));
  return inject(ConsultaService).buscarConsultaPorId(consultaId);
}
