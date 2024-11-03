package com.example.demo.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="projets")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @NotBlank(message = "ce champ et obligatoire")
   private String nom ;
    @NotBlank(message = "ce champ et obligatoire")
   private String description ;
    @NotNull(message = "La date de début est obligatoire")
   @Temporal(TemporalType.DATE)
   private LocalDate dateDebut ;
    @NotNull(message = "La date de fin est obligatoire")
    @Temporal(TemporalType.DATE)
    private LocalDate dateFin ;
    @OneToMany(mappedBy ="projet",cascade = CascadeType.ALL)
    private List<Membre> membres ;
    public Projet() {
    }
    public Projet(String nom, String description, LocalDate dateDebut, LocalDate dateFin, List<Membre> membres) {
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.membres = membres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "ce champ et obligatoire") String getNom() {
        return nom;
    }

    public void setNom(@NotBlank(message = "ce champ et obligatoire") String nom) {
        this.nom = nom;
    }

    public @NotBlank(message = "ce champ et obligatoire") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "ce champ et obligatoire") String description) {
        this.description = description;
    }

    public @NotNull(message = "La date de début est obligatoire") LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(@NotNull(message = "La date de début est obligatoire") LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public @NotNull(message = "La date de fin est obligatoire") LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(@NotNull(message = "La date de fin est obligatoire") LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public List<Membre> getMembres() {
        return membres;
    }

    public void setMembres(List<Membre> membres) {
        this.membres = membres;
    }
}
