package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.dao.MediumDao;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.metier.modele.Sexe;
import fr.insalyon.dasi.metier.service.Service;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author DASI Team
 */
public class Main {

    public static void main(String[] args) {

        // TODO : Pensez à créer une unité de persistance "DASI-PU" et à vérifier son nom dans la classe JpaUtil
        // Contrôlez l'affichage du log de JpaUtil grâce à la méthode log de la classe JpaUtil
        JpaUtil.init();

        initialiserClients();            // Question 3
        testerInscriptionClient();       // Question 4 & 5
        testerModifierClient();
        testerDemanderConsultation();
        //testerRechercheClient();         // Question 6
        //testerListeClients();            // Question 7
        //testerAuthentificationClient();  // Question 8
        //saisirInscriptionClient();       // Question 9
        //saisirRechercheClient();

        JpaUtil.destroy();
    }

    public static void afficherClient(Client client) {
        System.out.println("-> " + client);
    }

    public static void initialiserClients() {

        System.out.println();
        System.out.println("**** initialiserClients() ****");
        System.out.println();

        Service service = new Service();

        Sexe sexe = Sexe.F;
        Date date = new Date();
        ProfilAstral profil = new ProfilAstral("cancer", "Dragon de metal", "Turquoise", "Chatte");
        
        service.creerProfilAstral(profil);
        
        List<Consultation> consultations = new ArrayList<>();
        Client ada = new Client("Lovelace", "Ada", "ada.lovelace@insa-lyon.fr", "Ada1012", sexe, "75019", "0695227164", date, profil, consultations);

        System.out.println();
        System.out.println("** Client avant persistance: ");
        afficherClient(ada);
        System.out.println();

        service.inscrireClient(ada);

        System.out.println();
        System.out.println("** Clients après persistance: ");
        afficherClient(ada);
        System.out.println();
    }

    public static void testerDemanderConsultation() {

        System.out.println();
        System.out.println("**** testerDemanderConsultation() ****");
        System.out.println();


        Service service = new Service();

        Sexe sexe = Sexe.F;
        Date date = new Date();
        ProfilAstral profil = new ProfilAstral("verseau", "tigre de terre", "blanc", "pigeon");

        service.creerProfilAstral(profil);
        
        List<Consultation> consultations = new ArrayList<>();
        Medium escroc = new Medium("Escroc", Sexe.M, "je vais vous escroquer", consultations);
        Client pierre = new Client("dupont", "pierre", "pierrebis@insa-lyon.fr", "pierre123", sexe, "06230", "0655555555", date, profil, consultations);
        Long idPierre = service.inscrireClient(pierre);

        /* faute de "creer medium" comme service j'utilise ce bout de code pour pouvoir persister le medium, pour tester le service demanderConsultation*/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PredictifTP");
        EntityManager em = emf.createEntityManager();        
        em.getTransaction().begin();
        em.persist(escroc);
        em.getTransaction().commit();
        
        Long idConsultation = service.demanderConsultation(pierre, escroc);
        if (idConsultation != null) {
            System.out.println("> Succès demande consultation");
        } else {
            System.out.println("> Échec demande consultation");
        }
        

    }

    public static void testerInscriptionClient() {

        System.out.println();
        System.out.println("**** testerInscriptionClient() ****");
        System.out.println();
        

        Service service = new Service();

        Sexe sexe = Sexe.F;
        Date date = new Date();
        ProfilAstral profil = new ProfilAstral("verseau", "tigre de terre", "blanc", "pigeon");
        
        service.creerProfilAstral(profil);
        
        List<Consultation> consultations = new ArrayList<>();
        Client pierre = new Client("dupont", "pierre", "pierre@insa-lyon.fr", "pierre123", sexe, "06230", "0655555555", date, profil, consultations);
        Long idPierre = service.inscrireClient(pierre);
        if (idPierre != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(pierre);
    }
    
    public static void testerRechercheClient() {

        System.out.println();
        System.out.println("**** testerRechercheClient() ****");
        System.out.println();

        Service service = new Service();
        long id;
        Client client;

        id = 1;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 3;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client non-trouvé");
        }

        id = 17;
        System.out.println("** Recherche du Client #" + id);
        client = service.rechercherClientParId(id);
        if (client != null) {
            afficherClient(client);
        } else {
            System.out.println("=> Client #" + id + " non-trouvé");
        }
    }

    public static void testerListeClients() {

        System.out.println();
        System.out.println("**** testerListeClients() ****");
        System.out.println();

        Service service = new Service();
        List<Client> listeClients = service.listerClients();
        System.out.println("*** Liste des Clients");
        if (listeClients != null) {
            for (Client client : listeClients) {
                afficherClient(client);
            }
        } else {
            System.out.println("=> ERREUR...");
        }
    }

    public static void testerAuthentificationClient() {

        System.out.println();
        System.out.println("**** testerAuthentificationClient() ****");
        System.out.println();

        Service service = new Service();
        Client client;
        String mail;
        String motDePasse;

        mail = "pierre@insa-lyon.fr";
        motDePasse = "pierre123";
        client = service.connecterClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }

        mail = "etudiant.fictif@insa-lyon.fr";
        motDePasse = "********";
        client = service.connecterClient(mail, motDePasse);
        if (client != null) {
            System.out.println("Authentification réussie avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
            afficherClient(client);
        } else {
            System.out.println("Authentification échouée avec le mail '" + mail + "' et le mot de passe '" + motDePasse + "'");
        }
    }

    public static void saisirInscriptionClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("Appuyer sur Entrée pour passer la pause...");
        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** NOUVELLE INSCRIPTION **");
        System.out.println("**************************");
        System.out.println();

        String nom = Saisie.lireChaine("Nom ? ");
        String prenom = Saisie.lireChaine("Prénom ? ");
        String mail = Saisie.lireChaine("Mail ? ");
        String motDePasse = Saisie.lireChaine("Mot de passe ? ");

        Sexe sexe = Sexe.F;
        Date date = Date.from(Instant.MIN);
        ProfilAstral profil = new ProfilAstral("cancer", "Dragon de metal", "Turquoise", "Chatte");
        List<Consultation> consultations = new ArrayList<>();
        Client client = new Client(nom, prenom, mail, motDePasse, sexe, "75019", "0695227164", date, profil, consultations);
        Long idClient = service.inscrireClient(client);

        if (idClient != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(client);
    }
    
    public static void testerModifierClient() {
        System.out.println("");
        System.out.println("** Tester modif client **");
        System.out.println("");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PredictifTP");
        EntityManager em = emf.createEntityManager();
        
        Service service = new Service();
        
        ProfilAstral p = new ProfilAstral("cheval", "cancer", "bleu", "chevre");
        service.creerProfilAstral(p);
        
        List<Consultation> consultations = new ArrayList<>();
        
        Client c = new Client("jean", "pierre", "salut@gmail.com", "jeanpierre", Sexe.M, "06000", "0601020304", new Date(), p, consultations);
        service.inscrireClient(c);
        
        System.out.println("Client avant modifs : ");
        afficherClient(c);
        
        service.modifierProfilClient(c.getMail(), "marian", "jojo", c.getMotDePasse(), Sexe.M, c.getMotDePasse(), c.getNumeroTelephone());
        
        System.out.println("Client après modifs");
        c = service.rechercherClientParEmail(c.getMail());
        afficherClient(c);
    }

    public static void saisirRechercheClient() {
        Service service = new Service();

        System.out.println();
        System.out.println("*********************");
        System.out.println("** MENU INTERACTIF **");
        System.out.println("*********************");
        System.out.println();

        Saisie.pause();

        System.out.println();
        System.out.println("**************************");
        System.out.println("** RECHERCHE de CLIENTS **");
        System.out.println("**************************");
        System.out.println();
        System.out.println();
        System.out.println("** Recherche par Identifiant:");
        System.out.println();

        Integer idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        while (idClient != 0) {
            Client client = service.rechercherClientParId(idClient.longValue());
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client #" + idClient + " non-trouvé");
            }
            System.out.println();
            idClient = Saisie.lireInteger("Identifiant ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("********************************");
        System.out.println("** AUTHENTIFICATION de CLIENT **");
        System.out.println("********************************");
        System.out.println();
        System.out.println();
        System.out.println("** Authentifier Client:");
        System.out.println();

        String clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");

        while (!clientMail.equals("0")) {
            String clientMotDePasse = Saisie.lireChaine("Mot de passe ? ");
            Client client = service.connecterClient(clientMail, clientMotDePasse);
            if (client != null) {
                afficherClient(client);
            } else {
                System.out.println("=> Client non-authentifié");
            }
            clientMail = Saisie.lireChaine("Mail ? [0 pour quitter] ");
        }

        System.out.println();
        System.out.println("*****************");
        System.out.println("** AU REVOIR ! **");
        System.out.println("*****************");
        System.out.println();

    }
}
