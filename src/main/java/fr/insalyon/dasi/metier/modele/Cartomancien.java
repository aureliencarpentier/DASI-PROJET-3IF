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
public class Cartomancien extends Medium {
    public Cartomancien () {
        
    }
    public Cartomancien(String denomination, Sexe genre, String presentation, List<Consultation> consultations, Statut statut) {
        super(denomination, genre, presentation, consultations, statut);
    }
}   
