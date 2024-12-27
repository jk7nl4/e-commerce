import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UtilisateurListComponent } from './components/utilisateur-list/tilisateur-list.component';
import { UtilisateurCreateComponent } from './components/utilisateur-create/utilisateur-create.component';

import { UtilisateurService } from './services/utilisateur.service';


@NgModule({
  declarations: [
    AppComponent,
    UtilisateurListComponent,
    UtilisateurCreateComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [UtilisateurService],
  bootstrap: [AppComponent]
})
export class AppModule { }
