package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Consultation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    public boolean accepterConsultation(Long consultationId) throws Exception {

        boolean res = false;

        Consultation c = chercherParId(consultationId);
        if (c != null) {
            c.setStatut(Consultation.Statut.ACCEPTEE);
            c.setDateDebut(new Date());
            res = true;
        } else {
            throw new Exception("Id de consultation inconnue");
        }
        return res;
    }

    public boolean terminerConsultation(Long consultationId) throws Exception {
        boolean res = false;
        Consultation c = chercherParId(consultationId);

        if (c != null) {
            c.setStatut(Consultation.Statut.FINIE);
            c.setDateFin(new Date());
            res = true;
        } else {
            throw new Exception("Id de consultation inconnue");
        }
        return res;
    }

    public Long annulerConsultation(Long consultationId) throws Exception {

        EntityManager em = JpaUtil.obtenirContextePersistance();
        Consultation c = chercherParId(consultationId);

        if (c != null) {
            c.setStatut(Consultation.Statut.ANNULEE);
            c.setDateFin(new Date());
        } else {
            throw new Exception("Id de consultation inconnue");
        }
        return consultationId;
    }

    public Long demarrerConsultation(Long consultationId) throws Exception {

        EntityManager em = JpaUtil.obtenirContextePersistance();
        Consultation c = chercherParId(consultationId);

        if (c != null) {
            c.setStatut(Consultation.Statut.ENCOURS);
        } else {
            throw new Exception("Id de consultation inconnue");
        }

        return consultationId;
    }

    // modifier / supprimer  ... 
}
