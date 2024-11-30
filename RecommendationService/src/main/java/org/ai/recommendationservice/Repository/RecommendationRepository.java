package org.ai.recommendationservice.Repository;

import org.ai.recommendationservice.Entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByUtilisateurId(Long utilisateurId);
}
