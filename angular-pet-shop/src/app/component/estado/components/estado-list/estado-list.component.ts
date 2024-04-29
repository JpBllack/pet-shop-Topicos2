import { Component, OnInit } from '@angular/core';
import { NgFor } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { Estado } from '../../../../models/estado.model';
import { EstadoService } from '../../../../services/estado.service';

@Component({
  selector: 'app-estado-list',
  standalone: true,
  imports: [NgFor, MatTableModule, MatToolbarModule, MatIconModule
  , MatButtonModule, RouterModule],
  templateUrl: './estado-list.component.html',
  styleUrl: './estado-list.component.css'
})
export class EstadoListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'nome', 'sigla', 'acao'];
  estado: Estado[] = [];

  constructor(private estadoService: EstadoService) {

  }

  ngOnInit(): void {
    this.estadoService.findAll().subscribe(data => {
      this.estado = data;
    })
  }

}
