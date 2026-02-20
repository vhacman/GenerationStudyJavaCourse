import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormMoneyCost } from './form-money-cost';

describe('FormMoneyCost', () => {
  let component: FormMoneyCost;
  let fixture: ComponentFixture<FormMoneyCost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormMoneyCost]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormMoneyCost);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
