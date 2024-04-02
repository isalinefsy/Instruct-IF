package metier.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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
public class Cours implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Matiere matiere;
    @ManyToOne
    private Eleve eleve;
    @Temporal(TemporalType.DATE)
    private Date la_date;
    @Enumerated(EnumType.ORDINAL)
    private etat etatCours;
    private String commentaire;
    private int note;
    
    @ManyToOne
    private Intervenant intervenant;
    
    public Cours() {
    }

    public Cours( Matiere matiere, Eleve eleve,Intervenant intervenant, String commentaire) {
       
        this.matiere = matiere;
        this.eleve = eleve;
        Date d = new Date();
        this.la_date = d;
        this.commentaire= commentaire;
        this.intervenant = intervenant;
        this.etatCours = etat.EN_ATTENTE;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }
  
    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Date getDate() {
        return la_date;
    }

    public void setDate(Date date) {
        this.la_date = date;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    public etat getEtatCours() {
        return etatCours;
    }

    public void setEtatCours(etat etatCours) {
        this.etatCours = etatCours;
    }


    @Override
    public String toString() {
        return "Cours{" + "id=" + id + ", matiere=" + matiere + ", eleve=" + eleve.getPrenom() + ", date=" + la_date + ", commentaire=" + commentaire + ", note=" + note + ", intervenant=" + intervenant.getPrenom() + '}';
    }



}
    
    
    
