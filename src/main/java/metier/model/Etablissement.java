/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.model;

import com.google.maps.model.LatLng;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author flevasseur
 */
// 0240005A  /  0240006B  (Bergerac les deux) -------------------------------------------------- à rajouter pour tester le count etablissement by commune


@Entity
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String codeEtablissement;
    private String nomEtablissement;
    private String commune;
    private LatLng coordonnees; 
    private String IPS;

    public Etablissement() {
    }

    public Etablissement(String codeEtablissement, String nomEtablissement, String commune, LatLng coordonnees, String IPS) {
        this.codeEtablissement = codeEtablissement;
        this.nomEtablissement = nomEtablissement;
        this.commune = commune;
        this.coordonnees = coordonnees;
        this.IPS = IPS;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getCodeEtablissement() {
        return codeEtablissement;
    }

    public void setCodeEtablissement(String codeEtablissement) {
        this.codeEtablissement = codeEtablissement;
    }

    public String getNomEtablissement() {
        return nomEtablissement;
    }

    public void setNomEtablissement(String nomEtablissement) {
        this.nomEtablissement = nomEtablissement;
    }

    public LatLng getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(LatLng coordonnees) {
        this.coordonnees = coordonnees;
    }

    public String getIPS() {
        return IPS;
    }

    public void setIPS(String IPS) {
        this.IPS = IPS;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 
    
}
