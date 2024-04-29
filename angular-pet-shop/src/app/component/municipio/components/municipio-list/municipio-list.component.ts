

import { NgFor } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { municipioService } from '../../../../services/municipio.service';
import { municipio } from '../../../../models/municipio';
import { TitleService } from '../../../../title.service';

@Component({
  selector: 'app-municipio-list',
  standalone: true,
  imports: [NgFor, MatTableModule, MatToolbarModule, MatIconModule
  , MatButtonModule, RouterModule],
  templateUrl: './municipio-list.component.html',
  styleUrl: './municipio-list.component.css'
})
export class municipioListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'nome', 'estado', 'acao'];
  municipios: municipio[] = [];

  constructor(
    private municipioService: municipioService,
    private titleService: TitleService            
  ) {

  }

  ngOnInit(): void {
    this.municipioService.findAll().subscribe(data => {
      this.municipios = data;
      this.titleService.setTitle('Municípios');
    })
  }

}
