/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Utilisateur
 */
@Entity
public class Employe implements Serializable {
    
    enum Statut {
        OCCUPE,
        LIBRE
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
    
    protected Employe() {
        
    }
    
    
    public Employe(String nom, String prenom, String email, Sexe sexe, String numeroTelephone, String motDePasse, int nombreConsultation, Statut statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.numeroTelephone = numeroTelephone;
        this.motDePasse = motDePasse;
        this.nombreConsultation = nombreConsultation;
        this.statut = statut;
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

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
