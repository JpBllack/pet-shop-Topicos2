/* import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { PetService } from '../../services/pet.service';
import { TipoAnimalService } from '../../services/TipoAnimal.service';
import { Usuario } from '../../models/Usuario';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';


@Component({
  selector: 'app-meus-pets',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './meus-pets-adicionar.component.html',
  styleUrls: ['./meus-pets-adicionar.component.css']
})
export class MeusPetsAdicionarComponent implements OnInit {
  petForm!: FormGroup;
  tiposAnimais: any[] = [];

  constructor(
    private fb: FormBuilder,
    private usuarioLogadoService: UsuarioLogadoService,
    private tipoAnimalService: TipoAnimalService
  ) { }

  ngOnInit(): void {
    this.petForm = this.fb.group({
      nome: ['', Validators.required],
      tipoAnimal: ['', Validators.required],
      anoNascimento: ['', [Validators.required, Validators.min(1900), Validators.max(new Date().getFullYear())]]
    });

    

    this.loadTiposAnimais();
  }
  
  loadTiposAnimais(): void {
    this.tipoAnimalService.findAll().subscribe(data => {
      this.tiposAnimais = data;
    });
  }
  adicionarPet(): void {
    if (this.petForm.valid) {
      this.usuarioLogadoService.insertPets(this.petForm.value).subscribe(response => {
        // Handle successful response
        console.log('Pet adicionado com sucesso!', response);
        // Optionally, reset the form after successful submission
        this.petForm.reset();
      }, error => {
        // Handle error response
        console.error('Erro ao adicionar pet', error);
      });
    }
  }

}
 */