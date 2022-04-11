import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { ServicoModule } from '../servico/servico.module';
import { SharedModule } from '../shared/shared.module';
import { ListarOcorrenciaComponent } from './feature/listar-ocorrencia/listar-ocorrencia.component';
import { RegistrarOcorrenciaComponent } from './feature/registrar-ocorrencia/registrar-ocorrencia.component';
import { OcorrenciaRoutingModule } from './ocorrencia-routing.module';


@NgModule({
  declarations: [
    ListarOcorrenciaComponent,
    RegistrarOcorrenciaComponent
  ],
  imports: [
    CommonModule,
    OcorrenciaRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    ServicoModule
  ],
  exports: [
    ListarOcorrenciaComponent
  ]
})
export class OcorrenciaModule { }
