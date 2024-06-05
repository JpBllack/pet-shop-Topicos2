import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UltimosProdutosComponent } from './ultimos-produtos.component';

describe('UltimosProdutosComponent', () => {
  let component: UltimosProdutosComponent;
  let fixture: ComponentFixture<UltimosProdutosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UltimosProdutosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UltimosProdutosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
