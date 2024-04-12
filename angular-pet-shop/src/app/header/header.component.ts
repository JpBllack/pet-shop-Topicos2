import { Component, OnInit } from '@angular/core';
import { TitleService } from '../title.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  title: string = ''; 

  constructor(private titleService: TitleService) { }

  ngOnInit(): void {
    this.titleService.getTitle().subscribe(title => {
      this.title = title;
    });
  }
}
