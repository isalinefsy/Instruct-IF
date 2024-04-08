/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import metier.model.Cours;
import metier.model.Eleve;
import metier.model.Intervenant;

/**
 *
 * @author ifoissey
 */
public class CoursDao {

    public void create(Cours cours) {
        JpaUtil.obtenirContextePersistance().persist(cours);
    }

    public Cours findById(Long id) {

        return JpaUtil.obtenirContextePersistance().find(Cours.class, id);

    }

    public Cours findPendingByIntervenant(Intervenant intervenant) {
        Cours c;
        String s = "select c from Cours c where c.intervenant = :intervenant and c.etatCours = metier.model.etat.EN_ATTENTE";
        TypedQuery<Cours> query = JpaUtil.obtenirContextePersistance().createQuery(s, Cours.class);
        query.setParameter("intervenant", intervenant);
        try {
            c = query.getSingleResult();
        } catch(Exception e)  {
            c = null;
        }
        return c;
    }
public Object[] NbEtDureeSoutiens() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        try {
            // Requête pour obtenir tous les cours terminés
            String qlString = "SELECT c FROM Cours c WHERE c.etatCours = metier.model.etat.TERMINE";
            TypedQuery<Cours> query = em.createQuery(qlString, Cours.class);
            List<Cours> coursTermines = query.getResultList();
            
            long nbCoursTermines = coursTermines.size();
            double totalMinutes = 0;
            
            for (Cours c : coursTermines) {
                if (c.getDebutVisio() != null && c.getFinVisio() != null) {
                    // Calcul de la différence en millisecondes puis conversion en minutes
                    long difference = c.getFinVisio().getTime() - c.getDebutVisio().getTime();
                    double minutes = ((double)difference) / (60 * 1000);
                    totalMinutes += minutes;
                }
            }
            
            double dureeMoyenne = nbCoursTermines > 0 ? ((double) totalMinutes) / nbCoursTermines : 0;

            Object[] resultat = new Object[2];
            resultat[0] = nbCoursTermines;
            resultat[1] = dureeMoyenne;

            return resultat;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Ou gestion d'erreur spécifique
        }
    }
//    public Cours findCurrentByEleve(Eleve e) {
//        Cours c;
//        String s = "select e from Cours e where e.eleve = :eleve and e.etatCours = metier.model.etat.EN_COURS";
//        TypedQuery<Cours> query = JpaUtil.obtenirContextePersistance().createQuery(s, Cours.class);
//        query.setParameter("eleve", e);
//        try {
//            c = query.getSingleResult();
//        } catch(Exception ex)  {
//            c = null;
//        }
//        return c;
//    }
//    
//     public Cours findCurrentByIntervenant(Intervenant i) {
//        Cours c;
//        String s = "select e from Cours e where e.intervenant = :intervenant and e.etatCours = metier.model.etat.EN_COURS";
//        TypedQuery<Cours> query = JpaUtil.obtenirContextePersistance().createQuery(s, Cours.class);
//        query.setParameter("intervenant", i);
//        try {
//            c = query.getSingleResult();
//        } catch(Exception ex)  {
//            c = null;
//        }
//        return c;
//    }
    
    
    public List<Cours> findAll() {
        String s = "select c from Cours c";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Cours.class);
        return query.getResultList();
    }
    
    public List<Cours> findAllOfEleve(Eleve e) {
        String s = "select c from Cours c where c.eleve = :eleve";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Cours.class);
        query.setParameter("eleve", e);
        return query.getResultList();
    }
    
    public List<Cours> findAllOfIntervenant(Intervenant i) {
        String s = "select c from Cours c where c.intervenant = :intervenant and c.etatCours = metier.model.etat.TERMINE ";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Cours.class);
        query.setParameter("intervenant", i);
        return query.getResultList();
    }
    
    
    public void setNote(Cours c, int i) {
        c.setNote(i);
        JpaUtil.obtenirContextePersistance().merge(c);

    }

//    public void updateEtatCours(Cours cours) {
//        EntityManager em = JpaUtil.obtenirContextePersistance();
//
//        String queryString = "UPDATE Cours SET etat = :etatCours " +
//                "WHERE id = :coursId";
//
//        TypedQuery<Cours> query = em.createQuery(queryString, Cours.class);
//        query.setParameter("etatCours", cours.getEtatCours());
//        query.setParameter("coursId", cours.getId());
//        query.executeUpdate();
//    }
}
