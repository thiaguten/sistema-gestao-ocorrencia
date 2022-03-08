import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CriarContaComponent } from './feature/criar-conta/criar-conta.component';

const routes: Routes = [
  { path: '', component: CriarContaComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CadastroRoutingModule { }
