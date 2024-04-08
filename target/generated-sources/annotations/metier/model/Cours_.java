package metier.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import metier.model.Eleve;
import metier.model.Intervenant;
import metier.model.Matiere;
import metier.model.etat;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-04-08T09:02:48")
@StaticMetamodel(Cours.class)
public class Cours_ { 

    public static volatile SingularAttribute<Cours, Date> laDate;
    public static volatile SingularAttribute<Cours, Integer> note;
    public static volatile SingularAttribute<Cours, etat> etatCours;
    public static volatile SingularAttribute<Cours, Date> finVisio;
    public static volatile SingularAttribute<Cours, String> bilan;
    public static volatile SingularAttribute<Cours, Long> id;
    public static volatile SingularAttribute<Cours, Eleve> eleve;
    public static volatile SingularAttribute<Cours, Intervenant> intervenant;
    public static volatile SingularAttribute<Cours, String> commentaire;
    public static volatile SingularAttribute<Cours, Date> debutVisio;
    public static volatile SingularAttribute<Cours, Matiere> matiere;

}