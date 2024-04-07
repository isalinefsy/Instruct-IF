/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import metier.model.Cours;
import metier.model.Intervenant;
import metier.model.Matiere;

/**
 *
 * @author ifoissey
 */
public class IntervenantDao {
    
    public void create(Intervenant eleve) {
        JpaUtil.obtenirContextePersistance().persist(eleve);
    }
    
    public Intervenant findByMail(String email) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select c from Intervenant c where c.mail = :unMail";
        Query query = em.createQuery(jpql);
        query.setParameter("unMail", email);
        Intervenant resultat;
        try {
            resultat = (Intervenant)query.getSingleResult();
        } catch (Exception e) {
            resultat = null;       
        }
        return resultat;
    }
    
    public Intervenant findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Intervenant.class, id); //return null si client non trouvé
    }
    
    public void updateCoursActuelEtDisponibilite(Intervenant i, Cours c, boolean dispo)
    {
        i.setCoursActuel(c);
        i.setEtat(dispo);
        JpaUtil.obtenirContextePersistance().merge(i);
    }
    
  
    
    public List<Intervenant> findAll() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select c from Intervenant c order by c.nom, c.prenom";
        Query query = em.createQuery(jpql);
        List<Intervenant> resultat = (List<Intervenant>)query.getResultList();
        return resultat;
    }

    public Intervenant findAvailableIntervenant(int niveau) {
        Intervenant i = null;
        EntityManager em = JpaUtil.obtenirContextePersistance();

        // Il manque des espaces entre les mots-clés et les clauses JPQL, ce qui peut provoquer des erreurs de syntaxe.
        
        String jpql = "select i, count(c.id) as nb_cours from Intervenant i "
                + "left join Cours c on c.intervenant = i " // Il manque le signe égal entre les tables jointes
                + "where i.niveauMin >= :niveau "
                + "AND i.niveauMax <= :niveau "
                + "AND i.etat = true " // Utilisation du simple égal (=) pour tester l'égalité
                + "Group by i order by nb_cours";
        // Le type générique TypedQuery doit être spécifié avec la classe Intervenant
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setParameter("niveau", niveau); // Définition du paramètre ":niveau"
        List<Object[]> resultList = query.getResultList();

        // Il est possible que la liste soit vide, donc il faudrait vérifier cela avant de tenter d'accéder à son premier élément.
        
        if (!resultList.isEmpty()) {
            Object[] o = resultList.get(0);
            i = (Intervenant) o[0];
        }
/*
        for (Intervenant a : resultList)
        {
            System.out.println(a.toString());
        }*/
        return i;
    }

    public IntervenantDao() {
    }
    
}
