import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { UltimosProdutosComponent } from "../pages/ultimos-produtos/ultimos-produtos.component";
import { FooterComponent } from '../pages/template/footer/footer.component';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Racao } from '../models/racao.model';
import { RacaoService } from '../services/racao.service';

@Component({
  selector: 'app-index',
  standalone: true,
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
  imports: [CommonModule, SlickCarouselModule, UltimosProdutosComponent, FooterComponent, RouterModule]
})
export class IndexComponent {
  imagens = [
    { src: "assets/carrosel1.jpg" },
    { src: "assets/carrosel2.png" },
    { src: "assets/carrosel3.jpg" }
  ];

  racoes: Racao[] = [];

  myConfig = {
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
    pauseOnHover: true,
    infinite: true,
    responsive: [
      {
        breakpoint: 992,
        settings: {
          arrows: true,
          infinite: true,
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      },
      {
        breakpoint: 768,
        settings: {
          arrows: true,
          infinite: true,
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      }
    ]
  };

  constructor(private racaoService: RacaoService, private route: ActivatedRoute,
    private router: Router
  ) { }


  carregarFiltro(animalId: number): void {
    this.router.navigate(['racoes/animal', animalId]);
  }

}
