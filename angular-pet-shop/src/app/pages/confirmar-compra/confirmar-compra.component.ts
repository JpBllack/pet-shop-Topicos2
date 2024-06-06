import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { ItemCompra } from '../../models/itemCompra';
import { Endereco } from '../../models/endereco';
import { Cartao } from '../../models/cartao';
import { CompraService } from '../../services/compra.service';
import { CarrinhoService } from '../../services/carrinho.service';
import { UsuarioLogadoService } from '../../services/usuarioLogado.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Compra } from '../../models/compra';
import { CarrinhoComponent } from '../carrinho/carrinho.component';
import { Usuario } from '../../models/Usuario';

@Component({
    selector: 'app-confirmar-compra',
    standalone: true,
    templateUrl: './confirmar-compra.component.html',
    styleUrls: ['./confirmar-compra.component.css'],
    imports: [CommonModule, FormsModule, HttpClientModule, RouterModule]
})
export class ConfirmarCompraComponent implements OnInit {
    itensCompra: ItemCompra[] = [];
    enderecos: Endereco[] = [];
    cartoes: Cartao[] = [];
    enderecoSelecionado!: Endereco;
    cartaoSelecionado!: Cartao;
    usuario!: Usuario;

    constructor(
        private compraService: CompraService,
        private carrinhoService: CarrinhoService,
        private usuarioLogadoService: UsuarioLogadoService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.carregarItensCarrinho();
        this.carregarDadosUsuario();
        this.carregarUsuarioLogado();
    }

    private carregarItensCarrinho(): void {
        this.itensCompra = this.carrinhoService.obter().map(itemCarrinho => ({
            nome: itemCarrinho.nome,
            precoUnitario: itemCarrinho.preco,
            quantidade: itemCarrinho.quantidade,
            racao: itemCarrinho.racao.id
        }));
    }

    private carregarDadosUsuario(): void {

        this.usuarioLogadoService.getEnderecoUsuario().subscribe(
            (enderecos: Endereco[]) => {
                //console.log('Endereços:', enderecos);
                this.enderecos = enderecos;
                this.enderecoSelecionado = this.enderecos.find(endereco => endereco.isPrincipal) || this.enderecos[0];
            },
            error => {
                console.error('Erro ao carregar endereços:', error);
            }
        );

        this.usuarioLogadoService.getCartoesUsuario().subscribe(
            (cartoes: Cartao[]) => {
                //console.log('Cartões:', cartoes);
                this.cartoes = cartoes;
                this.cartaoSelecionado = this.cartoes.find(cartao => cartao.isPrincipal) || this.cartoes[0];
            },
            error => {
                console.error('Erro ao carregar cartões:', error);
            }
        );
    }

    private carregarUsuarioLogado(): void {
        this.usuarioLogadoService.getUsuarioLogado().subscribe(
            (usuario: Usuario) => {
                this.usuario = usuario;
            },
            error => {
                console.error('Erro ao carregar usuário logado:', error);
            }
        );
    }
    


    confirmarCompra(): void {
        if (!this.usuario || !this.usuario.cpf) {
            console.error('O usuário não possui CPF cadastrado');
            alert('Por favor, cadastre seu CPF antes de concluir a compra.');
            this.router.navigate(['/alterar-info']);
            return;
        }

        if (!this.enderecoSelecionado || !this.cartaoSelecionado) {
            console.error('Endereço ou cartão de crédito não estão definidos');
            alert('Por favor, selecione um endereço e um cartão de crédito.');
            return;
        }

        if (!this.cartaoSelecionado) {
            console.error('O cartão selecionado está nulo');
            alert('Por favor, selecione um cartão de crédito.');
            return;
        }

        //console.log(this.cartaoSelecionado);
    
        if (this.itensCompra.length === 0) {
            console.error('O carrinho está vazio');
            alert('O carrinho está vazio. Adicione itens ao carrinho antes de concluir a compra.');
            return;
        }
    
        console.log(this.itensCompra);
    
        const precoTotal = this.itensCompra.reduce((total, item) => {
            return total + (item.precoUnitario * item.quantidade);
        }, 0);   
        
        this.compraService.concluirCompra(this.itensCompra, this.enderecoSelecionado, this.cartaoSelecionado).subscribe(
            (response) => {
                console.log('Compra concluída com sucesso:', response);
                this.router.navigate(['/pedidos']);
                this.carrinhoService.limparCarrinho();
            },
            (error) => {
                console.error('Erro ao concluir compra:', error);
            }
        );
        
    }

    adicionarEndereco(): void {
        this.router.navigate(['/endereco']);
    }

    adicionarCartao(): void {
        this.router.navigate(['/add-cartao']);
    }
    
    
}
