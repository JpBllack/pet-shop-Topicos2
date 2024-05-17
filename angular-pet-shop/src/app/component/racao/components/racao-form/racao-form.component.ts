import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { ActivatedRoute, Router, RouterModule } from '@angular/router';

import { MatToolbarModule } from '@angular/material/toolbar';
import { NgIf } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput, MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatOptionModule } from '@angular/material/core';
import { HttpClient } from '@angular/common/http';
import { Idade } from '../../../../models/idade';
import { Peso } from '../../../../models/peso';
import { Racao } from '../../../../models/racao.model';
import { TipoAnimal } from '../../../../models/tipoAnimal';
import { TipoAnimalService } from '../../../../services/TipoAnimal.service';
import { RacaoService } from '../../../../services/racao.service';
import { Marca } from '../../../../models/marca';
import { MarcaService } from '../../../../services/marca.service';



@Component({
  selector: 'app-racao-form',
  standalone: true,
  imports: [NgIf, ReactiveFormsModule, MatFormFieldModule,
    MatInputModule, MatButtonModule, MatCardModule, MatToolbarModule, RouterModule, MatSelectModule, MatOptionModule, CommonModule, MatInput],
  templateUrl: './racao-form.component.html',
  styleUrls: ['./racao-form.component.css']
})
export class RacaoFormComponent implements OnInit {

  @ViewChild('fileInput') fileInput!: ElementRef<HTMLInputElement>; /* adicionado no ultimo commit */
  formGroup: FormGroup;
  animais: TipoAnimal[] = [];
  marcas: Marca[] = [];
  pesos = Object.values(Peso).filter(value => isNaN(Number(value)));
  idades = Object.values(Idade).filter(value => isNaN(Number(value)));
  racaoId: number | null = null; /* adicionado no ultimo commit */

  constructor(formBuilder: FormBuilder,
              private racaoService: RacaoService,
              private tipoAnimalService: TipoAnimalService,
              private marcaService: MarcaService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private http: HttpClient) {

                

    this.formGroup = formBuilder.group({
      id: [null],
      sabor: ['', Validators.required],
      animal: [null, Validators.required],
      peso: [null, Validators.required],
      idade: [null, Validators.required],
      marca: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    const racaoId = this.activatedRoute.snapshot.params['id'];
    this.carregarMarcas();
    this.carregarTipos();
  
    if (racaoId) {
      this.racaoId = racaoId; /* adicionado ultimo commit */
      this.racaoService.findById(racaoId).subscribe(
        (racao) => {
          this.formGroup.patchValue({
            id: racao.id,
            sabor: racao.sabor,
            animal: racao.animal.id,
            marca: racao.marca.id,
            peso: racao.peso,
            idade: racao.idade
          });
        },
        (error) => {
          console.error('Erro ao buscar ração:', error);
        }
      );
    }
  }
  


  salvar() {
    if (this.formGroup.valid) {
      const racao = this.formGroup.value;
      if (racao.id == null) {
        this.racaoService.createRacao(racao).subscribe({
          next: () => {
            this.router.navigateByUrl('/racoes/all');
          },
          error: (err) => {
            console.log('Erro ao Incluir' + JSON.stringify(err));
          }
        });
      } else {
        this.racaoService.updateRacao(racao).subscribe({
          next: () => {
            this.router.navigateByUrl('/racoes/all');
          },
          error: (err) => {
            console.log('Erro ao Editar' + JSON.stringify(err));
          }
        });
      }
    }
  }

  excluir() {
    if (this.formGroup.valid) {
      const racao = this.formGroup.value;
      if (racao.id != null) {
        this.racaoService.deleteRacao(racao.id).subscribe({
          next: () => {
            this.router.navigateByUrl('/racoes/all');
          },
          error: (err) => {
            console.log('Erro ao Excluir' + JSON.stringify(err));
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

  carregarMarcas(){
    this.marcaService.findAll().subscribe(
      (marca: Marca[]) => {
        this.marcas = marca;
      },
      (error) => {
        console.error('Erro ao carregar marcas', error);
      }
    )
  }

  openFileInput() {
    // Abre o seletor de arquivo
    if (this.fileInput) {
        this.fileInput.nativeElement.click();
    }
}

uploadImage(file: File | null) {
  console.log('uploadImage chamado com file:', file);

  if (file && this.racaoId !== null) {
      console.log('Ração ID:', this.racaoId);

      const formData = new FormData();
      formData.append('idProduto', this.racaoId.toString());
      console.log('ID da ração adicionado ao FormData:', this.racaoId.toString());
      
      formData.append('nomeImagem', file.name);
      console.log('Nome do arquivo de imagem adicionado ao FormData:', file.name);
      
      formData.append('imagem', file);
      console.log('Arquivo de imagem adicionado ao FormData:', file);


      formData.forEach((value, key) => {
        console.log(`FormData Key: ${key}, Value: ${value}`);
    });
      // Use formData como argumento para o método uploadImage
      this.racaoService.uploadImage(formData).subscribe({
          next: (response) => {
              console.log('Imagem enviada com sucesso! Resposta:', response);
          },
          error: (error) => {
              console.error('Erro ao enviar a imagem:', error);
          }
      });
  } else {
      console.log('Arquivo ou ração ID ausente.');
  }
}


downloadImage() {
  console.log('downloadImage chamado com racaoId:', this.racaoId);

  if (this.racaoId !== null) {
      this.racaoService.downloadImage(this.racaoId).subscribe({
          next: (blob) => {
              console.log('Imagem baixada com sucesso! Blob:', blob);

              const url = window.URL.createObjectURL(blob);
              const link = document.createElement('a');
              link.href = url;
              link.download = `racao-imagem-${this.racaoId}.jpg`; // Nome do arquivo com ID da ração
              document.body.appendChild(link);
              link.click();
              document.body.removeChild(link);
          },
          error: (error) => {
              console.error('Erro ao baixar a imagem:', error);
          }
      });
  } else {
      console.log('Ração ID ausente.');
  }
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
export class RacaoFormModule { }