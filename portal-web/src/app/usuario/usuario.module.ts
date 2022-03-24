import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from '../shared/shared.module';
import { CadastroComponent } from './feature/cadastro/cadastro.component';
import { UsuarioRoutingModule } from './usuario-routing.module';

@NgModule({
  declarations: [
    CadastroComponent
  ],
  imports: [
    CommonModule,
    UsuarioRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ]
})
export class UsuarioModule { }
