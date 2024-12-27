package org.ai.produitservice.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
    @CircuitBreaker(name = "produitService", fallbackMethod = "fallbackAjouterProduit")
    @Retry(name = "produitService")
    public Produit ajouterProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    @CircuitBreaker(name = "produitService", fallbackMethod = "fallbackMettreAJourProduit")
    @Retry(name = "produitService")
    public Produit mettreAJourProduit(Long id, Produit produit) {
        Optional<Produit> produitExistant = produitRepository.findById(id);
        if (produitExistant.isPresent()) {
            Produit updatedProduit = produitExistant.get();
            updatedProduit.setNom(produit.getNom());
            updatedProduit.setDescription(produit.getDescription());
            updatedProduit.setPrix(produit.getPrix());
            updatedProduit.setCategory(produit.getCategory());
            return produitRepository.save(updatedProduit);
        }
        throw new RuntimeException("Produit non trouvé avec l'ID: " + id);
    }

    @Override
    @CircuitBreaker(name = "produitService", fallbackMethod = "fallbackObtenirProduitParId")
    @Retry(name = "produitService")
    public Produit obtenirProduitParId(Long id) {
        return produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + id));
    }

    @Override
    @CircuitBreaker(name = "produitService", fallbackMethod = "fallbackObtenirTousLesProduits")
    @Retry(name = "produitService")
    public List<Produit> obtenirTousLesProduits() {
        return produitRepository.findAll();
    }

    @Override
    @CircuitBreaker(name = "produitService", fallbackMethod = "fallbackObtenirProduitsParCategorie")
    @Retry(name = "produitService")
    public List<Produit> obtenirProduitsParCategorie(String categorie) {
        return produitRepository.findByCategoryNom(categorie);
    }

    @Override
    @CircuitBreaker(name = "produitService", fallbackMethod = "fallbackSupprimerProduit")
    @Retry(name = "produitService")
    public void supprimerProduit(Long id) {
        produitRepository.deleteById(id);
    }

    // Fallback methods
    public Produit fallbackAjouterProduit(Produit produit, Throwable t) {
        throw new RuntimeException("Fallback: Unable to add product", t);
    }

    public Produit fallbackMettreAJourProduit(Long id, Produit produit, Throwable t) {
        throw new RuntimeException("Fallback: Unable to update product", t);
    }

    public Produit fallbackObtenirProduitParId(Long id, Throwable t) {
        throw new RuntimeException("Fallback: Unable to get product by id", t);
    }

    public List<Produit> fallbackObtenirTousLesProduits(Throwable t) {
        throw new RuntimeException("Fallback: Unable to get all products", t);
    }

    public List<Produit> fallbackObtenirProduitsParCategorie(String categorie, Throwable t) {
        throw new RuntimeException("Fallback: Unable to get products by category", t);
    }

    public void fallbackSupprimerProduit(Long id, Throwable t) {
        throw new RuntimeException("Fallback: Unable to delete product", t);
    }
}