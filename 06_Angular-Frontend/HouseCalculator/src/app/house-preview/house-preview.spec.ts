import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HousePreview } from './house-preview';

describe('HousePreview', () => {
  let component: HousePreview;
  let fixture: ComponentFixture<HousePreview>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HousePreview]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HousePreview);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
