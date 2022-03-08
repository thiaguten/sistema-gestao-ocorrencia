import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { OcorrenciaModule } from '../ocorrencia/ocorrencia.module';
import { SharedModule } from '../shared/shared.module';
import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home/home.component';


@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    SharedModule,
    OcorrenciaModule
  ]
})
export class HomeModule { }
