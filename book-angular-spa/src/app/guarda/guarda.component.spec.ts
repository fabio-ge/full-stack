import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuardaComponent } from './guarda.component';

describe('GuardaComponent', () => {
  let component: GuardaComponent;
  let fixture: ComponentFixture<GuardaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GuardaComponent]
    });
    fixture = TestBed.createComponent(GuardaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
