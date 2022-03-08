import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from '../shared/shared.module';
import { CadastroRoutingModule } from './cadastro-routing.module';
import { CriarContaComponent } from './feature/criar-conta/criar-conta.component';


@NgModule({
  declarations: [
    CriarContaComponent
  ],
  imports: [
    CommonModule,
    CadastroRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class CadastroModule { }
