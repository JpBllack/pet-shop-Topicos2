
import { Racao } from '../../../models/racao.model';
import { RacaoService } from '../../../services/racao.serviçe';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { Component, OnInit } from '@angular/core';

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
    RouterModule
  ]
})
export class RacaoListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'sabor', 'animal', 'peso', 'idade', 'acao'];
  racoes: Racao[] = [];

  constructor(private racaoService: RacaoService) {}

  ngOnInit(): void {
    this.racaoService.getAllRacoes().subscribe((data: Racao[]) => {
      this.racoes = data;
    });
  }
}
