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
import { TipoAnimal } from "../../../models/tipoAnimal";
import { TipoAnimalService } from "../../../services/TipoAnimal.service";

@Component({
    selector: 'app-tipoAnimal-form',
    standalone: true,
    imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
        MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule],
        templateUrl: './tipoAnimal-form.component.html',
        styleUrl: './tipoAnimal-form.component.css',
})

export class TipoAnimalFormComponent implements OnInit {
    formGroup: FormGroup;

    constructor(
        private formBuilder: FormBuilder,
        private tipoAnimalService: TipoAnimalService,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) {
        this.formGroup = formBuilder.group({
            id: [null],
            nome: ['', Validators.required],
        });
    }

    ngOnInit(): void {
        const tipoAnimal: TipoAnimal = this.activatedRoute.snapshot.data['tipoAnimal'];
        if (tipoAnimal) {
            this.formGroup.patchValue(tipoAnimal);
        }
    }

    salvar() {
        if (this.formGroup.valid) {
            const tipoAnimal = this.formGroup.value;
            if (tipoAnimal.id == null) {
                this.tipoAnimalService.insert(tipoAnimal).subscribe({
                    next: () => {
                        this.router.navigateByUrl('/tipos/insert');
                    },
                    error: (err) => {
                        console.log('Erro ao incluir' + JSON.stringify(err));
                    }
                });
            } else {
                this.tipoAnimalService.update(tipoAnimal).subscribe({
                    next: () => {
                        this.router.navigateByUrl(`/tipos/update/${tipoAnimal.id}`);
                    },
                    error: (err) => {
                        console.log('Erro ao editar' + JSON.stringify(err));
                    }
                });
            }
        }
    }

    excluir() {
        if (this.formGroup.valid) {
            const tipoAnimal = this.formGroup.value;
            if (tipoAnimal.id != null) {
                this.tipoAnimalService.delete(tipoAnimal.id).subscribe({
                    next: () => {
                        this.router.navigateByUrl(`/tipos/delete/${tipoAnimal.id}`);
                    },
                    error: (err) => {
                        console.log('Erro ao Excluir' + JSON.stringify(err));
                    }
                });
            }
        }
    }
}