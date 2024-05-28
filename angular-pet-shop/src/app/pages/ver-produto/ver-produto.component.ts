import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RacaoService } from '../../services/racao.service';
import { Racao } from '../../models/racao.model';
import { CarrinhoService } from '../../services/carrinho.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ItemCarrinho } from '../../models/itemcarrinho.model';
import { FooterComponent } from '../template/footer/footer.component';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { Peso, PesoLabel } from '../../models/peso';
import { Idade } from '../../models/idade';



@Component({
  selector: 'app-ver-produto',
  standalone: true,
  imports: [FormsModule, CommonModule, FooterComponent,MatIcon,MatIconModule],
  templateUrl: './ver-produto.component.html',
  styleUrls: ['./ver-produto.component.css']
})
export class VerProdutoComponent implements OnInit {
  produto: Racao | undefined;
  quantidade: number = 1; // Default to 1

  constructor(
    private route: ActivatedRoute,
    private racaoService: RacaoService,
    private carrinhoService: CarrinhoService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.racaoService.getRacaoById(+id).subscribe((data: Racao) => {
        this.produto = data;
      });
    }
  }

  getImagemPath(imagem: string): string {
    return `http://localhost:8080/quarkus/images/produto/${imagem}`;
  }

  adicionarAoCarrinho() {
    if (this.produto && this.quantidade > 0) {
      const itemCarrinho: ItemCarrinho = {
        id: this.produto.id,
        nome: this.produto.nome,
        imagem: this.getImagemPath(this.produto.imagem),
        preco: this.produto.preco,
        quantidade: this.quantidade,
        frequencia: 0 // ou outro valor padrão para a frequência
      };
      this.carrinhoService.adicionar(itemCarrinho);
      this.snackBar.open('Produto adicionado ao carrinho!', 'Fechar', {
        duration: 2000,
        verticalPosition: 'top',
        horizontalPosition: 'center'
      });
    } else {
      this.snackBar.open('A quantidade deve ser maior que zero', 'Fechar', {
        duration: 2000,
        verticalPosition: 'top',
        horizontalPosition: 'center'
      });
    }
  }

  aumentarQuantidade() {
    this.quantidade += 1;
  }

  diminuirQuantidade() {
    if (this.quantidade > 1) {
      this.quantidade -= 1;
    }
  }

  /* getPesoLabel(peso: Peso): string {
  console.log('Valor de entrada (peso):', peso);
  
  // Verifica se o valor de Peso está dentro do intervalo esperado
  if (peso >= 1 && peso <= 6) {
    const label = PesoLabel[peso];

    console.log('Label encontrada:', label);
    return label;
  } else {
    console.log('Peso desconhecido');
    return 'Peso desconhecido';
  }
} */

getPesoLabel(peso: Peso | { label: string }): string {
  console.log('Peso:', peso);
  
  // Verifica se peso é uma string diretamente
  if (typeof peso === 'string') {
    return peso;
  } 
  // Verifica se peso é um objeto e se possui a propriedade 'label'
  else if (typeof peso === 'object' && 'label' in peso) {
    return peso.label;
  } 
  // Se nenhum dos casos acima for verdadeiro, retorna 'Status desconhecido'
  else {
    return 'Status desconhecido';
  }
}


getIdadeLabel(idade: Idade | { label: string }): string {
  console.log('Idade', idade);
  
  // Verifica se idade é uma string diretamente
  if (typeof idade === 'string') {
    return idade;
  } 
  // Verifica se idade é um objeto e se possui a propriedade 'label'
  else if (typeof idade === 'object' && 'label' in idade) {
    return idade.label;
  } 
  // Se nenhum dos casos acima for verdadeiro, retorna 'Status desconhecido'
  else {
    return 'Status desconhecido';
  }
}
}

