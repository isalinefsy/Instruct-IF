package metier.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import java.io.Serializable;

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
public class Intervenant implements Serializable {
    ///Atributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String tel;
    private int niveauMin;
    private int niveauMax;
    private String typeEtablissement;
    private boolean etat;
    @Column(unique=true)
    private String mail;
    private String motDePasse;
    
    
    public Intervenant() {
    }

    public Intervenant(String nom, String prenom, String tel, int niveauMin, int niveauMax, String typeEtablissement, String mail, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.niveauMin = niveauMin;
        this.niveauMax = niveauMax;
        this.typeEtablissement = typeEtablissement;
        this.mail = mail;
        this.motDePasse = motDePasse;
    }

    
    

    @Override
    public String toString() {
        return "Eleve " + "#" + id + ": " + nom + " " + prenom + " <" + mail + "> ";
    }
    //le but de la fonction toString est de renvoyer sans jamais afficher
    
    
    
    
}
