/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.util.Date;

/**
 *
 * @author Utilisateur
 */
public class Astrologue extends Medium {
    
    private Date promotion;
    private String formation;
    
    public Astrologue(String denomination, Sexe genre, String presentation, Date promotion, String formation) {
        super(denomination, genre, presentation);
        this.promotion = promotion;
        this.formation = formation;
    }

    public Date getPromotion() {
        return promotion;
    }

    public void setPromotion(Date promotion) {
        this.promotion = promotion;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }
    
    
    
}
