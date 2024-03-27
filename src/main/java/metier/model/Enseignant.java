/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.model;
import java.io.Serializable;
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
public class Enseignant extends Intervenant {
    private String typeEtablissement;

    public Enseignant(String nom, String prenom, String tel, int niveauMin, int niveauMax, String email, String motDePasse, String typeEtablissement) {
        super(nom, prenom,tel, niveauMin, niveauMax, email, motDePasse);
        this.typeEtablissement = typeEtablissement;
    }
    
    public Enseignant() {}

    public String getTypeEtablissement() {
        return typeEtablissement;
    }

    public void setTypeEtablissement(String typeEtablissement) {
        this.typeEtablissement = typeEtablissement;
    }
    
   
    @Override
    public String toString() {
        return "Enseignant{" + super.toString() + " , " + "typeEtablissement=" + typeEtablissement + '}';
    }
   
}
