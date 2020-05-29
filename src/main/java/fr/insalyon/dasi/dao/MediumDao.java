/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import javax.persistence.EntityManager;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.Medium.Statut;
import fr.insalyon.dasi.metier.modele.Sexe;
import fr.insalyon.dasi.metier.modele.Spirite;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public List<Medium> listerMediums(Sexe sexe, Boolean cartomanciens, Boolean spirites, Boolean astrologues) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        //System.out.println("sexe 3:" + sexe);
        TypedQuery<Medium> query = em.createQuery("SELECT m FROM Medium m WHERE m.genre=:sexechercher", Medium.class);
        query.setParameter("sexechercher", sexe);
        List<Medium> results = query.getResultList();
        ArrayList<Medium> mediumsResultat = new ArrayList<>();

        for (Medium m : results) {
            if (cartomanciens) {
                if (m instanceof Cartomancien) {
                    mediumsResultat.add(m);
                }
            }
            if (astrologues) {
                if (m instanceof Astrologue) {
                    mediumsResultat.add(m);
                }
            }
            if (spirites) {
                if (m instanceof Spirite) {
                    mediumsResultat.add(m);
                }
            }
        }
        return mediumsResultat;

    }

    public List<Medium> listerMediums() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Medium> query = em.createQuery("SELECT m FROM Medium m", Medium.class);
        return query.getResultList();

    }

    public void modifierStatut(Long mediumId, Statut statut) {
        Medium m = chercherParId(mediumId);
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.getTransaction().begin();
        m.setStatut(statut);
        em.getTransaction().commit();
    }

}
