/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import fr.insalyon.dasi.metier.modele.Employe;

/**
 *
 * @author Utilisateur
 */
public class EmployeDao {
    
    public Boolean verifierEmailClient (String adresseMail){
        Boolean verification = false;
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.mail = :mail", Client.class);
        query.setParameter("mail", adresseMail); // correspond au paramètre ":mail" dans la requête
        List<Client> clients = query.getResultList();
        if (!clients.isEmpty()) {
            verification = true;
        }
        return verification;
    }
    
    public Employe chercherParMail(String employeEmail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e WHERE e.email = :email", Employe.class);
        query.setParameter("email", employeEmail); // correspond au paramètre ":mail" dans la requête
        List<Employe> employe = query.getResultList();
        Employe result = null;
        if (!employe.isEmpty()) {
            result = employe.get(0); // premier de la liste
        }
        return result;
    }
    
    public List<Employe> listerEmployes() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e ORDER BY e.nom ASC, e.prenom ASC", Employe.class);
        return query.getResultList();
    }
    
    /*public Consultation chercherConsultationEnCours(String employeEmail) {
        
    }*/
}
