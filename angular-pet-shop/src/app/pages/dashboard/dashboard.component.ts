import { CommonModule, NgIf } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatSelectModule } from "@angular/material/select";
import { MatToolbarModule } from "@angular/material/toolbar";
import { ActivatedRoute, Router, RouterModule } from "@angular/router";
import { UsuarioLogadoService } from "../../services/usuarioLogado.service";
import { Usuario } from "../../models/Usuario";

@Component({
    selector: 'app-dashboard',
    standalone: true,
    imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
        MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule, CommonModule],
        templateUrl: './dashboard.component.html',
        styleUrl: './dashboard.component.css'
})
export class UsuarioLogadoComponent implements OnInit{

    formGroup: FormGroup;

    constructor(private formBuilder: FormBuilder, private usuarioLogadoService: UsuarioLogadoService, private router: Router, private activatedRoute: ActivatedRoute){

        this.formGroup = formBuilder.group({
            id: [null],
            nome:['', Validators.required],
            cpf:['', Validators.required],
            username:['', Validators.required],
            email:['', Validators.required],
            senha:['', Validators.required],
        })
    }

    ngOnInit(): void {
        const usuario: Usuario = this.activatedRoute.snapshot.data['usuario'];
        if(usuario){
            this.formGroup.patchValue(usuario);
        }
    }
}