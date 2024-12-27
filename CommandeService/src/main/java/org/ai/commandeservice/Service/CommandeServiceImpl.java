package org.ai.commandeservice.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
    @CircuitBreaker(name = "commandeService", fallbackMethod = "fallbackCreerCommande")
    @Retry(name = "commandeService")
    public Commande creerCommande(Commande commande) {
        commande.setDateCommande(java.time.LocalDateTime.now());
        commande.setStatut("En attente");
        return commandeRepository.save(commande);
    }

    @Override
    @CircuitBreaker(name = "commandeService", fallbackMethod = "fallbackObtenirCommandesParUtilisateur")
    @Retry(name = "commandeService")
    public List<Commande> obtenirCommandesParUtilisateur(Long utilisateurId) {
        return commandeRepository.findByUtilisateurId(utilisateurId);
    }

    @Override
    @CircuitBreaker(name = "commandeService", fallbackMethod = "fallbackObtenirCommandeParId")
    @Retry(name = "commandeService")
    public Commande obtenirCommandeParId(Long id) {
        return commandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + id));
    }

    @Override
    @CircuitBreaker(name = "commandeService", fallbackMethod = "fallbackMettreAJourStatutCommande")
    @Retry(name = "commandeService")
    public void mettreAJourStatutCommande(Long id, String statut) {
        Commande commande = obtenirCommandeParId(id);
        commande.setStatut(statut);
        commandeRepository.save(commande);
    }

    @Override
    @CircuitBreaker(name = "commandeService", fallbackMethod = "fallbackSupprimerCommande")
    @Retry(name = "commandeService")
    public void supprimerCommande(Long id) {
        commandeRepository.deleteById(id);
    }

    // Fallback methods
    public Commande fallbackCreerCommande(Commande commande, Throwable t) {
        throw new RuntimeException("Fallback: Unable to create order", t);
    }

    public List<Commande> fallbackObtenirCommandesParUtilisateur(Long utilisateurId, Throwable t) {
        throw new RuntimeException("Fallback: Unable to get orders by user", t);
    }

    public Commande fallbackObtenirCommandeParId(Long id, Throwable t) {
        throw new RuntimeException("Fallback: Unable to get order by id", t);
    }

    public void fallbackMettreAJourStatutCommande(Long id, String statut, Throwable t) {
        throw new RuntimeException("Fallback: Unable to update order status", t);
    }

    public void fallbackSupprimerCommande(Long id, Throwable t) {
        throw new RuntimeException("Fallback: Unable to delete order", t);
    }
}