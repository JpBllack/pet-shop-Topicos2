import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltroRacaoComponent } from './filtro-racao.component';

describe('FiltroRacaoComponent', () => {
  let component: FiltroRacaoComponent;
  let fixture: ComponentFixture<FiltroRacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FiltroRacaoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FiltroRacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
