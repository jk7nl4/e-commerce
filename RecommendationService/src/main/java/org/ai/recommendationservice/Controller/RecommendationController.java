package org.ai.recommendationservice.Controller;

import org.ai.recommendationservice.Entity.Recommendation;
import org.ai.recommendationservice.Service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Recommendation> obtenirRecommandationsParUtilisateur(@PathVariable Long utilisateurId) {
        return recommendationService.obtenirRecommandationsParUtilisateur(utilisateurId);
    }
}
