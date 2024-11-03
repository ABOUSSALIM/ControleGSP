package com.example.demo.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "membres_D'equipe")
public class Membre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @NotBlank(message = "ce champ et obligatoire")
    private String nom ;
    @NotBlank(message = "ce champ et obligatoire")
    private String role ;
    @ManyToOne
    @JoinColumn(name = "projet_assigne",insertable = true,updatable = true)
    private Projet projet ;

    public Membre() {
    }

    public Membre(String nom, String role, Projet projet) {
        this.nom = nom;
        this.role = role;
        this.projet = projet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
