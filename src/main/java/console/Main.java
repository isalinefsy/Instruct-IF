package console;

import dao.JpaUtil;
import java.io.IOException;
import java.util.List;
import metier.model.Eleve;
import metier.service.Service;

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
        String email = Saisie.lireChaine("Saisir un E-mail");
        String mdp = Saisie.lireChaine("Saisir un Mot de Passe");
        String codeEt = Saisie.lireChaine("Saisir un code etablissement");
        int niveau = Saisie.lireInteger("Saisir votre classe (de 0 à 6)");
        
        
        Eleve eleve1 = new Eleve(nom, prenom, email, mdp, niveau);
        
        Service service = new Service();
        service.inscrireEleve(eleve1, codeEt);

        System.out.println(eleve1.toString());

        JpaUtil.fermerFabriquePersistance();
    }
    
    public static void testInscrns() throws IOException{
    
        Eleve eleve1 = new Eleve("le vasseur", "florian", "flv@gmail.com", "titi", 6);
        Eleve eleve2 = new Eleve("le vasseur", "isa", "isa@gmail.com", "titi", 6);
        
        Service service = new Service();
        service.inscrireEleve(eleve1, "0691664J");
        service.inscrireEleve(eleve2, "0691664J");
           
    }
    public static void testAuthns() throws IOException{
        
        Service service = new Service();
        Eleve eleve = service.authentifierEleve("flv@gmail.com", "titi");
        
        if(eleve == null)
        {
            System.out.println("Authentification failed");
        } else {
            System.out.println("Authentification succeeded");
            System.out.println(eleve.toString());
        }
    
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
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        JpaUtil.creerFabriquePersistance();
        testInscrns();
        testAuthns();
        //testInscrAuth2();
        //testAuth();
        //testInscrAuth();
        //testFindById();
        //testListClient();
        JpaUtil.fermerFabriquePersistance();
    }
    
}
