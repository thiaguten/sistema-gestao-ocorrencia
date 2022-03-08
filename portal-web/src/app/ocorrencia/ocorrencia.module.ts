import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from '../shared/shared.module';
import { ListarOcorrenciaComponent } from './feature/listar-ocorrencia/listar-ocorrencia.component';
import { OcorrenciaRoutingModule } from './ocorrencia-routing.module';


@NgModule({
  declarations: [
    ListarOcorrenciaComponent
  ],
  imports: [
    CommonModule,
    OcorrenciaRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ],
  exports: [
    ListarOcorrenciaComponent
  ]
})
export class OcorrenciaModule { }
