/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Employe.Statut;

/**
 *
 * @author Utilisateur
 */
public class EmployeDao {
    
    public void creer(Employe employe) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(employe);
    }
        
    public Employe chercherParId(Long employeId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Employe.class, employeId); // renvoie null si l'identifiant n'existe pas
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
        TypedQuery<Employe> query = em.createQuery("SELECT e FROM Employe e ORDER BY e.nombreConsultation ASC, e.nom ASC", Employe.class);
        return query.getResultList();
    }
    
    public boolean modifierStatut(Long employeId, Statut statut) {
        boolean res = false;
        
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.getTransaction().begin();
        
        TypedQuery<Employe> query = em.createQuery("Update Employe e set e.statut = :statut WHERE e.id = :employeId", Employe.class);
        query.setParameter("statut", statut);
        query.setParameter("employeId", employeId);
        
        int n = query.executeUpdate();
        if(n > 0) {
            res = true;
            em.getTransaction().commit();
        } else {
            em.getTransaction().rollback();
        }
        return res;
    }
    
    /*public Consultation chercherConsultationEnCours(String employeEmail) {
        
    }*/
}
