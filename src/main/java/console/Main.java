package console;

import dao.JpaUtil;
import java.io.IOException;
import java.util.List;
import metier.model.Cours;
import metier.model.Eleve;
import metier.model.Etablissement;
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
        Eleve e3 = new Eleve("Noir", "Chantal", "2004/07/05", "cnoir@gmail.com", "mdp", 2);
        s.inscrireEleve(e3, "0332490C");

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

    public static void inscriptionEleve(Service s) throws IOException {
        System.out.println("-------INSCRIPTION D'UN ÉLÈVE-------");
        String nom = Saisie.lireChaine("Saisir un Nom : ");
        String prenom = Saisie.lireChaine("Saisir un Prénom : ");
        String dateNaissance = Saisie.lireChaine("Saisir une date de naissance au format YYYY/MM/DD : ");
        String email = Saisie.lireChaine("Saisir un E-mail : ");
        String mdp = Saisie.lireChaine("Saisir un Mot de Passe : ");
        String codeEt = Saisie.lireChaine("Saisir un code etablissement : ");
        int niveau = Saisie.lireInteger("Saisir votre classe (de 0 à 6) : ");

        Eleve eleve1 = new Eleve(nom, prenom, dateNaissance, email, mdp, niveau);

        s.inscrireEleve(eleve1, codeEt);

        System.out.println(eleve1.getPrenom() + ", vous êtes bien inscrit. Vous pouvez maintenant vous connecter.");
    }

    public static Eleve authentificationEleve(Service s) throws IOException {
        System.out.println("-------AUTHENTIFICATION D'UN ÉLÈVE-------");
        String email = Saisie.lireChaine("Saisissez votre E-mail : ");
        String mdp = Saisie.lireChaine("Saisissez votre Mot de Passe : ");

        Eleve eleve = s.authentifierEleve(email, mdp);

        if (eleve == null) {
            System.out.println("Echec de l'authentification, veuillez ré-essayer");
        } else {
            System.out.println("L'authentification est un succès.\nBonjour : " + eleve.getPrenom());
            System.out.println(eleve.toString());
        }
        return eleve;
    }

    public static Intervenant authentificationIntervenant(Service s) throws ParseException {
        System.out.println("-------AUTHENTIFICATION D'UN INTERVENANT-------");
        String email = Saisie.lireChaine("Saisissez votre E-mail : ");
        String mdp = Saisie.lireChaine("Saisissez votre Mot de Passe : ");

        Intervenant in = s.authentificationIntervenant(email, mdp);

        if (in == null) {
            System.out.println("Echec de l'authentification, veuillez ré-essayer");
        } else {
            System.out.println("L'authentification est un succès.\nBonjour : " + in.getPrenom());
            System.out.println(in.toString());
        }
        return in;
    }

    public static void afficherHistoriqueEleve(Service s, Eleve e) throws IOException {
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

    public static void afficherHistoriqueIntervenant(Service s, Intervenant i) throws IOException {
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

    public static Cours demandeCours(Service s, Eleve e) throws IOException {
        Cours cours = null;
        List<Matiere> matieres = s.consulterListeMatieres();
                System.out.println("Matieres disponibles :");
                for (int i = 0; i < matieres.size(); i++) {
                    System.out.println((i + 1) + ". " + matieres.get(i).getNomMatiere());
                }
                int choixMatiere = Saisie.lireInteger("Choisissez une matière par son numéro: ");
                if (choixMatiere < 1 || choixMatiere > matieres.size()) {
                    System.out.println("Choix invalide. Veuillez réessayer.");
                } else {
                    String message = Saisie.lireChaine("Message pour l'intervenant: ");
                    Matiere matiereChoisie = matieres.get(choixMatiere - 1);
                    cours = s.demanderCours(e, matiereChoisie.getNomMatiere(), message);
                    if (cours != null) {
                        System.out.println("Demande de soutien enregistrée pour la matière " + matiereChoisie.getNomMatiere() + ".");
                    } else {
                        System.out.println("Erreur lors de la demande de soutien. Aucun intervenant disponible.");
                    }
                }
        return cours;
    }

    public static Cours afficherDemandeSoutien(Service s, Intervenant i) throws IOException {

        Cours c = s.CheckDemandeSoutien(i);
        if (c == null) {
            System.out.println("vous n'avez pas de soutien demandé actuellement");
        } else {
            System.out.println("Veuillez prendre en charge le soutien \n" + c.toString() + "\n demandé par :\n " + c.getEleve() + " en " + c.getEleve().getNiveau() +"ème.");
        }
        return c;
    }


    public static boolean attendreDebutVisio(Service s, Cours c) {
//        while (s.CheckVisioEnCours(c)) { //on attends tant que le visio n'a pas commencé (tant que le cours n'est pas passé à l'état EN_COURS)
//        }
//        return;
        System.out.println("1.On simule le lancement de la visio par l'intervenant");
        System.out.println("2.On se déconnecte pour passer sur la page de l'intervenant ou d'un autre élève");
        int choix = Saisie.lireInteger("Choix : ");
        return choix == 1;
    }

    public static Cours mettreFinVisioEleve(Service s, Cours c) {
        s.terminerVisio(c);
        System.out.println("----FIN VISIO----");
        int note = Saisie.lireInteger("A quel point cette séance de soutien vous a-t-elle aidé sur une échelle de 1 à 10 : ");
        s.noterCours(c, note);
        return c;
    }

    public static void mettreFinVisioIntervenant(Service s, Cours c) {
        s.terminerVisio(c);
        System.out.println("----FIN VISIO----");
        String bilan = Saisie.lireChaine("Rédigez un bilan de la séance de soutien à destination de " + c.getEleve().getPrenom() + " : ");
        s.EnvoyerBilanCours(c, bilan);
        return;
    }

    private static void afficherStatistiques(Service s) {
        List<Etablissement> etablissementsIPSBas = s.statEtablissementIPS();
        System.out.println("Etablissements avec un IPS bas:");
        etablissementsIPSBas.forEach(e -> System.out.println(e.getNomEtablissement() + " - " + e.getCommune()));

        Object[] soutiensEtDuree = s.statNbSoutienetDuree();
        System.out.println("Nombre total de soutiens: " + soutiensEtDuree[0]);
        System.out.println("Durée moyenne des soutiens: " + soutiensEtDuree[1] + " minutes");

        List<Object[]> nbEtabParCommune = s.statNbEtablissementParCommune();
        System.out.println("Nombre d'établissements par commune:");
        nbEtabParCommune.forEach(entry -> System.out.println(entry[0] + ": " + entry[1]));
    }
   
    public static void main(String[] args) throws ParseException, IOException {
        JpaUtil.creerFabriquePersistance();
        Service service = new Service();
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
                    inscriptionEleve(service);
                    break;
                case 2:
                    e = authentificationEleve(service);
                    if (e != null) {
                        connected = true;
                    }
                    break;
                case 3:
                    i = authentificationIntervenant(service);
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
                    afficherHistoriqueEleve(service, e);
                    System.out.println("1.Faire une demande de soutien");
                    System.out.println("2.Se déconnecter");
                    int select_eleve = Saisie.lireInteger("Saisir le choix : ");
                    if (select_eleve == 1) {
                        c = demandeCours(service, e);
                        if (c != null) {
                            boolean makeVisio = attendreDebutVisio(service, c);
                            if (makeVisio) {
                                System.out.println("----VISIO----");
                                Saisie.lireChaine("Appuyez sur \"Entrée\" pour mettre fin à la visio : ");
                                mettreFinVisioEleve(service, c);
                            } else {
                                connected = false;
                            }
                        }
                    } else {
                        connected = false;
                    }
                } else {  //Sinon c'est donc un intervenant qui est connecté
                    afficherHistoriqueIntervenant(service, i);
                    c = afficherDemandeSoutien(service, i);
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
                            c = service.lancerVisio(i);
                            System.out.println("----VISIO----");
                            Saisie.lireChaine("Appuyez sur \"Entrée\" pour mettre fin à la visio : ");
                            mettreFinVisioIntervenant(service, c);
                            break;
                        case 2:
                            afficherStatistiques(service);
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
