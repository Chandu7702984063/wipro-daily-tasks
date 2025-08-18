import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageToggleComponent } from './message-toggle.component';

describe('MessageToggleComponent', () => {
  let component: MessageToggleComponent;
  let fixture: ComponentFixture<MessageToggleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MessageToggleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MessageToggleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
