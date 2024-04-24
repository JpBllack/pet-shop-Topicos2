import { Component, OnInit } from "@angular/core";
import { pet } from "../../../models/pet";
import { PetService } from "../../../services/pet.service";
import { NgFor } from "@angular/common";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatTableModule } from "@angular/material/table";
import { MatToolbarModule } from "@angular/material/toolbar";
import { RouterModule } from "@angular/router";
import { TitleService } from "../../../title.service";

@Component({
    selector: 'app-racao-list',
    standalone: true,
    imports: [NgFor, MatTableModule, MatToolbarModule, MatIconModule
        , MatButtonModule, RouterModule],
    templateUrl: './pet-list.component.html',
    styleUrl: './pet-list.component.css'
})

export class PetListComponent implements OnInit{
    displayedColumns: string[] = ['id', 'nome', 'usuario', 'tipoAnimal', 'anoNascimento'];
    pets: pet[] = [];

    constructor(private petService: PetService) { }

    ngOnInit(): void {
        this.petService.findAll().subscribe((data: pet[]) => {
            this.pets = data;
        })
    }

    
}