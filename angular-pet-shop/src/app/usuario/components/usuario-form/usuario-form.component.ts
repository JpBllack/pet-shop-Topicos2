import { NgIf } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatSelectModule } from "@angular/material/select";
import { MatToolbarModule } from "@angular/material/toolbar";
import { ActivatedRoute, Router, RouterModule } from "@angular/router";
import { UsuarioService } from "../../../services/usuario.service";
import { Usuario } from "../../../models/Usuario";


@Component({
    selector: 'app-usuario-form',
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
            id: [null],
            nome:['', Validators.required],
            cpf:['', Validators.required],
            username:['', Validators.required],
            email:['', Validators.required],
            perfil:[null, Validators.required]
            

        })
    }
    ngOnInit(): void {
        const usuario: Usuario = this.activatedRoute.snapshot.data['usuario'];
        if(usuario){
            this.formGroup.patchValue(usuario);
        }
    }

    salvar(){
        if(this.formGroup.valid){
            const usuario = this.formGroup.value;
            if(usuario.id == null){
                this.usuarioService.insertUser(usuario).subscribe({
                    next: () => {
                        this.router.navigateByUrl('/usuarios');
                    },
                    error: (err) => {
                        console.log('Erro ao incluir' + JSON.stringify(err));
                    }
                });
            } else{
                this.usuarioService.updateUser(usuario).subscribe({
                    next: () => {
                        this.router.navigateByUrl('/usuarios');
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
                this.usuarioService.deleteUser(usuario).subscribe({
                    next: () => {
                        this.router.navigateByUrl('/usuarios');
                    },
                    error: (err) => {
                        console.log('Erro ao Excluir' + JSON.stringify(err));
                    }
                });
            }
        }
    }
}