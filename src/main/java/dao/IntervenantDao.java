/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import metier.model.Intervenant;

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
    
    public List<Intervenant> selectEleve() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select c from Intervenant c order by c.nom, c.prenom";
        Query query = em.createQuery(jpql);
        List<Intervenant> resultat = (List<Intervenant>)query.getResultList();
        return resultat;
    }

    public IntervenantDao() {
    }
    
}
