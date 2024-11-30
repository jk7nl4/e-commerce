package org.ai.produitservice.Service;

import org.ai.produitservice.Entity.Produit;
import org.ai.produitservice.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public Produit ajouterProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public Produit mettreAJourProduit(Long id, Produit produit) {
        Optional<Produit> produitExistant = produitRepository.findById(id);
        if (produitExistant.isPresent()) {
            Produit updatedProduit = produitExistant.get();
            updatedProduit.setNom(produit.getNom());
            updatedProduit.setDescription(produit.getDescription());
            updatedProduit.setPrix(produit.getPrix());
            updatedProduit.setCategorie(produit.getCategorie());
            return produitRepository.save(updatedProduit);
        }
        throw new RuntimeException("Produit non trouvé avec l'ID: " + id);
    }

    @Override
    public Produit obtenirProduitParId(Long id) {
        return produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + id));
    }

    @Override
    public List<Produit> obtenirTousLesProduits() {
        return produitRepository.findAll();
    }

    @Override
    public List<Produit> obtenirProduitsParCategorie(String categorie) {
        return produitRepository.findByCategorie(categorie);
    }

    @Override
    public void supprimerProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
