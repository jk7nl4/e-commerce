package org.ai.commandeservice.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long utilisateurId;

    private LocalDateTime dateCommande;

    private double total;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "commande_id")
    private List<ProduitCommande> produits; // List of products in the order

    private String statut; // e.g., "En attente", "Confirmée", "Livrée"

    // Additional fields (e.g., shipping address) can be added
}
