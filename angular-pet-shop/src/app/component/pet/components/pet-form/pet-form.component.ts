import { Component, NgModule, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { PetService } from "../../../../services/pet.service";
import { ActivatedRoute, Router, RouterModule } from "@angular/router";
import { pet } from "../../../../models/pet";
import { CommonModule, NgIf } from "@angular/common";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatSelectModule } from "@angular/material/select";
import { TipoAnimal } from "../../../../models/tipoAnimal";
import { MatOptionModule } from "@angular/material/core";
import { TipoAnimalService } from "../../../../services/TipoAnimal.service";
import { HttpClient } from "@angular/common/http";

@Component({
    selector: 'app-pet-form',
    standalone: true,
    imports: [
          NgIf,
          ReactiveFormsModule,
          MatFormFieldModule, 
          MatInputModule, 
          MatButtonModule, 
          MatCardModule,
          MatToolbarModule, 
          CommonModule,
          RouterModule, 
          MatSelectModule,
          MatOptionModule],
    templateUrl: './pet-form.component.html',
    styleUrl: './pet-form.component.css',
})
export class PetFormComponent implements OnInit {
    formGroup: FormGroup;
    usuarios: any[] = [];
    animais: TipoAnimal[] = [];

    constructor(private formBuilder: FormBuilder, 
        private petService: PetService, 
        private tipoAnimalService: TipoAnimalService,
        private router: Router, 
        private activatedRoute: ActivatedRoute,
        private http: HttpClient)
         {
        this.formGroup = formBuilder.group({
            id: [null],
            nome: ['', Validators.required],
            usuario: [null, Validators.required], // Nome do campo atualizado
            animal: [null, Validators.required],
            anoNascimento: [null, Validators.required]
        });
    }

    ngOnInit(): void {
        const pet: pet = this.activatedRoute.snapshot.data['pet'];
        this.carregarTipos();
        if (pet) {
          this.formGroup.patchValue(pet);
        };
      }
    

    

    salvar() {
        if (this.formGroup.valid) {
            const pet = this.formGroup.value;
            if (pet.id == null) {
                this.petService.insertPet(pet).subscribe({
                    next: () => {
                        this.router.navigateByUrl('/pets/all');
                    },
                    error: (err) => {
                        console.log('Erro ao incluir' + JSON.stringify(err));
                    }
                });
            } else {
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

    excluir() {
        if (this.formGroup.valid) {
            const pet = this.formGroup.value;
            if (pet.id != null) {
                this.petService.deletePet(pet).subscribe({
                    next: () => {
                        this.router.navigateByUrl('/pets/all');
                    },
                    error: (err) => {
                        console.log('Erro ao excluir' + JSON.stringify(err));
                    }
                });
            }
        }
    }

    carregarTipos(){
        this.tipoAnimalService.findAll().subscribe(
          (tipo: TipoAnimal[]) => {
            this.animais = tipo;
          },
          (error) => {
            console.error('Erro ao carregar animais:', error);
          }
        )
      }
}

@NgModule({
    declarations: [],
    imports: [
      CommonModule,
      MatSelectModule,
      MatOptionModule,
    ]
  })
  export class PetFormModule { }