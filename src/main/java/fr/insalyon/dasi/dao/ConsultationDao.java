package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import fr.insalyon.dasi.metier.modele.Employe;
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

    public int accepterConsultation(Long consultationId, Employe employe) {
        
        JpaUtil.creerContextePersistance();
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.getTransaction().begin();
        TypedQuery<Consultation> query = em.createQuery("UPDATE Consultation c SET c.employe = :employe, c.dateDebut = :dateDebut, c.statut = :statut WHERE c.id = :consultationId", Consultation.class);
        query.setParameter("employe", employe);
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
        } finally {
            em.close();
        }
<<<<<<< HEAD
        Long employeId = employe.getId();
        em.getTransaction().begin();
        TypedQuery<Employe> querybis = em.createQuery("UPDATE Employe e SET e.statut = :statutbis WHERE e.id = :employeId", Employe.class);
        querybis.setParameter("statutbis", Employe.Statut.OCCUPE);
        querybis.setParameter("employeId", employeId);
        int nbis = querybis.executeUpdate();
        if(nbis != 0) {
            em.getTransaction().commit();
        } else {
            em.getTransaction().rollback();
        }
        
=======
>>>>>>> d417824a20264666d7bf2b91d7af31cfdde5ca26
        return n;
    }
    
    public Long terminerConsultation(Long consultationId) {
        JpaUtil.creerContextePersistance();
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.getTransaction().begin();
        TypedQuery<Consultation> query = em.createQuery("UPDATE Consultation c SET c.employe = :employe, c.dateDebut = :dateDebut, c.statut = :statut WHERE c.id = :consultationId", Consultation.class);
        query.setParameter("employe", employe);
        query.setParameter("dateDebut", new Date());
        query.setParameter("statut", Consultation.Statut.FINIE);
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
        } finally {
            em.close();
        }
        return n;
        
    }
    // modifier / supprimer  ... 
}
