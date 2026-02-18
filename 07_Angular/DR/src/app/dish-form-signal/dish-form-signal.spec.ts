import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DishFormSignal } from './dish-form-signal';

describe('DishFormSignal', () => {
  let component: DishFormSignal;
  let fixture: ComponentFixture<DishFormSignal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DishFormSignal]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DishFormSignal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
