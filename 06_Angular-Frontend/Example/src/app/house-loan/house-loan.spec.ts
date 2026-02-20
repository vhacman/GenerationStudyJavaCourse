import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HouseLoan } from './house-loan';

describe('HouseLoan', () => {
  let component: HouseLoan;
  let fixture: ComponentFixture<HouseLoan>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HouseLoan]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HouseLoan);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
