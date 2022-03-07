import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';

import { ErrorDialogComponent } from './error/error-dialog/error-dialog.component';
import { MaterialModule } from './material/material.module';


@NgModule({
  declarations: [
    ErrorDialogComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule
  ],
  exports: [
    MaterialModule,
    FlexLayoutModule,
    ErrorDialogComponent
  ]
})
export class SharedModule { }
