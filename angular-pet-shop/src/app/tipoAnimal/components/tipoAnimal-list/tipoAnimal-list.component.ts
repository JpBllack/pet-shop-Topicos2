import { NgFor } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatTableModule } from "@angular/material/table";
import { MatToolbarModule } from "@angular/material/toolbar";
import { RouterModule } from "@angular/router";
import { TipoAnimal } from "../../../models/tipoAnimal";
import { TipoAnimalService } from "../../../services/TipoAnimal.service";

@Component({
    selector: 'app-tipoAnimal-form',
    standalone: true,
    imports: [NgFor, MatTableModule, MatToolbarModule, MatIconModule
        , MatButtonModule, RouterModule],
    templateUrl: './tipoAnimal-list.component.html',
    styleUrl: './tipoAnimal-list.component.css'
})
export class TipoAnimalListComponent implements OnInit{
    displayedColumns: string[] = ['id', 'nome'];
    tipoAnimais: TipoAnimal[] = [];

    constructor(private tipoAnimalService: TipoAnimalService){

    }
    ngOnInit(): void {
        this.tipoAnimalService.findAll().subscribe((data: TipoAnimal[]) => {
            this.tipoAnimais = data;
        })
    }

    
}