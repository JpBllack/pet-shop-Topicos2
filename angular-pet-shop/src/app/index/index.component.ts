import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { UltimosProdutosComponent } from "../pages/ultimos-produtos/ultimos-produtos.component";

@Component({
    selector: 'app-index',
    standalone: true,
    templateUrl: './index.component.html',
    styleUrls: ['./index.component.css'],
    imports: [CommonModule, SlickCarouselModule, UltimosProdutosComponent]
})
export class IndexComponent {
  imagens = [
    { src: "assets/carrosel1.jpg" },
    { src: "assets/carrosel2.png" },
    { src: "assets/carrosel3.jpg" }
  ];

  myConfig = {
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
    pauseOnHover: true,
    infinite : true,
    responsive : [
      {
        breakpoint: 992,
        settings: {
          arrows : true,
          infinite : true,
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      },
      {
        breakpoint: 768,
        settings: {
          arrows : true,
          infinite : true,
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      }
    ]
  };
}
