import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoPayeurComponent } from './info-payeur.component';

describe('InfoPayeurComponent', () => {
  let component: InfoPayeurComponent;
  let fixture: ComponentFixture<InfoPayeurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InfoPayeurComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoPayeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
