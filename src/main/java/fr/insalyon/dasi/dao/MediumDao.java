/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import javax.persistence.EntityManager;
import fr.insalyon.dasi.metier.modele.Medium;
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
}
