import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { SharedModule } from '../shared/shared.module';
import { TipoServicoPipe } from './pipe/tipo-servico.pipe';

@NgModule({
  declarations: [
    TipoServicoPipe
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    TipoServicoPipe
  ]
})
export class ServicoModule { }
