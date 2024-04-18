import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreaComponent } from './crea.component';

describe('CreaComponent', () => {
  let component: CreaComponent;
  let fixture: ComponentFixture<CreaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CreaComponent]
    });
    fixture = TestBed.createComponent(CreaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
