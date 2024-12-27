package org.ai.produitservice.Cotroller;

import org.ai.produitservice.Entity.Produit;
import org.ai.produitservice.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService produitService;

    @Autowired
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping
    public Produit ajouterProduit(@RequestBody Produit produit) {
        return produitService.ajouterProduit(produit);
    }

    @PutMapping("/{id}")
    public Produit mettreAJourProduit(@PathVariable Long id, @RequestBody Produit produit) {
        return produitService.mettreAJourProduit(id, produit);
    }

    @GetMapping("/{id}")
    public Produit obtenirProduitParId(@PathVariable Long id) {
        return produitService.obtenirProduitParId(id);
    }

    @GetMapping
    public List<Produit> obtenirTousLesProduits() {
        return produitService.obtenirTousLesProduits();
    }

    @GetMapping("/categorie/{categorie}")
    public List<Produit> obtenirProduitsParCategorie(@PathVariable String categorie) {
        return produitService.obtenirProduitsParCategorie(categorie);
    }

    @DeleteMapping("/{id}")
    public void supprimerProduit(@PathVariable Long id) {
        produitService.supprimerProduit(id);
    }
}
