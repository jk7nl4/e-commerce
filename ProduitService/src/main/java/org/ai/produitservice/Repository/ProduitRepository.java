package org.ai.produitservice.Repository;

import org.ai.produitservice.Entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByCategoryNom(String nom);
}
