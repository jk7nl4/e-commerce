package org.ai.utilisateurservice.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
    @CircuitBreaker(name = "utilisateurService", fallbackMethod = "fallbackCreateUser")
    @Retry(name = "utilisateurService")
    public Utilisateur createUser(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    @CircuitBreaker(name = "utilisateurService", fallbackMethod = "fallbackObtenirUtilisateursParRole")
    @Retry(name = "utilisateurService")
    public List<Utilisateur> obtenirUtilisateursParRole(String role) {
        return utilisateurRepository.findAll().stream()
                .filter(utilisateur -> utilisateur.getRole().name().equalsIgnoreCase(role))
                .toList();
    }

    @Override
    @CircuitBreaker(name = "utilisateurService", fallbackMethod = "fallbackUpdateUser")
    @Retry(name = "utilisateurService")
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
    @CircuitBreaker(name = "utilisateurService", fallbackMethod = "fallbackGetUserById")
    @Retry(name = "utilisateurService")
    public Utilisateur getUserById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Override
    @CircuitBreaker(name = "utilisateurService", fallbackMethod = "fallbackGetAllUsers")
    @Retry(name = "utilisateurService")
    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    @Override
    @CircuitBreaker(name = "utilisateurService", fallbackMethod = "fallbackDeleteUser")
    @Retry(name = "utilisateurService")
    public void deleteUser(Long id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    // Fallback methods
    public Utilisateur fallbackCreateUser(Utilisateur utilisateur, Throwable t) {
        throw new RuntimeException("Fallback: Unable to create user", t);
    }

    public List<Utilisateur> fallbackObtenirUtilisateursParRole(String role, Throwable t) {
        throw new RuntimeException("Fallback: Unable to obtain users by role", t);
    }

    public Utilisateur fallbackUpdateUser(Long id, Utilisateur utilisateur, Throwable t) {
        throw new RuntimeException("Fallback: Unable to update user", t);
    }

    public Utilisateur fallbackGetUserById(Long id, Throwable t) {
        throw new RuntimeException("Fallback: Unable to get user by id", t);
    }

    public List<Utilisateur> fallbackGetAllUsers(Throwable t) {
        throw new RuntimeException("Fallback: Unable to get all users", t);
    }

    public void fallbackDeleteUser(Long id, Throwable t) {
        throw new RuntimeException("Fallback: Unable to delete user", t);
    }
}