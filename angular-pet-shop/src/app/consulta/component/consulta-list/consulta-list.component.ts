import { Component, OnInit } from '@angular/core';
import { Consulta } from 'caminho/para/sua/consulta.model'; // Certifique-se de ajustar o caminho conforme necessário
import { ConsultaService } from 'caminho/para/seu/consulta.service'; // Certifique-se de ajustar o caminho conforme necessário

@Component({
  selector: 'app-consulta-list',
  standalone: true,
  templateUrl: './consulta-list.component.html',
  styleUrls: ['./consulta-list.component.css']
})
export class ConsultaListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'data', 'motivo', 'veterinario', 'pet', 'acao'];
  consultas: Consulta[] = [];

  constructor(private consultaService: ConsultaService) { }

  ngOnInit(): void {
    this.consultaService.getAllConsultas().subscribe(data => {
      this.consultas = data;
    });
  }
}
