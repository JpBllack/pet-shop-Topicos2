import { NgIf } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatSelectModule } from "@angular/material/select";
import { MatToolbarModule } from "@angular/material/toolbar";
import { ActivatedRoute, Route, Router, RouterModule } from "@angular/router";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { MarcaService } from "../../../../services/marca.service";
import { Marca } from "../../../../models/marca";

@Component({
    selector: 'app-marca-form',
    standalone: true,
    imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
        MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule],
        templateUrl: './marca-form.component.html',
        styleUrl: './marca-form.component.css',
})

export class MarcaFormComponent implements OnInit {
    formGroup: FormGroup;

    constructor(
        private formBuilder: FormBuilder,
        private marcaService: MarcaService,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) {
        this.formGroup = formBuilder.group({
            id: [null],
            nome: ['', Validators.required],
        })
    }

    ngOnInit(): void {
        const marca: Marca = this.activatedRoute.snapshot.data['marca'];
        if(marca){
            this.formGroup.patchValue(marca);
        }
    }

    salvar() {
        if (this.formGroup.valid) {
            const marca = this.formGroup.value;
            if (marca.id == null) {
                this.marcaService.insert(marca).subscribe({
                    next: () => {
                        this.router.navigateByUrl('/marcas/all');
                    },
                    error: (err) => {
                        console.log('Erro ao incluir' + JSON.stringify(err));
                    }
                });
            } else {
                this.marcaService.update(marca).subscribe({
                    next: () => {
                        this.router.navigateByUrl(`/marcas/all`);
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
            const marca = this.formGroup.value;
            if (marca.id != null) {
                this.marcaService.delete(marca.id).subscribe({
                    next: () => {
                        this.router.navigateByUrl(`/marcas/all`);
                    },
                    error: (err) => {
                        console.log('Erro ao Excluir' + JSON.stringify(err));
                    }
                });
            }
        }
    }
}