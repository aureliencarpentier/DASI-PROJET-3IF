/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.metier.modele;

import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author Utilisateur
 */
@Entity
public class Spirite extends Medium {
    
    private String support;
    
    public Spirite(String denomination, Sexe genre, String presentation, String support, List<Consultation> consultations) {
        super(denomination, genre, presentation, consultations);
        this.support = support;
    }

    public Spirite(String denomination, Sexe genre, String presentation, List<Consultation> consultations) {
        super(denomination, genre, presentation, consultations);
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
}
