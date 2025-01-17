package org.ai.produitservice.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;

    private double prix;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Produit() {
    }

    public Produit(String nom, String description, double prix, Category category) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.category = category;
    }
}
