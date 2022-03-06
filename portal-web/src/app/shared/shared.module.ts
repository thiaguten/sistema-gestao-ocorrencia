import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';

import { MaterialModule } from './material/material.module';


@NgModule({
  declarations: [],
  imports: [],
  exports: [
    MaterialModule,
    FlexLayoutModule
  ]
})
export class SharedModule { }
