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
import dao.IntervenantDao;
import util.GeoNetApi;
import dao.JpaUtil;
import dao.MatiereDao;
import java.io.IOException;
import java.util.List;
import metier.model.Autre;
import metier.model.Eleve;
import metier.model.Enseignant;
import metier.model.Etablissement;
import metier.model.Etudiant;
import metier.model.Intervenant;
import metier.model.Matiere;
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
        IntervenantDao emDao = new IntervenantDao();
        Intervenant e1 = new Etudiant("FAVRO", "Samuel", "0642049305", 6, 0, "sfavro@gmail.com", "mdp1", "INSA", "Informatique");
        Intervenant e2 = new Autre("DEKEW", "Simon", "0713200950", 6, 0, "sdekew4845@gmail.com", "mdp1", "Chercheur");
        Intervenant e3 = new Enseignant("LOU", "Flavien", "0437340532", 6, 0, "flavien.lou@gmail.com", "mp1", "Lycee");
        Intervenant e4 = new Etudiant("GUOGUEN", "Gabriela", "0719843316", 6, 0, "gguoguen2418@gmail.com", "mdp1", "ENSIMAG", "Chimie");
        Intervenant e5 = new Autre("HERNENDEZ", "Vincent", "0441564193", 6, 0, "vhernendez@gmail.com", "mdp1", "Ingenieur");
        boolean etat = false;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            emDao.create(e1);
            emDao.create(e2);
            emDao.create(e3);
            emDao.create(e4);
            emDao.create(e5);
            JpaUtil.validerTransaction();
            etat = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return etat;
    } 
    
    public Intervenant authentificationIntervenant(String mail, String mdp) {
        Intervenant i = null;
        IntervenantDao idao = new IntervenantDao();
        Boolean loggedIn = false;

        try {
            JpaUtil.creerContextePersistance();
            if (idao.findByMail(mail) != null) {
                i = idao.findByMail(mail);
                if (i.getMotDePasse().equals(mdp)) {
                    loggedIn = true;
                } else {
                    loggedIn = false;
                }
            } else {
                loggedIn = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }

        if (loggedIn.equals(false)) {
            i = null;
        }
        return i;
    }

    public boolean initialiserMatiere() {
        MatiereDao matDao = new MatiereDao();
        boolean etat = false;
        Matiere m1 = new Matiere("Français");
        Matiere m2 = new Matiere("Mathematiques");
        Matiere m3 = new Matiere("Histoire");
        Matiere m4 = new Matiere("Géographie");
        Matiere m5 = new Matiere("SVT");
        Matiere m6 = new Matiere("Philosophie");
        Matiere m7 = new Matiere("Anglais");
        Matiere m8 = new Matiere("Espagnol");
        Matiere m9 = new Matiere("Allemand");
        Matiere m10 = new Matiere("Italien");
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            matDao.create(m1);
            matDao.create(m2);
            matDao.create(m3);
            matDao.create(m4);
            matDao.create(m5);
            matDao.create(m6);
            matDao.create(m7);
            matDao.create(m8);
            matDao.create(m9);
            matDao.create(m10);

            JpaUtil.validerTransaction();
            etat = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();

        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return etat;
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
