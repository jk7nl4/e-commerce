package org.ai.produitservice.Service;

import org.ai.produitservice.Entity.Category;

import java.util.List;

public interface CategoryService {
    Category ajouterCategorie(Category category);
    Category mettreAJourCategorie(Long id, Category category);
    Category obtenirCategorieParId(Long id);
    Category obtenirCategorieParNom(String nom);
    List<Category> obtenirToutesLesCategories();
    void supprimerCategorie(Long id);
}
