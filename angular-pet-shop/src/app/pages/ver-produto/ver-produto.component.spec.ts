import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerProdutoComponent } from './ver-produto.component';

describe('VerProdutoComponent', () => {
  let component: VerProdutoComponent;
  let fixture: ComponentFixture<VerProdutoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerProdutoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VerProdutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
