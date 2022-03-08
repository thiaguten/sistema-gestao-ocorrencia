import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarOcorrenciaComponent } from './listar-ocorrencia.component';

describe('ListarOcorrenciaComponent', () => {
  let component: ListarOcorrenciaComponent;
  let fixture: ComponentFixture<ListarOcorrenciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarOcorrenciaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarOcorrenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
