import { Component, OnInit } from "@angular/core";
import { CommonModule } from "@angular/common";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatTableModule } from "@angular/material/table";
import { MatToolbarModule } from "@angular/material/toolbar";
import { RouterModule } from "@angular/router";

import { pet } from "../../../../models/pet";
import { PetService } from "../../../../services/pet.service";



@Component({
    selector: 'app-pet-list',
    standalone: true,
    imports: [
        CommonModule,
        MatTableModule,
        MatToolbarModule,
        MatIconModule,
        MatButtonModule,
        RouterModule
    ],
    templateUrl: './pet-list.component.html',
    styleUrls: ['./pet-list.component.css']
})
export class PetListComponent implements OnInit {
    // Colunas exibidas na tabela
    displayedColumns: string[] = ['id', 'nome', 'usuario', 'tipoAnimal', 'anoNascimento', 'acao'];

    // Lista de pets carregados
    pets: pet[] = [];

    // Injeção do serviço PetService para realizar operações com pets
    constructor(private petService: PetService) { }

    // Método inicial que carrega todos os pets ao iniciar o componente
    ngOnInit(): void {
        this.carregarPets();
    }

    // Função para carregar todos os pets
    carregarPets(): void {
        this.petService.findAllPet().subscribe((data: pet[]) => {
            // Armazena a lista de pets no componente
            this.pets = data;
        });
    }
}
