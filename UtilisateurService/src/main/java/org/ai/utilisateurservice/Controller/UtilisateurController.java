package org.ai.utilisateurservice.Controller;

import org.ai.utilisateurservice.Entity.Utilisateur;
import org.ai.utilisateurservice.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/role/{role}")
    public List<Utilisateur> obtenirUtilisateursParRole(@PathVariable String role) {
        return utilisateurService.obtenirUtilisateursParRole(role);
    }

    @PostMapping
    public Utilisateur createUser(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createUser(utilisateur);
    }

    @PutMapping("/{id}")
    public Utilisateur updateUser(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.updateUser(id, utilisateur);
    }

    @GetMapping("/{id}")
    public Utilisateur getUserById(@PathVariable Long id) {
        return utilisateurService.getUserById(id);
    }

    @GetMapping
    public List<Utilisateur> getAllUsers() {
        return utilisateurService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        utilisateurService.deleteUser(id);
    }
}