package org.ai.commandeservice.Controller;

import org.ai.commandeservice.Entity.Commande;
import org.ai.commandeservice.Service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    @Autowired
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @PostMapping
    public Commande creerCommande(@RequestBody Commande commande) {
        return commandeService.creerCommande(commande);
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Commande> obtenirCommandesParUtilisateur(@PathVariable Long utilisateurId) {
        return commandeService.obtenirCommandesParUtilisateur(utilisateurId);
    }

    @GetMapping("/{id}")
    public Commande obtenirCommandeParId(@PathVariable Long id) {
        return commandeService.obtenirCommandeParId(id);
    }

    @PutMapping("/{id}/statut")
    public void mettreAJourStatutCommande(@PathVariable Long id, @RequestParam String statut) {
        commandeService.mettreAJourStatutCommande(id, statut);
    }

    @DeleteMapping("/{id}")
    public void supprimerCommande(@PathVariable Long id) {
        commandeService.supprimerCommande(id);
    }
}
