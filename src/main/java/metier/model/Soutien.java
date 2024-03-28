/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author flevasseur
 */
@Entity
public class Soutien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique=true)
    private long ID;
     private String heure_debut;
     private String heure_fin;
     private String description;
     private String evaluation;
     private String bilan;
     private enum etat {EN_ATTENTE, EN_COURS, TERMINE};
     @ManyToOne
     private Eleve eleve;
     @ManyToOne
     private Matiere matiere;
     @ManyToOne
     private Intervenant intervenant;
}
public Soutien(){
    }





