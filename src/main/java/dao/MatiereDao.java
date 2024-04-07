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
import metier.model.Matiere;

/**
 *
 * @author ifoissey
 */
public class MatiereDao {
    
    public void create(Matiere matiere)
    {
        JpaUtil.obtenirContextePersistance().persist(matiere);
    }
    
    public Matiere findById(Long id)
    {
        return JpaUtil.obtenirContextePersistance().find(Matiere.class, id);
    }
    
    
    public List<Matiere> findAll()
    {
        String s = "select e from Matiere e";
       TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Matiere.class);
        return query.getResultList();
    }
    
    public Matiere findByName(String nom)
    {
        String s = "select e from Matiere e where e.nomMatiere = :nom";
       TypedQuery <Matiere> query = JpaUtil.obtenirContextePersistance().createQuery(s, Matiere.class);
       query.setParameter("nom",nom);
        return query.getSingleResult();
    }
    

    
}