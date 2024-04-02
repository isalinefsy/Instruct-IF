package metier.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

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
@Inheritance (strategy = InheritanceType.JOINED)
public class Intervenant implements Serializable {
    ///Atributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String tel;
    private int niveauMin; //le plus petit niveau est la 6 eme soit 6
    private int niveauMax; //le plus grand niveau est la terminale soit 0 
    private boolean etat;
    @Column(unique=true)
    private String mail;
    private String motDePasse;
    @OneToOne
    private Cours coursActuel;

    
    
    public Intervenant() {
    }

    public Intervenant(String nom, String prenom, String tel, int niveauMin, int niveauMax, String mail, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.niveauMin = niveauMin;
        this.niveauMax = niveauMax;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.coursActuel = null;
        this.etat = true;
    }

    public String getMail() {
        return mail;
    }
    public String getTel() {
        return tel;
    }

    public Long getId() {
        return id;
    }

    public Cours getCoursActuel() {
        return coursActuel;
    }

    public String getNom() {
        return nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public void setCoursActuel(Cours coursActuel) {
        this.coursActuel = coursActuel;
    }

    
    

    @Override
    public String toString() {
        return "Intervenant " + "#" + id + ": " + nom + " " + prenom + " <" + mail + "> ";
    }
    //le but de la fonction toString est de renvoyer sans jamais afficher


    
    
    
    
}
