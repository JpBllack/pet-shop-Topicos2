import { CommonModule, NgIf, Location } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatSelectModule } from "@angular/material/select";
import { MatToolbarModule } from "@angular/material/toolbar";
import { ActivatedRoute, Router, RouterModule } from "@angular/router";

import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { Usuario } from "../../../../models/Usuario";
import { Perfil } from "../../../../models/perfil";
import { UsuarioService } from "../../../../services/usuario.service";


@Component({
    selector: 'app-usuario-form',
    standalone: true,
    imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
        MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule, CommonModule],
        templateUrl: './usuario-form.component.html',
        styleUrl: './usuario-form.component.css'
})

export class UsuarioFormComponent implements OnInit{
    formGroup: FormGroup;
    perfis = Object.values(Perfil).filter(value => isNaN(Number(value)));

    constructor(
        private formBuilder: FormBuilder, 
        private usuarioService: UsuarioService, 
        private router: Router, 
        private activatedRoute: ActivatedRoute,
        private location: Location
    ){

        this.formGroup = formBuilder.group({
            id: [null],
            nome:['', Validators.required],
            cpf:['', Validators.required],
            username:['', Validators.required],
            email:['', Validators.required],
            senha:['', Validators.required],
            perfil:['', Validators.required]
        })
    }
    ngOnInit(): void {
        const usuario: Usuario = this.activatedRoute.snapshot.data['usuario'];
        if(usuario){
            this.formGroup.patchValue(usuario);
        }
    }

    
  voltarPagina() {
    this.location.back();
  }


    salvar(){
        if(this.formGroup.valid){
            const usuario = this.formGroup.value;
            if(usuario.id == null){
                this.usuarioService.insertUser(usuario).subscribe({
                    next: () => {
                        this.voltarPagina();
                    },
                    error: (err) => {
                        console.log('Erro ao incluir' + JSON.stringify(err));
                    }
                });
            } else{
                this.usuarioService.updateUser(usuario).subscribe({
                    next: () => {
                        this.voltarPagina();
                    },
                    error: (err) => {
                        console.log('Erro ao editar' + JSON.stringify(err));
                    }
                });
            }
        }
    }

    excluir(){
        if(this.formGroup.valid){
            const usuario = this.formGroup.value;
            if(usuario.id != null){
                this.usuarioService.deleteUser(usuario.id).subscribe({
                    next: () => {
                        this.voltarPagina();
                    },
                    error: (err) => {
                        console.log('Erro ao Excluir' + JSON.stringify(err));
                    }
                });
            }
        }
    }
}