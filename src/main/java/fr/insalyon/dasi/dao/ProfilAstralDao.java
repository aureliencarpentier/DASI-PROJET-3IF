package fr.insalyon.dasi.dao;

import javax.persistence.EntityManager;
import fr.insalyon.dasi.metier.modele.ProfilAstral;

/**
 *
 * @author DASI Team
 */
public class ProfilAstralDao {
    
    public void creer(ProfilAstral profil) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(profil);
    }
    
    public ProfilAstral chercherParId(Long profilId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(ProfilAstral.class, profilId); 
    }
    
    // modifier / supprimer  ... 
}
