/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Employe;
import javax.persistence.EntityManager;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Medium.Statut;
import java.util.List;
import javax.persistence.TypedQuery;
/**
 *
 * @author Utilisateur
 */
public class MediumDao {
        public void creer(Medium medium) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(medium);
    }
        public Medium chercherParId(Long mediumId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Medium.class, mediumId); // renvoie null si l'identifiant n'existe pas
    }
    public List<Medium> listerMediums() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT c FROM Client c ORDER BY c.nom ASC, c.prenom ASC", Medium.class);
        return query.getResultList();
    }
    
    public boolean modifierStatut(Long mediumId, Statut statut) {
        boolean res = false;
        
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.getTransaction().begin();
        
        TypedQuery<Medium> query = em.createQuery("Update Medium m set m.statut = :statut WHERE m.id = :mediumId", Medium.class);
        query.setParameter("statut", statut);
        query.setParameter("mediumId", mediumId);
        
        int n = query.executeUpdate();
        if(n > 0) {
            res = true;
            em.getTransaction().commit();
        } else {
            em.getTransaction().rollback();
        }
        return res;
    }
}
