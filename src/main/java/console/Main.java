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
    public static void initialisation() throws ParseException, IOException {
        Service s = new Service();
        ;
        //initialisation des matières
        s.initialiserMatiere();

        //inscription préalable de 3 élèves venant de 2 lycées différents.
        Eleve e1 = new Eleve("le vasseur", "florian", "2004/07/05", "flv@gmail.com", "mdp", 6);
        s.inscrireEleve(e1, "0921159K");
        Eleve e2 = new Eleve("le vasseur", "isa", "2004/07/05", "isa@gmail.com", "mdp", 6);
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

    public static void inscriptionEleve() throws IOException {
        System.out.println("-------INSCRIPTION D'UN ÉLÈVE-------");
        String nom = Saisie.lireChaine("Saisir un Nom : ");
        String prenom = Saisie.lireChaine("Saisir un Prénom : ");
        String dateNaissance = Saisie.lireChaine("Saisir une date de naissance au format YYYY/MM/DD : ");
        String email = Saisie.lireChaine("Saisir un E-mail : ");
        String mdp = Saisie.lireChaine("Saisir un Mot de Passe : ");
        String codeEt = Saisie.lireChaine("Saisir un code etablissement : ");
        int niveau = Saisie.lireInteger("Saisir votre classe (de 0 à 6) : ");

        Eleve eleve1 = new Eleve(nom, prenom, dateNaissance, email, mdp, niveau);

        Service service = new Service();
        service.inscrireEleve(eleve1, codeEt);

        System.out.println(eleve1.getPrenom() + ", vous êtes bien inscrit. Vous pouvez maintenant vous connecter.");
    }

    public static Eleve authentificationEleve() throws IOException {
        System.out.println("-------AUTHENTIFICATION D'UN ÉLÈVE-------");
        String email = Saisie.lireChaine("Saisissez votre E-mail : ");
        String mdp = Saisie.lireChaine("Saisissez votre Mot de Passe : ");

        Service service = new Service();
        Eleve eleve = service.authentifierEleve(email, mdp);

        if (eleve == null) {
            System.out.println("Echec de l'authentification, veuillez ré-essayer");
        } else {
            System.out.println("L'authentification est un succès.\nBonjour : " + eleve.getPrenom());
            System.out.println(eleve.toString());
        }
        return eleve;
    }

    public static Intervenant authentificationIntervenant() throws ParseException {
        System.out.println("-------AUTHENTIFICATION D'UN INTERVENANT-------");
        String email = Saisie.lireChaine("Saisissez votre E-mail : ");
        String mdp = Saisie.lireChaine("Saisissez votre Mot de Passe : ");

        Service s = new Service();
        Intervenant in = s.authentificationIntervenant(email, mdp);

        if (in == null) {
            System.out.println("Echec de l'authentification, veuillez ré-essayer");
        } else {
            System.out.println("L'authentification est un succès.\nBonjour : " + in.getPrenom());
            System.out.println(in.toString());
        }
        return in;
    }

    public static void afficherHistoriqueEleve(Eleve e) throws IOException {
        Service s = new Service();
        List<Cours> historique = s.getHistoriqueEleve(e);
        if (historique.isEmpty()) {
            System.out.println("vous n'avez encore jamais fait de soutien");
        } else {
            System.out.println("Voici la liste des soutiens que vous avez déjà effectués :");
            for (Cours c : historique) {
                System.out.println(c.toString());
            }
        }
    }

    public static void afficherHistoriqueIntervenant(Intervenant i) throws IOException {
        Service s = new Service();
        List<Cours> historique = s.getHistoriqueIntervenant(i);
        if (historique.isEmpty()) {
            System.out.println("vous n'avez encore jamais donné de soutien");
        } else {
            System.out.println("Voici la liste des soutien que vous avez déjà effectué :");
            for (Cours c : historique) {
                System.out.println(c.toString());
            }
        }
    }

    public static Cours demandeCours(Eleve e1) throws IOException {

        Service s = new Service();
        System.out.println("Veuillez saisir les informations propres à la demande de soutien :");
        String matiere = Saisie.lireChaine("Matière : ");
        String description = Saisie.lireChaine("Description : ");
        Cours c = s.demanderCours(e1, matiere, description);
        if (c == null) {
            System.out.println("Il n'y a pas d'intervenants disponibles actuellement. Veuillez réessayez plus tard.");
        }
        return c;
    }

    public static Cours afficherDemandeSoutien(Intervenant i) throws IOException {
        Service s = new Service();
        Cours c = s.CheckDemandeSoutien(i);
        if (c == null) {
            System.out.println("vous n'avez pas de soutien demandé actuellement");
        } else {
            System.out.println("Veuillez prendre en charge le soutien \n" + c.toString() + "\n demandé par :\n " + c.getEleve());
        }
        return c;
    }

    public static Cours lancerVisio(Intervenant in) {
        Service s = new Service();
        Cours c = s.lancerVisio(in);
        return c;
    }

    public static boolean attendreDebutVisio(Cours c) {
        Service s = new Service();
//        while (s.CheckVisioEnCours(c)) { //on attends tant que le visio n'a pas commencé (tant que le cours n'est pas passé à l'état EN_COURS)
//        }
//        return;
        System.out.println("1.On simule le lancement de la visio par l'intervenant");
        System.out.println("2.On se déconnecte pour passer sur la page de l'intervenant ou d'un autre élève");
        int choix = Saisie.lireInteger("Choix : ");
        return choix == 1;
    }

    public static Cours mettreFinVisioEleve(Cours c) {
        Service s = new Service();
        s.terminerVisio(c);
        System.out.println("----FIN VISIO----");
        int note = Saisie.lireInteger("A quel point cette séance de soutien vous a-t-elle aidé sur une échelle de 1 à 10 : ");
        s.noterCours(c, note);
        return c;
    }

    public static void mettreFinVisioIntervenant(Cours c) {
        Service s = new Service();
        s.terminerVisio(c);
        System.out.println("----FIN VISIO----");
        String bilan = Saisie.lireChaine("Rédigez un bilan de la séance de soutien à destination de " + c.getEleve().getPrenom() + " : ");
        s.EnvoyerBilanCours(c, bilan);
        return;
    }

    public static void afficherStats() {
        Service s = new Service();
        List<Object[]> nbEtabParCommune = s.statNbEtablissementParCommune();
        for (Object[] o : nbEtabParCommune) {
            String commune = (String) o[0];
            long nbEtab = (long) o[1];
            System.out.print(commune + " : " + nbEtab + " établissements utilisant Instruct'if\n");
        }
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
//    public static Eleve testAuthEleve1() throws IOException {
//
//        Service service = new Service();
//        Eleve eleve = service.authentifierEleve("flv@gmail.com", "titi");
//
//        if (eleve == null) {
//            System.out.println("Authentification failed");
//        } else {
//            System.out.println("Authentification succeeded");
//            System.out.println(eleve.toString());
//        }
//        return eleve;
//
//    }
//
//    public static Eleve testAuthEleve2() throws IOException {
//
//        Service service = new Service();
//        Eleve eleve = service.authentifierEleve("isa@gmail.com", "titi");
//
//        if (eleve == null) {
//            System.out.println("Authentification failed");
//        } else {
//            System.out.println("Authentification succeeded");
//            System.out.println(eleve.toString());
//        }
//        return eleve;
//
//    }
//
//    public static void testDemandeCours(Eleve e1) throws IOException {
//
//        Service service = new Service();
//        //service.initialiserIntervenant();
//        //service.initialiserMatiere();
//        String matiere = "Français";
//        service.demanderCours(e1, matiere, "J'ai besoin d'aide");
//
//    }
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
//    public static Intervenant testerAuthByEmailIntervenant1() throws ParseException {
//        Service s = new Service();
//        Intervenant in = s.authentificationIntervenant("sfavro@gmail.com", "mdp1");
//        if (in == null) {
//            System.out.println("Echec de l'authentification authentification, veuillez ré-essayer");
//        } else {
//            System.out.println("L'authentification est un succès : " + in);
//        }
//        return in;
//    }
//
//    public static Intervenant testerAuthByEmailIntervenant2() throws ParseException {
//        Service s = new Service();
//        Intervenant in = s.authentificationIntervenant("vhernendez@gmail.com", "mdp1");
//        if (in == null) {
//            System.out.println("Echec de l'authentification authentification, veuillez ré-essayer");
//        } else {
//            System.out.println("L'authentification est un succès : " + in);
//        }
//
//        Cours c = s.CheckDemandeSoutien(in);
//        if (c == null) {
//            System.out.println("Pas de cours");
//        } else {
//            System.out.println(c.toString());
//        }
//        return in;
//    }
//    public static void testLancerVisio() {
//        Service s = new Service();
//        Intervenant in = s.authentificationIntervenant("vhernendez@gmail.com", "mdp1");
//        if (in == null) {
//            System.out.println("Echec de l'authentification authentification, veuillez ré-essayer");
//        } else {
//            System.out.println("L'authentification est un succès : " + in);
//        }
//        Cours c = s.lancerVisio(in);
//    }
//    public static void testLancerEtFinVisioEleve() {
//        Service s = new Service();
//        Intervenant in = s.authentificationIntervenant("vhernendez@gmail.com", "mdp1");
//        if (in == null) {
//            System.out.println("Echec de l'authentification authentification, veuillez ré-essayer");
//        } else {
//            System.out.println("L'authentification est un succès : " + in);
//        }
//        Cours c = s.lancerVisio(in);
//        c = s.terminerVisio(c);
//        int note = Saisie.lireInteger("Saisir une note : 1 / 2 / 3");
//        s.noterCours(c, note);
//    }
//
//    public static void testLancerEtFinVisioIntervenant() {
//        Service s = new Service();
//        Intervenant in = s.authentificationIntervenant("vhernendez@gmail.com", "mdp1");
//        if (in == null) {
//            System.out.println("Echec de l'authentification authentification, veuillez ré-essayer");
//        } else {
//            System.out.println("L'authentification est un succès : " + in);
//        }
//        Cours c = s.lancerVisio(in);
//        c = s.terminerVisio(c);
//        String bilan = Saisie.lireChaine("Envoyez un bilan à " + c.getEleve().getPrenom());
//        s.EnvoyerBilanCours(c, bilan);
//    }
//    public static void testHistoriqueEleve() throws IOException {
//        Eleve e = testAuthEleve2();
//        Service s = new Service();
//        List<Cours> historique = s.getHistoriqueEleve(e);
//        if (historique.isEmpty()) {
//            System.out.println("vous n'avez encore jamais fait de soutien");
//        } else {
//            for (Cours c : historique) {
//                System.out.println(c.toString());
//            }
//        }
//    }
//
//    public static void testHistoriqueIntervenant() throws IOException {
//        Intervenant i = testerAuthByEmailIntervenant2();
//        Service s = new Service();
//        List<Cours> historique = s.getHistoriqueIntervenant(i);
//        if (historique.isEmpty()) {
//            System.out.println("vous n'avez encore jamais donné de soutien");
//        } else {
//            for (Cours c : historique) {
//                System.out.println(c.toString());
//            }
//        }
//    }
    public static void main(String[] args) throws ParseException, IOException {
        JpaUtil.creerFabriquePersistance();
        initialisation();

        boolean quitter = false;
        while (!quitter) {

            Eleve e = null;
            Intervenant i = null;
            Cours c = null;
            boolean connected = false;
            System.out.println("-----------Page d'accueil-------------");
            System.out.println("1.Inscrire un élève");
            System.out.println("2.S'authentifier en tant qu'élève");
            System.out.println("3.S'authentifier en tant qu'intervenant");
            System.out.println("4.Quitter");

            int main_select = Saisie.lireInteger("Saisir le choix : ");

            switch (main_select) {
                case 1:
                    inscriptionEleve();
                    break;
                case 2:
                    e = authentificationEleve();
                    if (e != null) {
                        connected = true;
                    }
                    break;
                case 3:
                    i = authentificationIntervenant();
                    if (i != null) {
                        connected = true;
                    }
                    break;
                case 4:
                    quitter = true;
                    break;
                default:
                    System.out.println("Nombre rentré incorrect. ");
                    break;
            }
            while (connected) {
                System.out.println("---------Page principale------------");
                if (e != null) { //C'est un élève qui est connecté
                    afficherHistoriqueEleve(e);
                    System.out.println("1.Faire une demande de soutien");
                    System.out.println("2.Se déconnecter");
                    int select_eleve = Saisie.lireInteger("Saisir le choix : ");
                    if (select_eleve == 1) {
                        c = demandeCours(e);
                        if (c != null) {
                            boolean makeVisio = attendreDebutVisio(c);
                            if (makeVisio) {
                                System.out.println("----VISIO----");
                                Saisie.lireChaine("Appuyez sur \"Entrée\" pour mettre fin à la visio : ");
                                mettreFinVisioEleve(c);
                            } else {
                                connected = false;
                            }
                        }
                    } else {
                        connected = false;
                    }
                } else {  //Sinon c'est donc un intervenant qui est connecté
                    afficherHistoriqueIntervenant(i);
                    c = afficherDemandeSoutien(i);
                    if (c == null) {
                        System.out.println("2.Consulter tableau de bord des soutiens");
                        System.out.println("3.Se déconnecter");
                    } else {
                        System.out.println("1.Lancer visio");
                        System.out.println("2.Consulter tableau de bord des soutiens");
                        System.out.println("3.Se déconnecter");
                    }
                    int select_intervenant = Saisie.lireInteger("Saisir le choix : ");
                    switch (select_intervenant) {
                        case 1:
                            c = lancerVisio(i);
                            System.out.println("----VISIO----");
                            Saisie.lireChaine("Appuyez sur \"Entrée\" pour mettre fin à la visio : ");
                            mettreFinVisioIntervenant(c);
                            break;
                        case 2:
                            afficherStats();
                            break;
                        case 3:
                            connected = false;
                            break;
                        default:
                            System.out.println("Nombre rentré incorrect. ");
                            break;
                    }
                }
            }
        }
        JpaUtil.fermerFabriquePersistance();
    }
}
