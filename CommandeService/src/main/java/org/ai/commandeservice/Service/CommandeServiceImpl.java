package org.ai.commandeservice.Service;

import org.ai.commandeservice.Entity.Commande;
import org.ai.commandeservice.Repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeServiceImpl(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public Commande creerCommande(Commande commande) {
        commande.setDateCommande(java.time.LocalDateTime.now());
        commande.setStatut("En attente");
        return commandeRepository.save(commande);
    }

    @Override
    public List<Commande> obtenirCommandesParUtilisateur(Long utilisateurId) {
        return commandeRepository.findByUtilisateurId(utilisateurId);
    }

    @Override
    public Commande obtenirCommandeParId(Long id) {
        return commandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + id));
    }

    @Override
    public void mettreAJourStatutCommande(Long id, String statut) {
        Commande commande = obtenirCommandeParId(id);
        commande.setStatut(statut);
        commandeRepository.save(commande);
    }

    @Override
    public void supprimerCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}
