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
        String s = "select e from Cours e where e.intervenant = :intervenant and e.etatCours = metier.model.etat.EN_ATTENTE";
        TypedQuery<Cours> query = JpaUtil.obtenirContextePersistance().createQuery(s, Cours.class);
        query.setParameter("intervenant", intervenant);
        try {
            c = query.getSingleResult();
        } catch(Exception e)  {
            c = null;
        }
        return c;
    }

    public List<Cours> findAll() {
        String s = "select e from Cours e";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Cours.class);
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
