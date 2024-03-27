/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.model;
import javax.persistence.Entity;
/**
 *
 * @author ifoissey
 */

/**
 *
 * @author isalinefsy
 */
@Entity
public class Etudiant extends Intervenant {
    private String universite;
    private String specialite;

    public Etudiant(String nom, String prenom, String tel, int niveauMin, int niveauMax, String email, String motDePasse, String universite, String specialite) {
        super(nom, prenom,tel, niveauMin, niveauMax, email, motDePasse);
        this.universite = universite;
        this.specialite = specialite;
    }
    
    public Etudiant() {}
    
    public String getUniversite() {
        return universite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

  

    @Override
    public String toString() {
        return "Etudiant{" + super.toString() + " , " + "universite=" + universite + ", specialite=" + specialite + '}';
    }
    
    

}
