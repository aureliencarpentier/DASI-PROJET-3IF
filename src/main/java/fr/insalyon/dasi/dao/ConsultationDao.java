package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import java.util.Date;

/**
 *
 * @author DASI Team
 */
public class ConsultationDao {

    public void creer(Consultation consultation) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(consultation);
    }

    public Consultation chercherParId(Long consultationId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Consultation.class, consultationId); // renvoie null si l'identifiant n'existe pas
    }

    public List<Consultation> listerConsultations() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Consultation> query = em.createQuery("SELECT c FROM Consultation c", Consultation.class);
        return query.getResultList();
    }

    public Long accepterConsultation(Long consultationId, Employe employe) {

        JpaUtil.creerContextePersistance();
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.getTransaction().begin();
        TypedQuery<Consultation> query = em.createQuery("UPDATE Consultation c SET  c.dateDebut = :dateDebut, c.statut = :statut WHERE c.id = :consultationId", Consultation.class);
        query.setParameter("dateDebut", new Date());
        query.setParameter("statut", Consultation.Statut.ACCEPTEE);
        query.setParameter("consultationId", consultationId);
        int n = query.executeUpdate();
        try {
            if (n != 0) {
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

        return consultationId;
    }

    public boolean terminerConsultation(Long consultationId) {
        boolean res = false;
        JpaUtil.creerContextePersistance();
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.getTransaction().begin();

        TypedQuery<Consultation> query = em.createQuery("UPDATE Consultation c SET c.dateFin = :dateFin, c.statut = :statut WHERE c.id = :consultationId", Consultation.class);
        query.setParameter("statut", Consultation.Statut.FINIE);
        query.setParameter("dateFin", new Date());
        query.setParameter("consultationId", consultationId);

        int n = query.executeUpdate();
        if (n != 0) {
            res = true;
        }

        return res;
    }

    public Long annulerConsultation(Long consultationId) {

        JpaUtil.creerContextePersistance();
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.getTransaction().begin();

        TypedQuery<Consultation> query = em.createQuery("UPDATE Consultation c SET c.statut = :statut, c.dateFin = :dateFin WHERE c.id = :consultationId", Consultation.class);
        query.setParameter("statut", Consultation.Statut.ANNULEE);
        query.setParameter("dateFin", new Date());
        query.setParameter("consultationId", consultationId);
        int n = query.executeUpdate();
        try {
            if (n != 0) {
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

        return consultationId;
    }

    public Long demarrerConsultation(Long consultationId) {

        JpaUtil.creerContextePersistance();
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.getTransaction().begin();
        TypedQuery<Consultation> query = em.createQuery("UPDATE Consultation c SET c.statut = :statut WHERE c.id = :consultationId", Consultation.class);
        query.setParameter("statut", Consultation.Statut.ENCOURS);
        query.setParameter("consultationId", consultationId);
        int n = query.executeUpdate();
        try {
            if (n != 0) {
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return consultationId;
    }

    // modifier / supprimer  ... 
}
