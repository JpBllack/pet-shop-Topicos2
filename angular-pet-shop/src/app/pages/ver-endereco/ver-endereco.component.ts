// ver-endereco.component.ts

import { Component, OnInit } from '@angular/core';
import { Endereco } from '../../models/endereco';
import { EnderecoService } from '../../services/endereco.service';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';
import { map, Observable } from 'rxjs';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';


@Component({
  selector: 'app-ver-endereco',
  standalone: true,
  templateUrl: './ver-endereco.component.html',
  styleUrls: ['./ver-endereco.component.css'],
  imports: [CommonModule, NgFor, NgIf, RouterModule, FormsModule]
})
export class VerEnderecoComponent implements OnInit {
  enderecos: Observable<Endereco[]> | undefined;

  constructor(private enderecoService: EnderecoService, private usuarioLogadoService: UsuarioLogadoService, private router: Router) {}

  ngOnInit(): void {
    this.atualizarEnderecos();
  }

  marcarComoPrincipal(endereco: Endereco): void {
    if (!endereco.isPrincipal) {
      this.usuarioLogadoService.setEnderecoPrincipal(endereco.id).subscribe(() => {
        console.log("Endereco setado como principal")
        this.atualizarEnderecos();
      });
    }
  }

  atualizarEnderecos(): void {
    this.enderecos = this.usuarioLogadoService.getEnderecoUsuario().pipe(
      map(enderecos => {
        return enderecos.sort((a, b) => {
          if (a.isPrincipal) return -1;
          if (b.isPrincipal) return 1;
          return 0;
        });
      })
    );
  }
}
