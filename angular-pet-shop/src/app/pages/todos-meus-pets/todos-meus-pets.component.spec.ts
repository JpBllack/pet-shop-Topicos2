import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodosMeusPetsComponent } from './todos-meus-pets.component';

describe('TodosMeusPetsComponent', () => {
  let component: TodosMeusPetsComponent;
  let fixture: ComponentFixture<TodosMeusPetsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodosMeusPetsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TodosMeusPetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
