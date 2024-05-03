import { Component, Injectable, NgModule, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { PetService } from "../../../../services/pet.service";
import { ActivatedRoute, Router, RouterModule } from "@angular/router";
import { Usuario } from "../../../../models/Usuario";
import { pet } from "../../../../models/pet";
import { CommonModule, NgIf } from "@angular/common";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatSelectModule } from "@angular/material/select";

@Component({
    selector: 'app-pet-form',
    standalone: true,
    imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
        MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule,CommonModule, RouterModule, MatSelectModule],
    templateUrl: './pet-form.component.html',
    styleUrl: './pet-form.component.css',
})

export class PetFormComponent implements OnInit
{
    formGroup: FormGroup;
    usuarios: any;
    tiposAnimais: any;

    constructor(private formBuilder: FormBuilder, private petService: PetService, private router: Router, private activatedRoute: ActivatedRoute){
        this.formGroup = formBuilder.group({
            id: [null],
            nome:['', Validators.required],
            usuario: [null, Validators.required],
            tipoAnimal: [null, Validators.required]
        });
    }

    ngOnInit(): void {
        const pet: pet = this.activatedRoute.snapshot.data['pet'];
        if(pet){
            this.formGroup.patchValue(pet);
        }
    }
    
    salvar(){
        if(this.formGroup.valid){
            const pet = this.formGroup.value;
            if(pet.id == null){
                this.petService.insertPet(pet).subscribe({
                    next: () => {
                        this.router.navigateByUrl('/pets/all');
                    },
                    error: (err) => {
                        console.log('Erro ao incluir' + JSON.stringify(err));
                    }
                });
            } else{
                this.petService.updatePet(pet).subscribe({
                    next: () => {
                        this.router.navigateByUrl('/pets/all');
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
            const pet = this.formGroup.value;
            if(pet.id != null){
                this.petService.deletePet(pet).subscribe({
                    next: () => {
                        this.router.navigateByUrl('/pets/all');
                    },
                    error: (err) => {
                        console.log('Erro ao Excluir' + JSON.stringify(err));
                    }
                });
            }
        }
    }
}