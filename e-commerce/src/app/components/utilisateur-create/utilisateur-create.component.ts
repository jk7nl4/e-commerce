import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UtilisateurService } from '../../services/utilisateur.service';
import { Utilisateur } from '../../models/utilisateur.model';

@Component({
  selector: 'app-utilisateur-create',
  templateUrl: './utilisateur-create.component.html',
  styleUrls: ['./utilisateur-create.component.css']
})
export class UtilisateurCreateComponent {
  utilisateur: Utilisateur = { nom: '', email: '', password: '', role: '' };

  constructor(private utilisateurService: UtilisateurService, private router: Router) {}

  createUser(): void {
    this.utilisateurService.createUser(this.utilisateur).subscribe(() => {
      this.router.navigate(['/']);
    });
  }
}
