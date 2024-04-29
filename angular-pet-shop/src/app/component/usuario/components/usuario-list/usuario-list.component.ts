import { NgFor } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { MatButtonModule } from "@angular/material/button";
import { MatIconModule } from "@angular/material/icon";
import { MatTableModule } from "@angular/material/table";
import { MatToolbarModule } from "@angular/material/toolbar";
import { RouterModule } from "@angular/router";
import { Usuario } from "../../../../models/Usuario";
import { UsuarioService } from "../../../../services/usuario.service";

@Component({
    selector: 'app-usuario-list',
    standalone: true,
    imports: [NgFor, MatTableModule, MatToolbarModule, MatIconModule
        , MatButtonModule, RouterModule],
    templateUrl: './usuario-list.component.html',
    styleUrl: './usuario-list.component.css'
})

export class UsuarioListComponent implements OnInit {
    displayedColumns: string[] = ['id', 'nome', 'cpf', 'username', 'email', 'perfil', 'acao'];
    usuarios: Usuario[] = [];

    constructor(private usuarioService: UsuarioService) { }

    ngOnInit(): void {
        this.usuarioService.getAllUsers().subscribe((data: Usuario[]) => {
            this.usuarios = data;
        });
    }

}