package org.ai.produitservice.Repository;

import org.ai.produitservice.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByNom(String nom);
}
