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
public class Medium implements Serializable {

    enum Statut {
        OCCUPE,
        LIBRE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String denomination;
    private Sexe genre;
    private String presentation;
    @OneToMany
    private List<Consultation> consultations;
    

    public Medium(String denomination, Sexe genre, String presentation, List<Consultation> consultations) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
        this.consultations = consultations;
    }

    public Medium() {
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Sexe getGenre() {
        return genre;
    }

    public void setGenre(Sexe genre) {
        this.genre = genre;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

}
