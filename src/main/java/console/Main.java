package console;

import dao.JpaUtil;
import java.io.IOException;
import java.util.List;
import metier.model.Cours;
import metier.model.Eleve;
import metier.model.Intervenant;
import metier.model.Matiere;
import metier.service.Service;
import org.apache.http.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ifoissey
 */
public class Main {

    /**
     * @param args the command line arguments
     */
//    public static void testAuth(){
//        JpaUtil.creerFabriquePersistance();
//    
//        Eleve eleve1 = new Eleve("Hugo", "Victor", "vhugo@paris.fr");
//        eleve1.setMotDePasse("toto");
//
//        Service service = new Service();
//        service.inscrireEleve(eleve1);
//        Eleve testAuth = service.authentifierEleve("vhugo@paris.fr", "toto");
//        if(testAuth == null)
//        {
//            System.out.println("Authentification failed");
//        } else {
//            System.out.println("Authentification succeeded");
//        }
//               
//        System.out.println(eleve1.toString());
// 
//        
//        JpaUtil.fermerFabriquePersistance();
//    }
    
    public static void testInscr() throws IOException{
        JpaUtil.creerFabriquePersistance();

        
        String nom = Saisie.lireChaine("Saisir un Nom");
        String prenom = Saisie.lireChaine("Saisir un Prénom");
        String dateNaissance = Saisie.lireChaine("Saisir une date de naissance au format YYYY/MM/DD");
        String email = Saisie.lireChaine("Saisir un E-mail");
        String mdp = Saisie.lireChaine("Saisir un Mot de Passe");
        String codeEt = Saisie.lireChaine("Saisir un code etablissement");
        int niveau = Saisie.lireInteger("Saisir votre classe (de 0 à 6)");
        
        
        Eleve eleve1 = new Eleve(nom, prenom, dateNaissance, email, mdp, niveau);
        
        Service service = new Service();
        service.inscrireEleve(eleve1, codeEt);

        System.out.println(eleve1.toString());

        JpaUtil.fermerFabriquePersistance();
    }
    
//    public static void testInscrns() throws IOException{
//    
//        Eleve eleve1 = new Eleve("le vasseur", "florian", "flv@gmail.com", "titi", 6);
//        Eleve eleve2 = new Eleve("le vasseur", "isa", "isa@gmail.com", "titi", 6);
//        
//        Service service = new Service();
//        service.inscrireEleve(eleve1, "0691664J");
//        service.inscrireEleve(eleve2, "0691664J");
//           
//    }

    public static Eleve testAuthEleve1() throws IOException{
        
        Service service = new Service();
        Eleve eleve = service.authentifierEleve("flv@gmail.com", "titi");
        
        if(eleve == null)
        {
            System.out.println("Authentification failed");
        } else {
            System.out.println("Authentification succeeded");
            System.out.println(eleve.toString());
        }
        return eleve;
    
    }
    public static Eleve testAuthEleve2() throws IOException{
        
        Service service = new Service();
        Eleve eleve = service.authentifierEleve("isa@gmail.com", "titi");
        
        if(eleve == null)
        {
            System.out.println("Authentification failed");
        } else {
            System.out.println("Authentification succeeded");
            System.out.println(eleve.toString());
        }
        return eleve;
    
    }
    
    public static void testDemandeCours(Eleve e1) throws IOException{
    
        
        Service service = new Service();
        //service.initialiserIntervenant();
        //service.initialiserMatiere();
        String matiere = "Français";
        service.demanderCours(e1, matiere, "J'ai besoin d'aide");
        
        
           
    }
    
    //public static void testInscrAuth2(){
//        JpaUtil.creerFabriquePersistance();
//
//        
//        String nom = Saisie.lireChaine("Saisir un Nom");
//        String prenom = Saisie.lireChaine("Saisir un Prénom");
//        String email = Saisie.lireChaine("Saisir un E-mail");
//        String mdp = Saisie.lireChaine("Saisir un mot de passe");
//        Eleve eleve1 = new Eleve(nom, prenom, email);
//        eleve1.setMotDePasse(mdp);
//        Service service = new Service();
//        service.inscrireEleve(eleve1);
//
//        System.out.println(eleve1.toString());
//        String emailAuth = Saisie.lireChaine("Saisir un E-mail");
//        String mdpAuth = Saisie.lireChaine("Saisir un mot de passe");
//        
//        Eleve testAuth = service.authentifierEleve(emailAuth, mdpAuth);
//        
//        if(testAuth == null)
//        {
//            System.out.println("Authentification failed");
//        } else {
//            System.out.println("Authentification successed");
//            System.out.println(testAuth.toString());
//        }
//        
//        JpaUtil.fermerFabriquePersistance();
//    }
//    
//    public static void testInscrAuth(){
//        JpaUtil.creerFabriquePersistance();
//    
//        Eleve eleve1 = new Eleve("Hugo", "Victor", "vhugo@paris.fr");
//        eleve1.setMotDePasse("toto");
//
//        Service service = new Service();
//        service.inscrireEleve(eleve1);
//        Eleve testAuth = service.authentifierEleve("vhugo@paris.fr", "toto");
//        if(testAuth == null)
//        {
//            System.out.println("Authentification failed");
//        } else {
//            System.out.println("Authentification successed");
//            System.out.println(testAuth.toString());
//        }
//
//        JpaUtil.fermerFabriquePersistance();
    //}      
    
//    public static void testFindById(){
//        JpaUtil.creerFabriquePersistance();
//    
//        Eleve eleve1 = new Eleve("Hugo", "Victor", "vhugo@paris.fr");
//        eleve1.setMotDePasse("toto");
//
//        Service service = new Service();
//        service.inscrireEleve(eleve1);
//
//        Eleve testRecherche = service.rechercherEleveParID(2L);
//        
//        if(testRecherche == null)
//        {
//            System.out.println("Aucun client correspondant");
//        } else {
//            System.out.println(testRecherche.toString());
//        }
//
//        JpaUtil.fermerFabriquePersistance();
//    }  
//    
//    public static void testListClient(){
//        JpaUtil.creerFabriquePersistance();
//        
//        Eleve eleve1 = new Eleve("Hugo", "Victor", "vhugo@paris.fr");
//        Eleve eleve2 = new Eleve("Charles", "Beaudelaire", "cbo@paris.fr");
//        Eleve eleve3 = new Eleve("Jacques", "Prévert", "jp@paris.fr");
//        
//        
//        Service service = new Service();
//        service.inscrireEleve(eleve1);
//        service.inscrireEleve(eleve2);
//        service.inscrireEleve(eleve3);
//        
//        List<Eleve> testRecherche = service.consulterListeEleves();
//        
//        for (Eleve e1 : testRecherche) {
//            
//            System.out.println("- " + e1.toString());
//        }
//
//        JpaUtil.fermerFabriquePersistance();
//    }  
    public static Intervenant testerAuthByEmailIntervenant1() throws ParseException {
        Service s = new Service();
        Intervenant in = s.authentificationIntervenant("sfavro@gmail.com", "mdp1");
        if (in == null) {
            System.out.println("Echec de l'authentification authentification, veuillez ré-essayer");
        } else {
            System.out.println("L'authentification est un succès : " + in);
        }
        return in;
    }
    public static Intervenant testerAuthByEmailIntervenant2() throws ParseException {
        Service s = new Service();
        Intervenant in = s.authentificationIntervenant("vhernendez@gmail.com", "mdp1");
        if (in == null) {
            System.out.println("Echec de l'authentification authentification, veuillez ré-essayer");
        } else {
            System.out.println("L'authentification est un succès : " + in);
        }
        return in;
    }

    public static void testLancerVisio(){
        Service s = new Service();
        Intervenant in = s.authentificationIntervenant("vhernendez@gmail.com", "mdp1");
        if (in == null) {
            System.out.println("Echec de l'authentification authentification, veuillez ré-essayer");
        } else {
            System.out.println("L'authentification est un succès : " + in);
        }
        Cours c = s.lancerVisio(in);
    }
    
    public static void testLancerEtFinVisioEleve(){
        Service s = new Service();
        Intervenant in = s.authentificationIntervenant("vhernendez@gmail.com", "mdp1");
        if (in == null) {
            System.out.println("Echec de l'authentification authentification, veuillez ré-essayer");
        } else {
            System.out.println("L'authentification est un succès : " + in);
        }
        Cours c = s.lancerVisio(in);
        c = s.terminerVisio(c);
        int note = Saisie.lireInteger("Saisir une note : 1 / 2 / 3");
        s.noterCours(c, note); 
    }
    public static void testLancerEtFinVisioIntervenant(){
        Service s = new Service();
        Intervenant in = s.authentificationIntervenant("vhernendez@gmail.com", "mdp1");
        if (in == null) {
            System.out.println("Echec de l'authentification authentification, veuillez ré-essayer");
        } else {
            System.out.println("L'authentification est un succès : " + in);
        }
        Cours c = s.lancerVisio(in);
        c = s.terminerVisio(c);
        String bilan = Saisie.lireChaine("Envoyez un bilan à "+ c.getEleve().getPrenom());
        s.EnvoyerBilanCours(c, bilan); 
    }
    
    public static void testHistoriqueEleve() throws IOException{
        Eleve e = testAuthEleve2();
        Service s = new Service();
        List<Cours> historique = s.getHistoriqueEleve(e);
        if (historique.isEmpty()){
            System.out.println("vous n'avez encore jamais fait de soutien");
        }else{
        for (Cours c : historique){
            System.out.println(c.toString());
            }
        }
    }
    public static void testHistoriqueIntervenant() throws IOException{
        Intervenant i = testerAuthByEmailIntervenant2();
        Service s = new Service();
        List<Cours> historique = s.getHistoriqueIntervenant(i);
        if (historique.isEmpty()){
            System.out.println("vous n'avez encore jamais donné de soutien");
        }else{
        for (Cours c : historique){
            System.out.println(c.toString());
            }
        }
    }

    public static void initialisationTest() throws ParseException, IOException {
        Service s = new Service();
;
        //initialisation des matières
        s.initialiserMatiere();

        //inscription préalable de 3 élèves venant de 2 lycées différents.

        Eleve e1 = new Eleve("le vasseur", "florian", "2004/07/05", "flv@gmail.com", "titi", 6);
        s.inscrireEleve(e1, "0921159K");
        Eleve e2 = new Eleve("le vasseur", "isa", "2004/07/05", "isa@gmail.com", "titi", 6);
        s.inscrireEleve(e2, "0332490C");
        Eleve e3 = new Eleve("Blanc", "Chantal", "2004/07/05", "cblanc@gmail.com", "mdp", 0);
        s.inscrireEleve(e3, "0332490C");
        Eleve e4 = new Eleve("Noir", "Chantal", "2004/07/05", "cnoir@gmail.com", "mdp", 2);
        s.inscrireEleve(e4, "0332490C");

        
        

        //inscription de quelques intervenants
        s.initialiserIntervenant();
        
      
        System.out.println("Boujour et Bienvenue sur INSTRUCT'IF.\n");
        
        //Presentation des matières
        List<Matiere> listMatiere = s.consulterListeMatieres();
        System.out.println("Les matières disponibles sur l'application sont : \n");
        for (Matiere m : listMatiere) {
            System.out.println(m);
        }

        //Presentation des élèves et intervenants
        System.out.println(" Voici les élèves inscrits et les intervenants");
        List<Eleve> le = s.consulterListeEleves();
        for (Eleve e : le) {
            System.out.println(e.toString());
        }
        List<Intervenant> li = s.consulterListeIntervenants();
        for (Intervenant i : li) {
            System.out.println(i.toString());
        }

        System.err.println("Tout le monde a été initialisé");
    }

    public static void main(String[] args) throws ParseException, IOException {
            JpaUtil.creerFabriquePersistance();
            initialisationTest();
            testerAuthByEmailIntervenant1();
            //OUBLIEZ PAS DE DEMANDER POUR L'HEURE ET LES MINUTES, ZOUBI
            Eleve e1 = testAuthEleve1();
            testDemandeCours(e1);
            //testLancerVisio();
            testLancerEtFinVisioIntervenant();
            testHistoriqueEleve(); 
            testHistoriqueIntervenant();
            JpaUtil.fermerFabriquePersistance();
        }

}
