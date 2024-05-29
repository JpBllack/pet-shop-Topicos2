import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // Importe o módulo de formulários se necessário

@Component({
  selector: 'app-busca',
  templateUrl: './busca.component.html',
  styleUrls: ['./busca.component.css'],
  standalone: true, // Defina como true para indicar que é um componente independente
  imports: [CommonModule, FormsModule] // Importe os módulos necessários aqui
})
export class BuscaComponent implements OnInit {
  resultados: any[] = [];

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      // @ts-ignore
      if (params['racoes']) {
        this.resultados = JSON.parse(params['racoes']);
      }
    });
  }
  
}
