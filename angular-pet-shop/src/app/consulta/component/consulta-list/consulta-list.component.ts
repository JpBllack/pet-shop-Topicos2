import { Component, OnInit } from '@angular/core';
import { Consulta } from '../../../models/consulta.model';
import { ConsultaService } from '../../../services/consulta.service'; 
import { RouterModule } from '@angular/router';
import { NgIf } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-consulta-list',
  standalone: true,
  styleUrls: ['./consulta-list.component.css'],
  imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
    MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, CommonModule,MatTableModule ,MatIconModule,RouterModule],
  templateUrl: './consulta-list.component.html'
})
export class ConsultaListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'data', 'motivo', 'veterinario', 'pet', 'acao'];
  consultas: Consulta[] = [];

  constructor(private consultaService: ConsultaService) {}

  ngOnInit(): void {
    this.consultaService.listarTodasConsultas().subscribe(data => {
      this.consultas = data;
    });
  }
}
