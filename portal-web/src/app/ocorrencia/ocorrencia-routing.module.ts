import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegistrarOcorrenciaComponent } from './feature/registrar-ocorrencia/registrar-ocorrencia.component';

const routes: Routes = [
  { path: 'registrar', component: RegistrarOcorrenciaComponent },
  { path: '', pathMatch: 'full', redirectTo: '/ocorrencia/registrar' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OcorrenciaRoutingModule { }
