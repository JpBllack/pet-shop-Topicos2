import { Component, OnInit } from "@angular/core";
import { pet } from "../../../models/pet";
import { PetService } from "../../../services/pet.service";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatTableModule } from "@angular/material/table";
import { MatToolbarModule } from "@angular/material/toolbar";
import { RouterModule } from "@angular/router";

@Component({
    selector: 'app-pet-list',
    standalone: true,
    imports: [MatTableModule, MatToolbarModule, MatIconModule, MatButtonModule, RouterModule],
    templateUrl: './pet-list.component.html',
    styleUrls: ['./pet-list.component.css']
})

export class PetListComponent implements OnInit {
    displayedColumns: string[] = ['id', 'nome', 'usuario', 'tipoAnimal', 'anoNascimento'];
    pets: pet[] = [];

    constructor(private petService: PetService) {}

    ngOnInit(): void {
        this.petService.findAll().subscribe((data: pet[]) => {
            this.pets = data;
        })
    }
}
