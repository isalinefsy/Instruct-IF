/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static javafx.scene.input.KeyCode.T;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
    
    public List<Object[]> countEtablissementByCommune(){
        EntityManager em = JpaUtil.obtenirContextePersistance();
        String jpql = "select e.commune, count(e.id) as nbParCommune from Etablissement e group by e.commune order by nbParCommune";
        TypedQuery <Object[]> query = em.createQuery(jpql, Object[].class);
        List<Object[]> resultList = query.getResultList(); 
        return resultList;
    }
    
    public List<Etablissement> selectEtablissementIPSBas() {
    EntityManager em = JpaUtil.obtenirContextePersistance();
    String jpql = "select e from Etablissement e where substring(e.IPS,1,1)<='8' and substring(e.IPS,1,1)>'1'";
    Query query = em.createQuery(jpql);
    List<Etablissement> resultat = (List<Etablissement>) query.getResultList();
    return resultat;
}
    //substring(e.IPS,1,1) >'1'

    public List<Etablissement> findAll() {
        String s = "select e from Etablissement e";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Etablissement.class);
        return query.getResultList();
    }
    
    public EtablissementDao() {
    }
    
    
}
