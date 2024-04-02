package metier.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.model.Eleve;
import metier.model.Intervenant;
import metier.model.Matiere;
import metier.model.etat;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-02T08:55:47")
@StaticMetamodel(Cours.class)
public class Cours_ { 

    public static volatile SingularAttribute<Cours, Integer> les_minutes;
    public static volatile SingularAttribute<Cours, Integer> note;
    public static volatile SingularAttribute<Cours, etat> etatCours;
    public static volatile SingularAttribute<Cours, Long> id;
    public static volatile SingularAttribute<Cours, Eleve> eleve;
    public static volatile SingularAttribute<Cours, String> commentaire;
    public static volatile SingularAttribute<Cours, Intervenant> intervenant;
    public static volatile SingularAttribute<Cours, Matiere> matiere;
    public static volatile SingularAttribute<Cours, Integer> l_heure;
    public static volatile SingularAttribute<Cours, Integer> le_jour;

}