/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import com.google.maps.model.LatLng;
import console.Message;
import dao.EleveDao;
import dao.EtablissementDao;
import util.GeoNetApi;
import dao.JpaUtil;
import java.io.IOException;
import java.util.List;
import metier.model.Eleve;
import metier.model.Etablissement;
import util.EducNetApi;

/**
 *
 * @author ifoissey
 */
public class Service {

    public boolean inscrireEleve(Eleve eleve, String code) throws IOException {

        EleveDao elevedao = new EleveDao();
        EtablissementDao etablissementdao = new EtablissementDao();
        EducNetApi educApi = new EducNetApi();
        boolean return_value = true;
        Etablissement etablissement = null;
        List<String> info;
        boolean needToCreate= false;
        
        try {
            JpaUtil.creerContextePersistance();
            
            
            etablissement = etablissementdao.findByCode(code);
            if (etablissement == null) {
            info = educApi.getInformationLycee(code);
            //si l'établissement est un college, info est null
            if (info == null) {
                info = educApi.getInformationCollege(code);
            }
            LatLng coordsEtablissement = GeoNetApi.getLatLng(info.get(1) + " " + info.get(4)); // On envoie le nom de l'établissement et de la commune
            etablissement = new Etablissement(info.get(0), info.get(1), info.get(4), coordsEtablissement, info.get(8));
            needToCreate = true;
            }
            
            eleve.setEtablissement(etablissement);
            
            JpaUtil.ouvrirTransaction();
            
            if (needToCreate){
            etablissementdao.create(etablissement);
            }
            elevedao.create(eleve);

            JpaUtil.validerTransaction();

            JpaUtil.fermerContextePersistance();
            Message.envoyerMail("application@gmail.com", eleve.getMail(), "Objet : VALIDE", "Corps");
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            Message.envoyerMail("application@gmail.com", eleve.getMail(), "Objet : INVALIDE", "Corps");
            return_value = false;
        }

        return return_value;
    }

    public Eleve authentifierEleve(String mail, String motDePasse) {

        Eleve eleve = null;
        EleveDao elevedao = new EleveDao();

        try {
            JpaUtil.creerContextePersistance();

            eleve = elevedao.findByMail(mail);
            
            if (eleve!=null && !eleve.getMotDePasse().equals(motDePasse)) {
                eleve = null;
            }
            
            JpaUtil.fermerContextePersistance();

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();

        }
        return eleve;
    }
    
    
    public boolean initialiserIntervenant(){
         
        
        return true;
    } 

    public Eleve rechercherEleveParID(Long id) {

        Eleve client = null;
        EleveDao clientdao = new EleveDao();

        try {
            JpaUtil.creerContextePersistance();

            JpaUtil.ouvrirTransaction();

            client = clientdao.findById(id);

            JpaUtil.validerTransaction();

            JpaUtil.fermerContextePersistance();

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();

        }

        return client;
    }

    public List<Eleve> consulterListeEleves() {

        List<Eleve> desEleves = null;
        EleveDao elevedao = new EleveDao();

        try {
            JpaUtil.creerContextePersistance();

            JpaUtil.ouvrirTransaction();

            desEleves = elevedao.selectEleve();

            JpaUtil.validerTransaction();

            JpaUtil.fermerContextePersistance();

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();

        }

        return desEleves;
    }

    public Service() {
    }

}
