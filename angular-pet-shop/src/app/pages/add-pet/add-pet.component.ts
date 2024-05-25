import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { TipoAnimalService } from '../../services/TipoAnimal.service';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';

@Component({
  selector: 'app-add-pet',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './add-pet.component.html',
  styleUrls: ['./add-pet.component.css']
})
export class AddPetComponent implements OnInit {
  petForm!: FormGroup;
  tiposAnimais: any[] = [];
  petsUsuario: any[] = [];

  constructor(
    private fb: FormBuilder,
    private usuarioLogadoService: UsuarioLogadoService,
    private tipoAnimalService: TipoAnimalService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.petForm = this.fb.group({
      nome: ['', Validators.required],
      tipoAnimal: ['', Validators.required],
      anoNascimento: ['', [Validators.required, Validators.min(1900), Validators.max(new Date().getFullYear())]]
    });

    this.loadTiposAnimais();
    this.loadpetsUsuario();
  }

  loadTiposAnimais(): void {
    this.tipoAnimalService.findAll().subscribe(data => {
      this.tiposAnimais = data;
    });
  }

  loadpetsUsuario(): void {
    this.usuarioLogadoService.getPetsUsuario().subscribe(data => {
      this.petsUsuario = data;
    })
  }

  adicionarPet(): void {
    if (this.petForm.valid) {
      this.usuarioLogadoService.insertPets(this.petForm.value).subscribe(response => {
        console.log('Pet adicionado com sucesso!', response);
        this.petForm.reset();
        this.router.navigate(['/meus-pets']);
      }, error => {
        console.error('Erro ao adicionar pet', error);
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/meus-pets']);
  }
}
