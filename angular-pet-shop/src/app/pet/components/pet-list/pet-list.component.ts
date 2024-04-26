import { Component, OnInit } from '@angular/core';

import { petService } from '../../../services/pet.service';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterModule } from '@angular/router';
import { TipoAnimal } from '../../../models/tipoAnimal';
import { Usuario } from '../../../models/Usuario';
import { pet } from '../../../models/pet';


@Component({
    selector: 'app-pet-list',
    standalone: true,
    imports: [CommonModule, MatTableModule, MatToolbarModule, MatIconModule, MatButtonModule, RouterModule],
    templateUrl: './pet-list.component.html',
    styleUrls: ['./pet-list.component.css']
})
export class petListComponent implements OnInit {
    displayedColumns: string[] = ['id', 'nome', 'usuario', 'tipoAnimal', 'anoNascimento', 'acao'];
    pets: pet[] = [];
    usuarios: Usuario[] = [];
    animais: TipoAnimal[] = [];

    constructor(private petService: petService) {}

    ngOnInit(): void {
        this.petService.getAllpets().subscribe((data: pet[]) => {
            this.pets = data;
        });
    }
}
