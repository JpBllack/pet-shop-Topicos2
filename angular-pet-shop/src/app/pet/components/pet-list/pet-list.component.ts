import { Component, OnInit } from "@angular/core";
import { pet } from "../../../models/pet";
import { PetService } from "../../../services/pet.service";

@Component({
    selector: 'app-racao-list',
    standalone: true,
    templateUrl: './pet-list.component.html',
    styleUrl: './pet-list.component.css'
})

export class PetListComponent implements OnInit{
    displayedColumns: string[] = ['id', 'nome', 'usuario', 'tipoAnimal', 'anoNascimento'];
    pets: pet[] = [];

    constructor(private petService: PetService){}

    ngOnInit(): void {
        this.petService.findAll().subscribe((data: pet[]) => {
            this.pets = data;
        })
    }

    
}