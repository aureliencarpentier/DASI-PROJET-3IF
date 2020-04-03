/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

/**
 *
 * @author Utilisateur
 */
public class Spirite extends Medium {
    
    private String support;
    
    public Spirite(String denomination, Sexe genre, String presentation, String support) {
        super(denomination, genre, presentation);
        this.support = support;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
}
