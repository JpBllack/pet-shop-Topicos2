import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Consulta } from '../../../models/consulta.model';
import { ConsultaService } from '../../../services/consulta.service';


@Injectable({ providedIn: 'root' })
export class ConsultaResolver implements Resolve<Consulta[]> {
  constructor(private consultaService: ConsultaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Consulta[]> {
    return this.consultaService.listarTodasConsultas().pipe(
      catchError((error) => {
        console.error('Erro ao resolver consulta:', error);
        // Retorna um array vazio em caso de erro ou executa outra ação desejada
        return of([]);
      })
    );
  }
}
