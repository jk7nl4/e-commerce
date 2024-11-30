package org.ai.recommendationservice.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long utilisateurId; // User for whom the recommendation is made

    private Long produitId; // Recommended product

    private double score; // Relevance score for recommendation
}
