package org.ai.produitservice.Service;

import org.ai.produitservice.Entity.Produit;

import java.util.List;

public interface ProduitService {
    Produit ajouterProduit(Produit produit);
    Produit mettreAJourProduit(Long id, Produit produit);
    Produit obtenirProduitParId(Long id);
    List<Produit> obtenirTousLesProduits();
    List<Produit> obtenirProduitsParCategorie(String categorie);
    void supprimerProduit(Long id);
}
