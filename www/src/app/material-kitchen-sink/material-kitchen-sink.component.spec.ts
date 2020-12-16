import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialKitchenSinkComponent } from './material-kitchen-sink.component';

describe('MaterialKitchenSinkComponent', () => {
  let component: MaterialKitchenSinkComponent;
  let fixture: ComponentFixture<MaterialKitchenSinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MaterialKitchenSinkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MaterialKitchenSinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
