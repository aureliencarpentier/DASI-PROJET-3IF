/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mario
 */

@Entity
public class ProfilAstral implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    String signeZodiaque;
    String signeAstrologique;
    String couleur;
    String animal;
    
    public ProfilAstral() {
    }
    
    public ProfilAstral(String signeZodiaque, String signeAstrologique, String couleur, String animal) {
        this.signeZodiaque = signeZodiaque;
        this.signeAstrologique = signeAstrologique;
        this.couleur = couleur;
        this.animal = animal;
    }

    public Long getId() {
        return id;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public String getSigneAstrologique() {
        return signeAstrologique;
    }

    public void setSigneAstrologique(String signeAstrologique) {
        this.signeAstrologique = signeAstrologique;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
      
    @Override
    public String toString() {
        return "Signe du zodiaque=" + signeZodiaque + "Signe astrologique chinois=" + signeAstrologique + "Couleur porte-bonheur=" + couleur + "Animal-Totem=" + animal;
    }
}

