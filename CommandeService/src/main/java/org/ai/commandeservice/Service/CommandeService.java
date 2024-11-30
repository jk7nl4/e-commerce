package org.ai.commandeservice.Service;

import org.ai.commandeservice.Entity.Commande;

import java.util.List;

public interface CommandeService {
    Commande creerCommande(Commande commande);
    List<Commande> obtenirCommandesParUtilisateur(Long utilisateurId);
    Commande obtenirCommandeParId(Long id);
    void mettreAJourStatutCommande(Long id, String statut);
    void supprimerCommande(Long id);
}
