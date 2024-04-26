import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { petService } from '../../../services/pet.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { pet } from '../../../models/pet';
import { NgIf } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';

@Component({
    selector: 'app-pet-form',
    standalone: true,
    imports: [
        NgIf, ReactiveFormsModule, MatFormFieldModule,
        MatInputModule, MatButtonModule, MatCardModule,
        MatToolbarModule, RouterModule, MatIconModule, MatSelectModule
    ],
    templateUrl: './pet-form.component.html',
    styleUrls: ['./pet-form.component.css']
})
export class PetFormComponent implements OnInit {
    formGroup: FormGroup;

    constructor(
        private formBuilder: FormBuilder,
        private petService: petService,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) {
        this.formGroup = formBuilder.group({
            id: [null],
            nome: ['', Validators.required],
            usuario: [null, Validators.required],
            tipoAnimal: [null, Validators.required],
            anoNascimento: ['', Validators.required],
        });
    }

    ngOnInit(): void {
        const pet: pet = this.activatedRoute.snapshot.data['pet'];
        if (pet) {
            this.formGroup.patchValue(pet);
        }
    }

    salvar() {
        if (this.formGroup.valid) {
            const pet = this.formGroup.value;
            if (pet.id == null) {
                this.petService.createpet(pet).subscribe({
                    next: () => {
                        this.router.navigateByUrl('/pets/all');
                    },
                    error: (err) => {
                        console.error('Erro ao incluir:', err);
                    }
                });
            } else {
                this.petService.updatepet(pet).subscribe({
                    next: () => {
                        this.router.navigateByUrl('/pets/all');
                    },
                    error: (err) => {
                        console.error('Erro ao editar:', err);
                    }
                });
            }
        }
    }

    excluir() {
        const pet = this.formGroup.value;
        if (pet.id != null) {
            this.petService.deletepetById(pet.id).subscribe({
                next: () => {
                    this.router.navigateByUrl('/pets/all');
                },
                error: (err) => {
                    console.error('Erro ao excluir:', err);
                }
            });
        }
    }
}
