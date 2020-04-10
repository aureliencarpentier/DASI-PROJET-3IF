package fr.insalyon.dasi.metier.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author DASI Team
 */


        
@Entity
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    private String codePostal;
    private String numeroTelephone;    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    private Sexe sexe;
    @OneToOne
    private ProfilAstral profil;
    @OneToMany
    private List<Consultation> consultations;
    
    protected Client() {
    }

    public Client(String nom, String prenom, String mail, String motDePasse, Sexe sexe, String code, String numero, Date date, ProfilAstral profil, List<Consultation> consultations) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.sexe = sexe;
        this.codePostal = code;
        this.dateNaissance = date;
        this.numeroTelephone = numero;
        this.profil = profil;
        this.consultations = consultations;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public ProfilAstral getProfil() {
        return profil;
    }

    public void setProfil(ProfilAstral profil) {
        this.profil = profil;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public Date getDate() {
        return this.dateNaissance;
    }

    public void setDate(Date date) {
        this.dateNaissance = date;
    }   
    
    public Sexe getSexe() {
        return this.sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }
    
    public String getCodePostal() {
        return this.codePostal;
    }

    public void setCodePostal(String code) {
        this.codePostal = code;
    }
    
    @Override
    public String toString() {
        return "Client : id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", "
                + "mail=" + mail + ", motDePasse=" + motDePasse + ", code postal=" 
                + codePostal + ", numero de Telephone=" + numeroTelephone + ", Sexe=" 
                + sexe + ",Date de naissance=" + dateNaissance + ",Profil Astral=" + profil + ", Consultations =" + consultations;
    }
    

}
