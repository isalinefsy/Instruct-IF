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
import dao.CoursDao;
import util.GeoNetApi;
import dao.JpaUtil;
import dao.MatiereDao;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.util.Pair;
import metier.model.Autre;
import metier.model.Cours;
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
        boolean needToCreate = false;

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

            if (needToCreate) {
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

            if (eleve != null && !eleve.getMotDePasse().equals(motDePasse)) {
                eleve = null;
            }

            JpaUtil.fermerContextePersistance();

        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();

        }
        return eleve;
    }

    public boolean initialiserIntervenant() {
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

            desEleves = elevedao.selectEleve();

            JpaUtil.fermerContextePersistance();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return desEleves;
    }

    public List<Matiere> consulterListeMatieres() {

        List<Matiere> desMatieres = null;
        MatiereDao matieredao = new MatiereDao();

        try {
            JpaUtil.creerContextePersistance();

            desMatieres = matieredao.findAll();

            JpaUtil.fermerContextePersistance();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return desMatieres;
    }

    public List<Intervenant> consulterListeIntervenants() {

        List<Intervenant> desIntervenants = null;
        IntervenantDao intervenantdao = new IntervenantDao();

        try {
            JpaUtil.creerContextePersistance();

            desIntervenants = intervenantdao.findAll();

            JpaUtil.fermerContextePersistance();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return desIntervenants;
    }

    public Cours demanderCours(Eleve eleve, String nomMatiere, String message) {
        Cours cours = null;

        try {
            JpaUtil.creerContextePersistance();
            int niveau = eleve.getNiveau();
            IntervenantDao intervenantDao = new IntervenantDao();
            CoursDao coursdao = new CoursDao();
            MatiereDao matieredao = new MatiereDao();

            Intervenant intervenant = intervenantDao.findAvailableIntervenant(niveau);
            Matiere m = matieredao.findByName(nomMatiere);
            cours = new Cours(m, eleve, intervenant, message);

            JpaUtil.ouvrirTransaction();
            coursdao.create(cours);
            //on affecte le cours à l'intervenant et on le rend indisponible
            intervenant.setCoursActuel(cours);
            intervenant.setEtat(false);
            JpaUtil.validerTransaction();

            //il faut maintenant contacter l'intervenant
            SimpleDateFormat heure = new SimpleDateFormat("HH'h'mm");
            String corps = "Bonjour " + intervenant.getPrenom() + ". Merci de prendre en charge la demande de soutien en \""
                    + m.getNomMatiere() + "\" demandée à " + heure.format(cours.getLaDate()) + " par " + eleve.getPrenom()
                    + " en classe de " + eleve.getNiveau() + "ème.";
            Message.envoyerNotification(intervenant.getTel(), "Pour : " + intervenant.getPrenom() + " " + intervenant.getNom() + "\n" + corps);
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();

        }
        return cours;
    }

    public Cours lancerVisio(Intervenant i) {
        CoursDao coursdao = new CoursDao();
        Date d = new Date();
        Cours c = null;
        try {
            JpaUtil.creerContextePersistance();
            c = coursdao.findPendingByIntervenant(i);
            JpaUtil.ouvrirTransaction();
            c.setEtatCours(metier.model.etat.EN_COURS);
            c.setDebutVisio(d);
            JpaUtil.validerTransaction();
            System.out.println("----VISIO----");
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return c;
    }

    public Cours terminerVisio(Cours c) {
        CoursDao coursdao = new CoursDao();
        Date d = new Date();
        Cours cours = null;
        try {
            JpaUtil.creerContextePersistance();
            cours = coursdao.findById(c.getId());
            JpaUtil.ouvrirTransaction();
            cours.setEtatCours(metier.model.etat.TERMINE);
            
            cours.setFinVisio(d);
            JpaUtil.validerTransaction();
            System.out.println("----FIN VISIO----");
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return cours;
    }

    public void noterCours(Cours c, int note) {
        CoursDao coursdao = new CoursDao();
        Cours cours = null;
        try {
            JpaUtil.creerContextePersistance();
            cours = coursdao.findById(c.getId());
            JpaUtil.ouvrirTransaction();
            cours.setNote(note);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public void EnvoyerBilanCours(Cours c, String bilan) {
        CoursDao coursdao = new CoursDao();
        IntervenantDao intervenantdao = new IntervenantDao();
        Cours cours = null;
        Intervenant i = null;
        Eleve e = c.getEleve();
        SimpleDateFormat heure = new SimpleDateFormat("HH'h'mm");
        String corps = "Bonjour " + e.getPrenom() + ", voici le bilan de ta session de soutien en \""
                + c.getMatiere().getNomMatiere() + "\" :\n" + bilan;
        Message.envoyerMail(c.getIntervenant().getMail(), e.getMail(), "[BILAN visio]", corps);
        try {
            JpaUtil.creerContextePersistance();
            cours = coursdao.findById(c.getId());
            i = intervenantdao.findById(c.getIntervenant().getId());
            JpaUtil.ouvrirTransaction();
            cours.setBilan(bilan);
            i.setEtat(true);
            i.setCoursActuel(null);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public List<Cours> getHistoriqueEleve(Eleve e) {
        CoursDao coursdao = new CoursDao();
        List<Cours> lesCours = null;
        try {
            JpaUtil.creerContextePersistance();
            lesCours = coursdao.findAllOfEleve(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return lesCours;
    }

    public List<Cours> getHistoriqueIntervenant(Intervenant i) {
        CoursDao coursdao = new CoursDao();
        List<Cours> lesCours = null;
        try {
            JpaUtil.creerContextePersistance();
            lesCours = coursdao.findAllOfIntervenant(i);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return lesCours;
    }

    public List<Object[]> statNbEtablissementParCommune() {     
        EtablissementDao etablissementdao = new EtablissementDao();
        List<Object[]> nbEtabParCommune = null;
        try {
            JpaUtil.creerContextePersistance();
            nbEtabParCommune = etablissementdao.countEtablissementByCommune();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return nbEtabParCommune;
    }

    
   public Object[] checkDemandeSoutien(Intervenant i) {
        Cours c = null;
        Eleve e = null;
        IntervenantDao intervenantdao = new IntervenantDao();
        EleveDao elevedao = new EleveDao();
        try {
            JpaUtil.creerContextePersistance();
            c = intervenantdao.findById(i.getId()).getCoursActuel();
            e = elevedao.findById(c.getEleve().getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return new Object[] {c, e};
    }
   
   public List <Etablissement> statEtablissementIPS() {
       EtablissementDao etablissementdao = new EtablissementDao();
       List <Etablissement> etablissementsIPSBas = null;
       try {
            JpaUtil.creerContextePersistance();
            etablissementsIPSBas = etablissementdao.selectEtablissementIPSBas();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return etablissementsIPSBas;
     
   }
    
   public Object[] statNbSoutienetDuree() {
       Object[] NbetDuree = null;
       CoursDao coursdao = new CoursDao();
       try {
            JpaUtil.creerContextePersistance();
            NbetDuree = coursdao.NbEtDureeSoutiens();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return NbetDuree;
     
   }
    
   
    public Service() {
    }

    

}