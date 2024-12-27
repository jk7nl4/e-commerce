import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UtilisateurListComponent } from './components/utilisateur-list/tilisateur-list.component';
import { UtilisateurCreateComponent } from './components/utilisateur-create/utilisateur-create.component';

const routes: Routes = [
  { path: '', component: UtilisateurListComponent },
  { path: 'create', component: UtilisateurCreateComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
