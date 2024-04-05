import { NgIf } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, ReactiveFormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatSelectModule } from "@angular/material/select";
import { MatToolbarModule } from "@angular/material/toolbar";
import { ActivatedRoute, Router, RouterModule } from "@angular/router";
import { UsuarioService } from "../../../services/usuario.service";


@Component({
    selector: 'app-pet-form',
    standalone: true,
    imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
        MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule],
        templateUrl: './usuario-form.component.html',
        styleUrl: './usuario-form.component.css'
})

export class UsuarioFormComponent implements OnInit{
    formGroup: FormGroup;

    constructor(private formBuilder: FormBuilder, private usuarioService: UsuarioService, private router: Router, private activatedRoute: ActivatedRoute){
        this.formGroup = formBuilder.group({
            

        })
    }
    ngOnInit(): void {
        throw new Error("Method not implemented.");
    }
}