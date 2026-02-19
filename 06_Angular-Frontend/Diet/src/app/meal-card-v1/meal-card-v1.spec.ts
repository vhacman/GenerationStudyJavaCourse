import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MealCardV1 } from './meal-card-v1';

describe('MealCardV1', () => {
  let component: MealCardV1;
  let fixture: ComponentFixture<MealCardV1>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MealCardV1]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MealCardV1);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
