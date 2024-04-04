/*  import { Component, OnInit } from '@angular/core';
import { Racao } from '../../../models/racao.model';
import { RacaoService } from '../../../services/racao.serviçe';

@Component({
  selector: 'app-racao-list',
  templateUrl: './racao-list.component.html',
  styleUrls: ['./racao-list.component.css']
})
export class RacaoListComponent implements OnInit {
  racoes: Racao[] = [];

  constructor(private racaoService: RacaoService) { }

  ngOnInit(): void {
    this.racaoService.getAllRacoes().subscribe((data: Racao[]) => {
      this.racoes = data;
    });
  }
}
  */