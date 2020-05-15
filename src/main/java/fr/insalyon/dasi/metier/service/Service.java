package fr.insalyon.dasi.metier.service;

import fr.insalyon.dasi.dao.ClientDao;
import fr.insalyon.dasi.dao.ConsultationDao;
import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Client;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fr.insalyon.dasi.dao.EmployeDao;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.dao.ProfilAstralDao;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.metier.modele.Sexe;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Binome 3103
 */
public class Service {

    protected ClientDao clientDao = new ClientDao();
    protected EmployeDao employeDao = new EmployeDao();
    protected ProfilAstralDao profilDao = new ProfilAstralDao();
    protected ConsultationDao consultationDao = new ConsultationDao();
    protected MediumDao mediumDao = new MediumDao();

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
    
    public List<Medium> listerMediums() {
        List<Medium> resultat = null;
        JpaUtil.creerContextePersistance();
        try {
            resultat =mediumDao.listerMediums();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service listerMediums()", ex);
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

    public Long demanderConsultation(Long clientId, Long mediumId) {
        JpaUtil.creerContextePersistance();
        Long id = null;
        Medium medium = mediumDao.chercherParId(mediumId); 
        Client client = clientDao.chercherParId(clientId);
        Employe employe = employeDao.chercherEmployePourConsultation(medium.getGenre());

        // Check si il y a un employe de disponible
        if (employe == null) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Aucun employé disponible");
            return null;
        }

        Consultation consultation = new Consultation(Consultation.Statut.ENATTENTE, new Date(), null, null, null, employe, medium, client);

        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.creer(consultation);
            JpaUtil.validerTransaction();
            id = consultation.getId();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service demanderConsultation()", ex);
            JpaUtil.annulerTransaction();
        }
        JpaUtil.fermerContextePersistance();

        return id;
    }

    public Long accepterConsultation(Long consultationId) {
        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.accepterConsultation(consultationId);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service accepterConsultation()", ex);
            JpaUtil.annulerTransaction();
        }
        JpaUtil.fermerContextePersistance();

        return consultationId;
    }

    public boolean terminerConsultation(Long consultationId) {
        JpaUtil.creerContextePersistance();

        Consultation c = consultationDao.chercherParId(consultationId);

        Employe e = c.getEmploye();
        Medium m = c.getMedium();
        Client client = c.getClient();

        boolean res = false;
        try {
            res = consultationDao.terminerConsultation(consultationId);
        } catch (Exception ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, "Exception lors de l'appel au Service terminerConsultation()", ex);
        }

        if (res) {
            try {
                JpaUtil.ouvrirTransaction();
                client.getConsultations().add(c);
                e.getConsultations().add(c);
                m.getConsultations().add(c);
                e.setNombreConsultation(e.getNombreConsultation() + 1);
                JpaUtil.validerTransaction();
            } catch (Exception ex) {
                Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service terminerConsultation()", ex);
            }

            employeDao.modifierStatut(e.getId(), Employe.Statut.LIBRE);
            mediumDao.modifierStatut(m.getId(), Medium.Statut.LIBRE);
        }
        JpaUtil.fermerContextePersistance();
        return res;
    }

    public Long annulerConsultation(Long consultationId) {

        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.annulerConsultation(consultationId);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service annulerConsultation()", ex);
            JpaUtil.annulerTransaction();
        }
        JpaUtil.fermerContextePersistance();

        return consultationId;
    }

    public Long demarrerConsultation(Long consultationId) {

        JpaUtil.creerContextePersistance();
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.demarrerConsultation(consultationId);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Exception lors de l'appel au Service demarrerConsultation()", ex);
            JpaUtil.annulerTransaction();
        }
        JpaUtil.fermerContextePersistance();

        return consultationId;
    }

    public Map<Medium, Integer> nombreConsultationParMedium() {
        List<Medium> mediums = mediumDao.listerMediums();
        Map<Medium, Integer> nb = new HashMap<>();
        for (int i = 0; i < mediums.size(); i++) {
            nb.put(mediums.get(i), mediums.get(i).getConsultations().size());
        }
        return nb;
    }
    
    public Map<Employe, Integer> repartitionClientParEmploye() {
        List<Employe> employes = employeDao.listerEmployes();
        Map<Employe, Integer> nb = new HashMap<>();
        for (int i = 0; i < employes.size(); i++) {
            nb.put(employes.get(i), employes.get(i).getConsultations().size());
        }
        return nb;
    }
    
    
}
