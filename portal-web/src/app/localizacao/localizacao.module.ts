import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { SharedModule } from '../shared/shared.module';
import { LocalizacaoRoutingModule } from './localizacao-routing.module';
import { LocalizacaoComponent } from './localizacao/localizacao.component';


@NgModule({
  declarations: [
    LocalizacaoComponent
  ],
  imports: [
    CommonModule,
    LocalizacaoRoutingModule,
    SharedModule
  ]
})
export class LocalizacaoModule { }
