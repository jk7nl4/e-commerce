package org.ai.commandeservice.Repository;

import org.ai.commandeservice.Entity.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByUtilisateurId(Long utilisateurId);
}
