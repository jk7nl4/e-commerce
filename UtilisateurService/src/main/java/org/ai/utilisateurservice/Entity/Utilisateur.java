package org.ai.utilisateurservice.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ai.utilisateurservice.Enum.Role;

@Getter
@Entity
@Setter

public class Utilisateur {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


}