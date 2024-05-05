import { NgFor } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatTableModule } from "@angular/material/table";
import { MatToolbarModule } from "@angular/material/toolbar";
import { RouterModule } from "@angular/router";
import { Marca } from "../../../../models/marca";
import { MarcaService } from "../../../../services/marca.service";
import { TitleService } from "../../../../title.service";

@Component({
    selector: 'app-marca-form',
    standalone: true,
    imports: [NgFor, MatTableModule, MatToolbarModule, MatIconModule
        , MatButtonModule, RouterModule],
        templateUrl: './marca-list.component.html',
        styleUrl: './marca-list.component.css'
})

export class MarcaListComponent implements OnInit{
    displayedColumns: string[] = ['id', 'nome', 'acao'];
    marcas: Marca[] = [];

    constructor(
        private marcaService: MarcaService,
        private titleService: TitleService)
        {}

    ngOnInit(): void {
        this.marcaService.findAll().subscribe((data: Marca[]) => {
            this.marcas = data;
            this.titleService.setTitle('Lista de marcas');
        })
    }

    
}