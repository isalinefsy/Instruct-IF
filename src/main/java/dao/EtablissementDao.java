/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import metier.model.Etablissement;

/**
 *
 * @author ifoissey
 */
public class EtablissementDao {
    
    public void create(Etablissement etablissement) {
        JpaUtil.obtenirContextePersistance().persist(etablissement);
    }
    
    public Etablissement findByCode(String code) {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select e from Etablissement e where e.codeEtablissement = :code";
        Query query = em.createQuery(jpql);
        query.setParameter("code", code);
        Etablissement resultat;
        try {
            resultat = (Etablissement)query.getSingleResult();
        } catch (Exception e) {
            resultat = null;       
        }
        return resultat;
    }
    
    public Etablissement findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Etablissement.class, id); //return null si Etablissement non trouvé
    }
    
    public List<Etablissement> selectEtablissement() {
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select c from codeEtablissement c order by c.codeEtablissement, c.nomEtablissement";
        Query query = em.createQuery(jpql);
        List<Etablissement> resultat = (List<Etablissement>)query.getResultList();
        return resultat;
    }

    public EtablissementDao() {
    }
    
    
}
