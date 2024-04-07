package metier.model;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ifoissey
 */

@Entity
public class Eleve {
    ///Atributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    @Column(unique=true)
    private String mail;
    private String motDePasse;
    @ManyToOne
    private Etablissement etablissement;
    private int niveau;
    
    public Eleve() {
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }
    
    

    public Eleve(String nom, String prenom, String dateNaissance, String mail, String mdp, int niveau) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = mdp;
        this.niveau = niveau;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); // Adjust the date format as per your input string
            this.dateNaissance = dateFormat.parse(dateNaissance);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the parse exception appropriately
        }
  
    }


@Override
public String toString() {
    // Calcul de l'âge de l'élève
    Calendar dob = Calendar.getInstance();
    dob.setTime(dateNaissance);
    Calendar today = Calendar.getInstance();
    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
        age--;
    }
    
    // Conversion du niveau en classe
    String classe;
    switch (niveau) {
        case 0:
            classe = "Terminale";
            break;
        case 1:
            classe = "Première";
            break;
        case 2:
            classe = "Seconde";
            break;
        case 3:
            classe = "Troisième";
            break;
        case 4:
            classe = "Quatrième";
            break;
        case 5:
            classe = "Cinquième";
            break;
        case 6:
            classe = "Sixième";
            break;
        default:
            classe = "Inconnue";
    }
    
    // Récupération du nom de l'établissement
    String nomEtablissement = (etablissement != null) ? etablissement.getNomEtablissement() : "Inconnu";
    
    return "Eleve #" + id + ": " + nom + " " + prenom + " <" + mail + "> " + ", âge : " + age + " ans, en classe de " + classe + ". Etablissement : " + nomEtablissement;
}

    
    
    
    
}