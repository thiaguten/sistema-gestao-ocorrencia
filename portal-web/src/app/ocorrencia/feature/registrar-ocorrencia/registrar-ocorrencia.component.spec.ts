import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarOcorrenciaComponent } from './registrar-ocorrencia.component';

describe('RegistrarOcorrenciaComponent', () => {
  let component: RegistrarOcorrenciaComponent;
  let fixture: ComponentFixture<RegistrarOcorrenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrarOcorrenciaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrarOcorrenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
