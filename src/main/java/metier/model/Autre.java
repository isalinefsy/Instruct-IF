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
public class Autre extends Intervenant {
    private String activite;

    public Autre(String nom, String prenom, String tel, int niveauMin, int niveauMax, String email, String motDePasse, String activite) {
        super(nom, prenom,tel, niveauMin, niveauMax, email, motDePasse);
        this.activite = activite;
    }

    public Autre() {
    }
    
    

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    @Override
    public String toString() {
        return "Autre{" + super.toString() + " , " +  "activite=" + activite + '}';
    }
    
    

}
