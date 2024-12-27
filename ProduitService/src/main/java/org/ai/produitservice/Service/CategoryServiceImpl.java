package org.ai.produitservice.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.ai.produitservice.Entity.Category;
import org.ai.produitservice.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @CircuitBreaker(name = "categoryService", fallbackMethod = "fallbackAjouterCategorie")
    @Retry(name = "categoryService")
    public Category ajouterCategorie(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @CircuitBreaker(name = "categoryService", fallbackMethod = "fallbackMettreAJourCategorie")
    @Retry(name = "categoryService")
    public Category mettreAJourCategorie(Long id, Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            updatedCategory.setNom(category.getNom());
            updatedCategory.setDescription(category.getDescription());
            return categoryRepository.save(updatedCategory);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    @Override
    @CircuitBreaker(name = "categoryService", fallbackMethod = "fallbackObtenirCategorieParId")
    @Retry(name = "categoryService")
    public Category obtenirCategorieParId(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    @CircuitBreaker(name = "categoryService", fallbackMethod = "fallbackObtenirCategorieParNom")
    @Retry(name = "categoryService")
    public Category obtenirCategorieParNom(String nom) {
        return categoryRepository.findByNom(nom);
    }

    @Override
    @CircuitBreaker(name = "categoryService", fallbackMethod = "fallbackObtenirToutesLesCategories")
    @Retry(name = "categoryService")
    public List<Category> obtenirToutesLesCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @CircuitBreaker(name = "categoryService", fallbackMethod = "fallbackSupprimerCategorie")
    @Retry(name = "categoryService")
    public void supprimerCategorie(Long id) {
        categoryRepository.deleteById(id);
    }

    // Fallback methods
    public Category fallbackAjouterCategorie(Category category, Throwable t) {
        throw new RuntimeException("Fallback: Unable to add category", t);
    }

    public Category fallbackMettreAJourCategorie(Long id, Category category, Throwable t) {
        throw new RuntimeException("Fallback: Unable to update category", t);
    }

    public Category fallbackObtenirCategorieParId(Long id, Throwable t) {
        throw new RuntimeException("Fallback: Unable to get category by id", t);
    }

    public Category fallbackObtenirCategorieParNom(String nom, Throwable t) {
        throw new RuntimeException("Fallback: Unable to get category by name", t);
    }

    public List<Category> fallbackObtenirToutesLesCategories(Throwable t) {
        throw new RuntimeException("Fallback: Unable to get all categories", t);
    }

    public void fallbackSupprimerCategorie(Long id, Throwable t) {
        throw new RuntimeException("Fallback: Unable to delete category", t);
    }
}