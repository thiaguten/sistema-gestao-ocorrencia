import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';

import { ErrorDialogComponent } from './component/error-dialog/error-dialog.component';
import { MaterialModule } from './material/material.module';
import { MessageSnackBarComponent } from './component/message-snack-bar/message-snack-bar.component';


@NgModule({
  declarations: [
    ErrorDialogComponent,
    MessageSnackBarComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule
  ],
  exports: [
    MaterialModule,
    FlexLayoutModule,
    ErrorDialogComponent,
    MessageSnackBarComponent
  ]
})
export class SharedModule { }
