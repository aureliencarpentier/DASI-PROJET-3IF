/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

/**
 *
 * @author mario
 */
public class ProfilAstral {
    String signeZodiaque;
    String signeAstrologique;
    String couleur;
    String animal;

    public ProfilAstral(String signeZodiaque, String signeAstrologique, String couleur, String animal) {
        this.signeZodiaque = signeZodiaque;
        this.signeAstrologique = signeAstrologique;
        this.couleur = couleur;
        this.animal = animal;
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
}

