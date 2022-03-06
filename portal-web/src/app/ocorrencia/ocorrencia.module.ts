import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { SharedModule } from '../shared/shared.module';
import { OcorrenciaRoutingModule } from './ocorrencia-routing.module';
import { OcorrenciaComponent } from './ocorrencia/ocorrencia.component';


@NgModule({
  declarations: [
    OcorrenciaComponent
  ],
  imports: [
    CommonModule,
    OcorrenciaRoutingModule,
    SharedModule
  ]
})
export class OcorrenciaModule { }
