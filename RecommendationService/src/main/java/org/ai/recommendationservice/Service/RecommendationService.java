package org.ai.recommendationservice.Service;

import org.ai.recommendationservice.Entity.Recommendation;

import java.util.List;

public interface RecommendationService {
    List<Recommendation> obtenirRecommandationsParUtilisateur(Long utilisateurId);
}
