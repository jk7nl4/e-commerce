package org.ai.utilisateurservice.Service;
import org.ai.utilisateurservice.Entity.Utilisateur;

import org.ai.utilisateurservice.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur createUser(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }
    @Override
    public List<Utilisateur> obtenirUtilisateursParRole(String role) {
        return utilisateurRepository.findAll().stream()
                .filter(utilisateur -> utilisateur.getRole().name().equalsIgnoreCase(role))
                .toList();
    }

    @Override
    public Utilisateur updateUser(Long id, Utilisateur utilisateur) {
        Optional<Utilisateur> existingUser = utilisateurRepository.findById(id);
        if (existingUser.isPresent()) {
            Utilisateur updatedUser = existingUser.get();
            updatedUser.setNom(utilisateur.getNom());
            updatedUser.setEmail(utilisateur.getEmail());
            updatedUser.setPassword(utilisateur.getPassword());
            updatedUser.setRole(utilisateur.getRole());
            return utilisateurRepository.save(updatedUser);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    @Override
    public Utilisateur getUserById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }
}
