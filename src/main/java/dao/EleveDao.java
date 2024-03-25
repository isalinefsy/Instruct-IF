/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import metier.model.Eleve;

/**
 *
 * @author ifoissey
 */
public class EleveDao {
    
    public void create(Eleve eleve) {
        JpaUtil.obtenirContextePersistance().persist(eleve);
    }
    
    public Eleve findByMail(String email) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select c from Eleve c where c.mail = :unMail";
        Query query = em.createQuery(jpql);
        query.setParameter("unMail", email);
        Eleve resultat;
        try {
            resultat = (Eleve)query.getSingleResult();
        } catch (Exception e) {
            resultat = null;       
        }
        return resultat;
    }
    
    public Eleve findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Eleve.class, id); //return null si client non trouvé
    }
    
    public List<Eleve> selectEleve() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select c from Eleve c order by c.nom, c.prenom";
        Query query = em.createQuery(jpql);
        List<Eleve> resultat = (List<Eleve>)query.getResultList();
        return resultat;
    }

    public EleveDao() {
    }
    
}
