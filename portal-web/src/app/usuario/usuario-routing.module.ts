import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CadastroComponent } from './feature/cadastro/cadastro.component';

const routes: Routes = [
  { path: 'cadastro', component: CadastroComponent },
  { path: '', pathMatch: 'full', redirectTo: '/usuario/cadastro' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }
