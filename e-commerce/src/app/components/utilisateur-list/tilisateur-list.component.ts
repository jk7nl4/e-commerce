import { Component, OnInit } from '@angular/core';
// @ts-ignore
import { Utilisateur } from 'src/app/models/utilisateur.model';
// @ts-ignore
import { UtilisateurService } from 'src/app/services/utilisateur.service';

@Component({
  selector: 'app-utilisateur-list',
  templateUrl: './utilisateur-list.component.html',
  standalone: true,
  styleUrls: ['./utilisateur-list.component.css']
})
export class UtilisateurListComponent implements OnInit {
  utilisateurs: Utilisateur[] = [];

  constructor(private utilisateurService: UtilisateurService) {}

  ngOnInit(): void {
    this.utilisateurService.getAllUsers().subscribe((data: Utilisateur[]) => {
      this.utilisateurs = data;
    });
  }

  deleteUser(id: number): void {
    this.utilisateurService.deleteUser(id).subscribe(() => {
      this.utilisateurs = this.utilisateurs.filter(user => user.id !== id);
    });
  }
}
