package console;

import dao.JpaUtil;
import java.io.IOException;
import java.util.List;
import metier.model.Cours;
import metier.model.Eleve;
import metier.model.Etablissement;
import metier.model.Intervenant;
import metier.service.Service;
import console.Saisie;
import metier.model.Matiere;


// codes etablissement : 0691664J - 
// intervenants : Intervenant e1 = new Etudiant("FAVRO", "Samuel", "0642049305", 6, 0, "sfavro@gmail.com", "mdp1", "INSA", "Informatique");
//        Intervenant e2 = new Autre("DEKEW", "Simon", "0713200950", 6, 0, "sdekew4845@gmail.com", "mdp1", "Chercheur");
//        Intervenant e3 = new Enseignant("LOU", "Flavien", "0437340532", 6, 0, "flavien.lou@gmail.com", "mp1", "Lycee");
//        Intervenant e4 = new Etudiant("GUOGUEN", "Gabriela", "0719843316", 6, 0, "gguoguen2418@gmail.com", "mdp1", "ENSIMAG", "Chimie");
//        Intervenant e5 = new Autre("HERNENDEZ", "Vincent", "0441564193", 6, 0, "vhernendez@gmail.com", "mdp1", "Ingenieur"); 
public class Main {

    public static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();
        Service service = new Service();
        service.initialiserIntervenant();
        service.initialiserMatiere();

        boolean quitter = false;
        while (!quitter) {
            afficherMenuPrincipal();
            int choix = Saisie.lireInteger("Votre choix: ");
            switch (choix) {
                case 1:
                    try {
                    inscrireEleve(service);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
                case 2:
                    Eleve eleve = service.authentifierEleve(Saisie.lireChaine("Email: "), Saisie.lireChaine("Mot de passe: "));
                    if (eleve != null) {
                        gererMenuEleve(service, eleve);
                    }
                    break;
                case 3:
                    Intervenant intervenant = service.authentificationIntervenant(Saisie.lireChaine("Email: "), Saisie.lireChaine("Mot de passe: "));
                    if (intervenant != null) {
                        gererMenuIntervenant(service, intervenant);
                    }
                    break;
                case 4:
                    quitter = true;
                    break;
                default:
                    System.out.println("Choix non valide, veuillez réessayer.");
            }
        }
        JpaUtil.fermerFabriquePersistance();
    }

    private static void afficherMenuPrincipal() {
        System.out.println("\nMenu Principal:");
        System.out.println("1. Inscription Élève");
        System.out.println("2. Authentification Élève");
        System.out.println("3. Authentification Intervenant");
        System.out.println("4. Quitter");
    }

    private static void inscrireEleve(Service service) throws IOException {
        System.out.println("Inscription d'un nouvel élève");
        String nom = Saisie.lireChaine("Nom: ");
        String prenom = Saisie.lireChaine("Prénom: ");
        String dateNaissance = Saisie.lireChaine("Date de naissance (YYYY-MM-DD): ");
        String email = Saisie.lireChaine("Email: ");
        String motDePasse = Saisie.lireChaine("Mot de passe: ");
        String codeEtablissement = Saisie.lireChaine("Code établissement: ");
        int niveau = Saisie.lireInteger("Niveau : (0 à 6) ");
        // Adaptez cette partie pour créer un élève avec les bonnes informations
        Eleve eleve = new Eleve(nom, prenom, dateNaissance, email, motDePasse, niveau); // Supposons un constructeur adapté

        if (service.inscrireEleve(eleve, codeEtablissement)) {
            System.out.println("Inscription réussie. Bienvenue " + prenom + " !");
        } else {
            System.out.println("Échec de l'inscription. Veuillez réessayer.");
        }
    }

    // Méthode gererMenuEleve avec toutes les options disponibles
    private static void gererMenuEleve(Service service, Eleve eleve) {
        boolean retour = false;
        Cours coursActuel = null; // Utilisé pour stocker le dernier cours demandé ou en cours

        while (!retour) {
            System.out.println("\nMenu Élève:");
            System.out.println("1. Demander un soutien");
            System.out.println("2. Terminer la visio");
            System.out.println("3. Noter la visio");
            System.out.println("4. Consulter Historique");
            System.out.println("5. Retour");

            int choix = Saisie.lireInteger("Votre choix: ");
            switch (choix) {
                 case 1:
                List<Matiere> matieres = service.consulterListeMatieres();
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
                    Cours cours = service.demanderCours(eleve, matiereChoisie.getNomMatiere(), message);
                    if (cours != null) {
                        System.out.println("Demande de soutien enregistrée pour la matière " + matiereChoisie.getNomMatiere() + ".");
                    } else {
                        System.out.println("Erreur lors de la demande de soutien. Aucun intervenant disponible.");
                    }
                }
                break;
                case 2:
                    if (coursActuel != null) {
                        service.terminerVisio(coursActuel);
                        System.out.println("La visio a été terminée.");
                    } else {
                        System.out.println("Aucune visio à terminer.");
                    }
                    break;
                case 3:
                    if (coursActuel != null && coursActuel.getEtatCours().equals(metier.model.etat.TERMINE)) {
                        int note = Saisie.lireInteger("Votre note pour la visio (1-3): ");
                        service.noterCours(coursActuel, note);
                        System.out.println("Visio notée avec succès.");
                    } else {
                        System.out.println("Aucune visio terminée à noter.");
                    }
                    break;
                case 4:
                    List<Cours> historique = service.getHistoriqueEleve(eleve);
                    if (historique.isEmpty()) {
                        System.out.println("Aucun historique disponible.");
                    } else {
                        historique.forEach(System.out::println);
                    }
                    break;
                case 5:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez essayer à nouveau.");
            }
        }
    }

// Méthode gererMenuIntervenant avec toutes les options disponibles
    private static void gererMenuIntervenant(Service service, Intervenant intervenant) {
        boolean retour = false;
        Cours coursActuel = null; // Pour stocker le cours actuel

        while (!retour) {
            System.out.println("\nMenu Intervenant:");
            System.out.println("1. Consulter demandes de soutien");
            System.out.println("2. Lancer la visio");
            System.out.println("3. Terminer la visio");
            System.out.println("4. Commenter le cours");
            System.out.println("5. Consulter statistiques");
            System.out.println("6. Retour");

            int choix = Saisie.lireInteger("Votre choix: ");
            switch (choix) {
                case 1:
                    Object[] demande = service.checkDemandeSoutien(intervenant);
                    if (demande[0] != null) {
                        coursActuel = (Cours) demande[0];
                        Eleve e = (Eleve) demande[1];
                        System.out.println("Demande de soutien de " + e.getPrenom() + " pour le cours de " + coursActuel.getMatiere().getNomMatiere());
                    } else {
                        System.out.println("Pas de demandes de soutien en attente.");
                    }
                    break;
                case 2:
                    if (coursActuel != null) {
                        coursActuel = service.lancerVisio(intervenant);
                        System.out.println("Visio lancée.");
                    } else {
                        System.out.println("Pas de cours à lancer.");
                    }
                    break;
                case 3:
                    if (coursActuel != null) {
                        coursActuel = service.terminerVisio(coursActuel);
                        System.out.println("Visio terminée.");
                    } else {
                        System.out.println("Pas de cours à terminer.");
                    }
                    break;
                case 4:
                    if (coursActuel != null && coursActuel.getEtatCours().equals(metier.model.etat.TERMINE)) {
                        String bilan = Saisie.lireChaine("Entrez votre commentaire pour le cours: ");
                        service.EnvoyerBilanCours(coursActuel, bilan);
                        System.out.println("Bilan envoyé à l'élève.");
                    } else {
                        System.out.println("Aucun cours terminé à commenter.");
                    }
                    break;
                case 5:
                    consulterStatistiques(service);
                    break;
                case 6:
                    retour = true;
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez essayer à nouveau.");
            }
        }
    }

// Méthode pour consulter les statistiques dans le menu Intervenant
    private static void consulterStatistiques(Service service) {
        List<Etablissement> etablissementsIPSBas = service.statEtablissementIPS();
        System.out.println("Etablissements avec un IPS bas:");
        etablissementsIPSBas.forEach(e -> System.out.println(e.getNomEtablissement() + " - " + e.getCommune()));

        Object[] soutiensEtDuree = service.statNbSoutienetDuree();
        System.out.println("Nombre total de soutiens: " + soutiensEtDuree[0]);
        System.out.println("Durée moyenne des soutiens: " + soutiensEtDuree[1] + " minutes");

        List<Object[]> nbEtabParCommune = service.statNbEtablissementParCommune();
        System.out.println("Nombre d'établissements par commune:");
        nbEtabParCommune.forEach(entry -> System.out.println(entry[0] + ": " + entry[1]));
    }

}
