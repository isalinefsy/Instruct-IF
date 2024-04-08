package metier.model;


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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Etablissement getEtablissement() {
        return etablissement;
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
        return "Eleve " + "#" + id + ": " + nom + " " + prenom + " <" + mail + "> ";
    }
    //le but de la fonction toString est de renvoyer sans jamais afficher
    
    
    
    
}
