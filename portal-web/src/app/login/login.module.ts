import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { SharedModule } from '../shared/shared.module';
import { LoginInfoComponent } from './login-info/login-info.component';



@NgModule({
  declarations: [
    LoginInfoComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  exports: [
    LoginInfoComponent
  ]
})
export class LoginModule { }
