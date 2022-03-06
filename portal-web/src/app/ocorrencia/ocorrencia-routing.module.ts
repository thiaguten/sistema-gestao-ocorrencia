import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { OcorrenciaComponent } from './ocorrencia/ocorrencia.component';

const routes: Routes = [
  { path: '', component: OcorrenciaComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OcorrenciaRoutingModule { }
