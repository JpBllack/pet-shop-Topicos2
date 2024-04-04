import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { PetService } from "../../../services/pet.service";
import { ActivatedRoute, Router } from "@angular/router";
import { usuario } from "../../../models/Usuario";
import { pet } from "../../../models/pet";

@Component({
    selector: 'app-pet-form',
    standalone: true,
    imports: [],
    templateUrl: './pet-form.component.html',
    styleUrl: './pet-form.component.css'
})

export class PetFormComponent implements OnInit
{
    formGroup: FormGroup;

    constructor(private formBuilder: FormBuilder, private petService: PetService, private router: Router, private activatedRoute: ActivatedRoute){
        this.formGroup = formBuilder.group({
            id: [null],
            nome:['', Validators.required],
            usuario: [null, Validators.required],
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
                this.petService.
            }
        }
    }
}