package org.ai.recommendationservice.Service;

import org.ai.recommendationservice.Entity.Recommendation;
import org.ai.recommendationservice.Repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    @Autowired
    public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public List<Recommendation> obtenirRecommandationsParUtilisateur(Long utilisateurId) {
        return recommendationRepository.findByUtilisateurId(utilisateurId);
    }
}
