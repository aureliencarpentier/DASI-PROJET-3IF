package fr.insalyon.dasi.dao;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Sexe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author DASI Team
 */
public class ClientDao {

    public void creer(Client client) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.persist(client);
    }

    public Boolean verifierEmailClient(String adresseMail) {
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

    public Client chercherParId(Long clientId) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        return em.find(Client.class, clientId); // renvoie null si l'identifiant n'existe pas
    }

    public Client chercherParMail(String clientMail) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.mail = :mail", Client.class);
        query.setParameter("mail", clientMail); // correspond au paramètre ":mail" dans la requête
        List<Client> clients = query.getResultList();
        Client result = null;
        if (!clients.isEmpty()) {
            result = clients.get(0); // premier de la liste
        }
        return result;
    }

    public List<Client> listerClients() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c ORDER BY c.nom ASC, c.prenom ASC", Client.class);
        return query.getResultList();
    }

    public int modifier(String mail, String nom, String prenom, String motDePasse, Sexe sexe, String code, String numeroTelephone) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        em.getTransaction().begin();
        TypedQuery<Client> query = em.createQuery("UPDATE Client c SET c.nom = :nom, c.prenom = :prenom, c.motDePasse = :motDePasse, c.sexe = :sexe, c.codePostal = :code, c.numeroTelephone = :numeroTelephone WHERE c.mail = :mail", Client.class);
        query.setParameter("mail", mail);
        query.setParameter("nom", nom);
        query.setParameter("prenom", prenom);
        query.setParameter("motDePasse", motDePasse);
        query.setParameter("sexe", sexe);
        query.setParameter("code", code);
        query.setParameter("numeroTelephone", numeroTelephone);
        int n = query.executeUpdate();
        if(n != 0) {
            em.getTransaction().commit();
        } else {
            em.getTransaction().rollback();
        }
        return n;
    }

    // modifier / supprimer  ... 
}
