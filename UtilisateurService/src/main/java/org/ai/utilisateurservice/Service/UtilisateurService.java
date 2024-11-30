package org.ai.utilisateurservice.Service;

import org.ai.utilisateurservice.Entity.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    Utilisateur createUser(Utilisateur utilisateur);
    Utilisateur updateUser(Long id, Utilisateur utilisateur);
    Utilisateur getUserById(Long id);
    List<Utilisateur> getAllUsers();
    List<Utilisateur> obtenirUtilisateursParRole(String role);
    void deleteUser(Long id);
}
