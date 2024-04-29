import { Component, OnInit } from '@angular/core';

import { RouterModule } from '@angular/router';
import { TitleService } from '../../title.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {
  title: string = ''; 

  constructor(private titleService: TitleService) { }

  ngOnInit(): void {
    this.titleService.getTitle().subscribe(title => {
      this.title = title;
    });
  }

 menuValue:boolean = false;
 menu_icon: string = 'bi bi-list';
 
 openMenu()
 {
  this.menuValue=!this.menuValue;
  this.menu_icon = this.menuValue ? 'bi bi-x' : 'bi bi-list';
 }

 closeMenu()
 {
  this.menuValue = false;
  this.menu_icon='bi bi-list';
 }
}