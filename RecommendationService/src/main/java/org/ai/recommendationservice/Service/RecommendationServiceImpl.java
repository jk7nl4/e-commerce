package org.ai.recommendationservice.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
    @CircuitBreaker(name = "recommendationService", fallbackMethod = "fallbackObtenirRecommandationsParUtilisateur")
    @Retry(name = "recommendationService")
    public List<Recommendation> obtenirRecommandationsParUtilisateur(Long utilisateurId) {
        return recommendationRepository.findByUtilisateurId(utilisateurId);
    }

    // Fallback method
    public List<Recommendation> fallbackObtenirRecommandationsParUtilisateur(Long utilisateurId, Throwable t) {
        throw new RuntimeException("Fallback: Unable to obtain recommendations for user", t);
    }
}