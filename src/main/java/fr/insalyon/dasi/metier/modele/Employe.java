/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 *
 * @author Utilisateur
 */
@Entity
public class Employe implements Serializable {
    
    enum Statut{
        OCCUPE,
        LIBRE;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    @Column(unique=true)
    private String email;
    private Sexe sexe;
    private String numeroTelephone;
    private String motDePasse;
    private int nombreConsultation;
    private Statut statut;
    @OneToMany
    private List<Consultation> consultations;
    
    
    
    public Employe(String nom, String prenom, String email, Sexe sexe, String numeroTelephone, String motDePasse, int nombreConsultation, Statut statut, List<Consultation> consultations) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.numeroTelephone = numeroTelephone;
        this.motDePasse = motDePasse;
        this.nombreConsultation = nombreConsultation;
        this.statut = statut;
        this.consultations = consultations;
    }

    public Employe() {
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Enum getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getNombreConsultation() {
        return nombreConsultation;
    }

    public void setNombreConsultation(int nombreConsultation) {
        this.nombreConsultation = nombreConsultation;
    }

    public Enum getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
