import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerEnderecoComponent } from './ver-endereco.component';

describe('VerEnderecoComponent', () => {
  let component: VerEnderecoComponent;
  let fixture: ComponentFixture<VerEnderecoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerEnderecoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VerEnderecoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
