package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.ConsultationDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.ProfilAstralDao;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.metier.modele.Sexe;
import java.util.Date;

/**
 *
 * @author Binome 3103
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected EmployeDao employeDao = new EmployeDao();
    protected ProfilAstralDao profilDao = new ProfilAstralDao();
    protected ConsultationDao consultationDao = new ConsultationDao();


    public Long inscrireClient(Client client) {
        Long resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            if (verifierMail(client.getMail())) {
                clientDao.creer(client);
                JpaUtil.validerTransaction();
                resultat = client.getId();
            } else {
                throw new Exception(" L'email existe déjà ");
            }

        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, " Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Boolean verifierMail(String mail) {
        Boolean verification = false;
        Client c = clientDao.chercherParMail(mail);
        if (c == null) {
            verification = true;
        }
        return verification;
    }

    public Client rechercherClientParId(Long id) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.chercherParId(id);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }
    public Client rechercherClientParEmail(String email) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.chercherParMail(email);
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service rechercherClientParId(id)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client connecterClient(String mail, String motDePasse) {
        Client resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Client client = clientDao.chercherParMail(mail);
            if (client != null) {
                // Vérification du mot de passe
                if (client.getMotDePasse().equals(motDePasse)) {
                    resultat = client;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierClient(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public List<Client> listerClients() {
        List<Client> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat = clientDao.listerClients();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerClients()", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Employe connecterEmploye(String mail, String motDePasse) {
        Employe resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            // Recherche du client
            Employe employe = employeDao.chercherParMail(mail);
            if (employe != null) {
                // Vérification du mot de passe
                if (employe.getMotDePasse().equals(motDePasse)) {
                    resultat = employe;
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service authentifierEmploye(mail,motDePasse)", ex);
            resultat = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return resultat;
    }

    public Client modifierProfilClient(String mail, String nom, String prenom, String motDePasse, Sexe sexe, String code, String numero) {

        JpaUtil.creerContextePersistance();
        Client client = null;
        try {
            client = clientDao.chercherParMail(mail);
            if (client != null) {
                clientDao.modifier(mail, nom, prenom, motDePasse, sexe, code, numero);
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service modifierProfilClient()", ex);
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return client;
    }
    
    public Long creerProfilAstral(ProfilAstral profil) {
        Long res = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            profilDao.creer(profil);
            JpaUtil.validerTransaction();
            res = profil.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, " Exception lors de l'appel au Service inscrireClient(client)", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public Long demanderConsultation(Client client, Medium medium){

        Consultation consultation = new Consultation(Consultation.Statut.ENATTENTE, new Date(), null, null, null, null, medium, client);
        Long res = null;
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();            
            consultationDao.creer(consultation);
            JpaUtil.validerTransaction();
            res = consultation.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service demanderConsultation()", ex);
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
        
    public Long accepterConsultation(Long consultationId, Employe employe){


        //JpaUtil.creerContextePersistance();
        try {
            //JpaUtil.ouvrirTransaction();            
            consultationDao.accepterConsultation(consultationId,employe);
            //JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service accepterConsultation()", ex);
            //JpaUtil.annulerTransaction();
        } finally {
            //JpaUtil.fermerContextePersistance();
        }
        return consultationId;
    }
    
    public Long terminerConsultation(Long consultationId) {
        
    }
}