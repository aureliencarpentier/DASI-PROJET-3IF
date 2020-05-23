package fr.insalyon.dasi.ihm.console;

import fr.insalyon.dasi.dao.JpaUtil;
import fr.insalyon.dasi.metier.modele.Astrologue;
import fr.insalyon.dasi.metier.modele.Cartomancien;
import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Medium;
import fr.insalyon.dasi.metier.modele.ProfilAstral;
import fr.insalyon.dasi.metier.modele.Sexe;
import fr.insalyon.dasi.metier.modele.Spirite;
import fr.insalyon.dasi.metier.service.Service;
import fr.insalyon.dasi.utils.AstroAPI;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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

        //initialiserClients();            // Question 3
        //initialiserEmployes();
        //initialiserMediums();
        //testerInscriptionClient();       // Question 4 & 5
        //testerModifierClient();
        testerAuthentificationClient();

        //testerTerminerConsultation();

        //testerDemanderConsultation();
        //testerAccepterConsultation();
        //testerAnnulerConsultation();
        //testerDemarrerConsultation();
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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PredictifTP");
        EntityManager em = emf.createEntityManager();

        Service service = new Service();
        AstroAPI astro = new AstroAPI();

        String prenom = "Ada";
        Date date = new Date();
        List<String> profilAstroFromAPI;
        ProfilAstral profil;
        try {
            profilAstroFromAPI = astro.getProfil(prenom, date);
            profil = new ProfilAstral(profilAstroFromAPI.get(0), profilAstroFromAPI.get(1), profilAstroFromAPI.get(2), profilAstroFromAPI.get(3));
            service.creerProfilAstral(profil);
            Sexe sexe = Sexe.F;

            List<Consultation> consultations = new ArrayList<>();
            Client ada = new Client("Lovelace", prenom, "ada.lovelace@insa-lyon.fr", "Ada1012", sexe, "75019", "0695227164", date, profil, consultations);
            System.out.println();
            System.out.println("** Client avant persistance: ");
            afficherClient(ada);
            System.out.println();

            service.inscrireClient(ada);

            System.out.println();
            System.out.println("** Clients après persistance: ");
            afficherClient(ada);
            System.out.println();
        } catch (IOException e) {
        }

    }

    public static void testerDemanderConsultation() {

        System.out.println();
        System.out.println("**** testerDemanderConsultation() ****");
        System.out.println();

        Service service = new Service();

        Date date = new Date();
        ProfilAstral profil = new ProfilAstral("verseau", "tigre de terre", "blanc", "pigeon");

        service.creerProfilAstral(profil);

        List<Consultation> consultations = new ArrayList<>();
        Medium medium = new Medium("Escroc", Sexe.M, "je vais vous escroquer", consultations, Medium.Statut.LIBRE);
        Client pierre = new Client("dupont", "pierre", "pierrebis@insa-lyon.fr", "pierre123", Sexe.F, "06230", "0655555555", date, profil, consultations);
        Long idPierre = service.inscrireClient(pierre);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PredictifTP");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(medium);
        em.getTransaction().commit();
        emf.close();

        Long consultationId = service.demanderConsultation(pierre.getId(), medium.getId());


        if (consultationId != null) {
            System.out.println("> Succès demande consultation");
        } else {
            System.out.println("> Échec demande consultation");
        }

    }

    public static void testerAccepterConsultation() {

        System.out.println();
        System.out.println("**** testerAccepterConsultation() ****");
        System.out.println();

        Service service = new Service();

        Sexe sexe = Sexe.F;
        Date date = new Date();
        ProfilAstral profil = new ProfilAstral("verseau", "tigre de terre", "blanc", "pigeon");

        service.creerProfilAstral(profil);

        List<Consultation> consultations = new ArrayList<>();
        Medium medium = new Medium("Escrocbis", Sexe.M, "je vais vous escroquerbis", consultations, Medium.Statut.LIBRE);
        Client pierrebis = new Client("dupont", "pierre", "pierretris@insa-lyon.fr", "pierre123", sexe, "06230", "0655555555", date, profil, consultations);
        Long idPierre = service.inscrireClient(pierrebis);
        Consultation consultation = new Consultation();
        
        /* faute de "creer medium" comme service j'utilise ce bout de code pour pouvoir persister le medium, pour tester le service demanderConsultation*/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PredictifTP");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(medium);
        em.persist(consultation);
        em.getTransaction().commit();
        em.close();
        
        //consultation.setId(esclavebis.getId());
        Long consultationId = consultation.getId();
        Long idConsultationbis = service.accepterConsultation(consultationId);
        if (idConsultationbis != null) {
            System.out.println("> Succès acceptation consultation");
        } else {
            System.out.println("> Échec acceptation consultation");
        }
    }

    public static void testerTerminerConsultation() {

        System.out.println();
        System.out.println("**** testerTerminerConsultation() ****");
        System.out.println();

        Service service = new Service();

        Sexe sexe = Sexe.M;
        Date date = new Date();
        ProfilAstral profil = new ProfilAstral("cancer", "verre d'eau", "rouge", "castor");

        service.creerProfilAstral(profil);

        List<Consultation> consultations = new ArrayList<>();
        Medium medium = new Medium("salvator", Sexe.M, "je vais vous escroquerbis", consultations, Medium.Statut.LIBRE);
        Client client = new Client("jean", "jean", "jeanpatrice@insa-lyon.fr", "jean", sexe, "06230", "0655555555", date, profil, consultations);
        Long idPierre = service.inscrireClient(client);

        /* faute de "creer medium" comme service j'utilise ce bout de code pour pouvoir persister le medium, pour tester le service demanderConsultation*/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PredictifTP");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(medium);
        em.getTransaction().commit();
        em.close();
        Long clientId = client.getId();
        Long mediumId = medium.getId();
        
        Long consultationId = service.demanderConsultation(clientId, mediumId);
        Long idConsultationbis = service.accepterConsultation(consultationId);

        if (idConsultationbis != null) {
            System.out.println("> Succès acceptation consultation");
        } else {
            System.out.println("> Échec acceptation consultation");
        }

        boolean isFinished = service.terminerConsultation(consultationId);

        if (isFinished) {
            System.out.println("> Succès terminer consultation");
        } else {
            System.out.println("> Echec termliner consultation");
        }
    }

    public static void testerAnnulerConsultation() {

        System.out.println();
        System.out.println("**** testerAnnulerConsultation() ****");
        System.out.println();

        Service service = new Service();

        Sexe sexe = Sexe.F;
        Date date = new Date();
        ProfilAstral profil = new ProfilAstral("verseaua", "tigre de terrea", "blanca", "pigeona");

        service.creerProfilAstral(profil);

        List<Consultation> consultations = new ArrayList<>();
        Medium annuler = new Medium("annuler", Sexe.M, "je vais vous escroquer", consultations, Medium.Statut.LIBRE);
        //Employe annu = new Employe("annu", "mannu", "mannu@gmail.com", Sexe.F, "0695227684", "esclavemerci", 0, Employe.Statut.LIBRE, consultations);
        Client Jannule = new Client("Eric", "Couscous", "eric@gmail.cousous", "mdpmdp", sexe, "75000", "0699989796", date, profil, consultations);
        Long idJannule = service.inscrireClient(Jannule);

        /* faute de "creer medium" comme service j'utilise ce bout de code pour pouvoir persister le medium, pour tester le service demanderConsultation*/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PredictifTP");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(annuler);
        em.getTransaction().commit();
        Long JannuleId = Jannule.getId();
        Long annulerId = annuler.getId();
        Long consultationNonAnnuleeId = service.demanderConsultation(JannuleId, annulerId);
        //Long idConsultationbis = service.accepterConsultation(consultationId, esclave);       
        if (consultationNonAnnuleeId != null) {
            System.out.println("> Succès annulation consultation");
        } else {
            System.out.println("> Échec annulation consultation");
        }
        Long consultationAnnuleeId = service.annulerConsultation(consultationNonAnnuleeId);
    }

    public static void testerDemarrerConsultation() {

        System.out.println();
        System.out.println("**** testerDemarrerConsultation() ****");
        System.out.println();

        Service service = new Service();

        Date date = new Date();
        ProfilAstral profil = new ProfilAstral("CAncer", "dragon", "noir clair", "tigre des mers");

        service.creerProfilAstral(profil);

        List<Consultation> consultations = new ArrayList<>();
        Medium demarrage = new Medium("mediumage", Sexe.M, "meiolleur medium de france", consultations, Medium.Statut.LIBRE);
        Client client = new Client("jean", "lasalle", "lasalle@yahou.com", "lasalle12345", Sexe.M, "75000", "0698979996", date, profil, consultations);
        Long clientId = service.inscrireClient(client);

        /* faute de "creer medium" comme service j'utilise ce bout de code pour pouvoir persister le medium, pour tester le service demanderConsultation*/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PredictifTP");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(demarrage);
        em.getTransaction().commit();
        Long demarrageId = demarrage.getId();
        Long consultationNonDemarreeId = service.demanderConsultation(clientId, demarrageId);
        service.accepterConsultation(consultationNonDemarreeId);       
       
        Long consultationDemarreeId = service.demarrerConsultation(consultationNonDemarreeId);
         if (consultationDemarreeId != null) {
            System.out.println("> Succès demarrage consultation");
        } else {
            System.out.println("> Échec demarrage consultation");
        }
    }

    public static void initialiserEmployes() {

        System.out.println();
        System.out.println("**** initialiserEmployes() ****");
        System.out.println();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PredictifTP");
        EntityManager em = emf.createEntityManager();

        Employe e1 = new Employe("dupont", "christine", "christine@employe.com", Sexe.F, "0655555555", "christine123", 0, Employe.Statut.LIBRE, new ArrayList<Consultation>());
        Employe e2 = new Employe("Ish", "Francois", "francois@employe.com", Sexe.M, "0674555895", "francois123", 0, Employe.Statut.LIBRE, new ArrayList<Consultation>());

        try {
            em.getTransaction().begin();
            em.persist(e1);
            em.persist(e2);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        em.close();
    }
    
    private static void initialiserMediums() {
       System.out.println();
        System.out.println("**** initialiserMediums() ****");
        System.out.println();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PredictifTP");
        EntityManager em = emf.createEntityManager();
        Date d = new GregorianCalendar(2007, 1, 1).getTime();
        Astrologue m1 = new Astrologue("Henry", Sexe.M, "Je suis henry", d, "Grande ecole d'astrologues", new ArrayList<>(), Medium.Statut.LIBRE);
        Medium m2 = new Cartomancien("Patrick", Sexe.M, "Bonjour je suis Patrick votre voyant préféré", new ArrayList<>(), Medium.Statut.LIBRE);
        Medium m3 = new Spirite("Sylvie", Sexe.F, "Bonjour je suis Sylvie de la compta, j'ai réglé toutes vos factures", "Machine à écrire", new ArrayList<>(), Medium.Statut.LIBRE);
        
        try {
            em.getTransaction().begin();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        em.close();
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
        Client pierretris = new Client("dupont", "pierre", "pierre@insa-lyon.fr", "pierre123", sexe, "06230", "0655555555", date, profil, consultations);
        Long idPierre = service.inscrireClient(pierretris);
        if (idPierre != null) {
            System.out.println("> Succès inscription");
        } else {
            System.out.println("> Échec inscription");
        }
        afficherClient(pierretris);
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

        mail = "ada.lovelace@insa-lyon.fr";
        motDePasse = "Ada1012";
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
        ProfilAstral profil = new ProfilAstral("cancer", "Dragon de metal", "Turquoise", "Chat");
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
