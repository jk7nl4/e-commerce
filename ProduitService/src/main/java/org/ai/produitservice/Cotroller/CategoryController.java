package org.ai.produitservice.Cotroller;

import org.ai.produitservice.Entity.Category;
import org.ai.produitservice.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category ajouterCategorie(@RequestBody Category category) {
        return categoryService.ajouterCategorie(category);
    }

    @PutMapping("/{id}")
    public Category mettreAJourCategorie(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.mettreAJourCategorie(id, category);
    }

    @GetMapping("/{id}")
    public Category obtenirCategorieParId(@PathVariable Long id) {
        return categoryService.obtenirCategorieParId(id);
    }

    @GetMapping("/nom/{nom}")
    public Category obtenirCategorieParNom(@PathVariable String nom) {
        return categoryService.obtenirCategorieParNom(nom);
    }

    @GetMapping
    public List<Category> obtenirToutesLesCategories() {
        return categoryService.obtenirToutesLesCategories();
    }

    @DeleteMapping("/{id}")
    public void supprimerCategorie(@PathVariable Long id) {
        categoryService.supprimerCategorie(id);
    }
}
