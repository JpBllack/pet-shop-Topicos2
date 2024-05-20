

import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { CommonModule } from '@angular/common';
import { Racao } from '../../../../models/racao.model';
import { TipoAnimal } from '../../../../models/tipoAnimal';
import { RacaoService } from '../../../../services/racao.service';

@Component({
  selector: 'app-racao-list',
  standalone : true,
  templateUrl: './racao-list.component.html',
  styleUrls: ['./racao-list.component.css'],
  imports: [
    MatTableModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    RouterModule,
    CommonModule,

  ],
})


export class RacaoListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'sabor', 'marca','preco', 'estoque', 'animal', 'peso', 'idade', 'acao'];
  racoes: Racao[] = [];
  animals: TipoAnimal[] = [];

  constructor(
    private racaoService: RacaoService,
  ) {}

  ngOnInit(): void {
    this.racaoService.getAllRacoes().subscribe((data: Racao[]) => {
      this.racoes = data;
    });
  }
}
