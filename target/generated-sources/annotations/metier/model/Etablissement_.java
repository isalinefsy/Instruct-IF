package metier.model;

import com.google.maps.model.LatLng;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-03-27T11:07:38")
@StaticMetamodel(Etablissement.class)
public class Etablissement_ { 

    public static volatile SingularAttribute<Etablissement, String> nomEtablissement;
    public static volatile SingularAttribute<Etablissement, String> commune;
    public static volatile SingularAttribute<Etablissement, String> codeEtablissement;
    public static volatile SingularAttribute<Etablissement, Long> id;
    public static volatile SingularAttribute<Etablissement, LatLng> coordonnees;
    public static volatile SingularAttribute<Etablissement, String> IPS;

}