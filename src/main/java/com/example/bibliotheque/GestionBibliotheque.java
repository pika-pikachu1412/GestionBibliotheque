package com.example.bibliotheque;

import java.io.*;
import java.util.*;

public class GestionBibliotheque {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bibliotheque bibliotheque = new Bibliotheque();

        // Charger les livres à partir du fichier si disponible
        try {
            bibliotheque.chargerLivres("livres.dat");
            System.out.println("Données chargées avec succès.");
        } catch (Exception e) {
            System.out.println("Aucune donnée précédente trouvée.");
        }

        while (true) {
            System.out.println("\n--- Menu Bibliothèque ---");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Afficher les livres");
            System.out.println("3. Rechercher un livre");
            System.out.println("4. Modifier un livre");
            System.out.println("5. Supprimer un livre");
            System.out.println("6. Emprunter/Retourner un livre");
            System.out.println("7. Sauvegarder et quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    ajouterLivre(bibliotheque, scanner);
                    break;
                case 2:
                    afficherLivres(bibliotheque);
                    break;
                case 3:
                    rechercherLivre(bibliotheque, scanner);
                    break;
                case 4:
                    modifierLivre(bibliotheque, scanner);
                    break;
                case 5:
                    supprimerLivre(bibliotheque, scanner);
                    break;
                case 6:
                    emprunterRetournerLivre(bibliotheque, scanner);
                    break;
                case 7:
                    try {
                        bibliotheque.sauvegarderLivres("livres.dat");
                        System.out.println("Données sauvegardées. Au revoir !");
                    } catch (IOException e) {
                        System.out.println("Erreur lors de la sauvegarde des données.");
                    }
                    return;
                default:
                    System.out.println("Choix invalide, réessayez.");
            }
        }
    }

    private static void ajouterLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.print("ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne
        System.out.print("Titre : ");
        String titre = scanner.nextLine();
        System.out.print("Auteur : ");
        String auteur = scanner.nextLine();
        System.out.print("Année de publication : ");
        int annee = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne
        System.out.print("Genre : ");
        String genre = scanner.nextLine();

        Livre livre = new Livre(id, titre, auteur, annee, genre);
        bibliotheque.ajouterLivre(livre);
        System.out.println("Livre ajouté avec succès.");
    }

    private static void afficherLivres(Bibliotheque bibliotheque) {
        List<Livre> livres = bibliotheque.getLivres();
        if (livres.isEmpty()) {
            System.out.println("Aucun livre dans la bibliothèque.");
        } else {
            System.out.println("Livres dans la bibliothèque : ");
            for (Livre livre : livres) {
                System.out.println(livre);
            }
        }
    }

    private static void rechercherLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.print("Entrez le titre ou l'auteur à rechercher : ");
        String recherche = scanner.nextLine();
        List<Livre> resultats = bibliotheque.rechercherParTitreOuAuteur(recherche);
        if (resultats.isEmpty()) {
            System.out.println("Aucun livre trouvé.");
        } else {
            for (Livre livre : resultats) {
                System.out.println(livre);
            }
        }
    }

    private static void modifierLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.print("Entrez l'ID du livre à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne
        Livre livre = bibliotheque.rechercherParId(id);
        if (livre == null) {
            System.out.println("Livre introuvable.");
            return;
        }

        System.out.print("Nouveau titre : ");
        String titre = scanner.nextLine();
        System.out.print("Nouvel auteur : ");
        String auteur = scanner.nextLine();
        System.out.print("Nouvelle année de publication : ");
        int annee = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne
        System.out.print("Nouveau genre : ");
        String genre = scanner.nextLine();

        livre.modifier(titre, auteur, annee, genre);
        System.out.println("Livre modifié avec succès.");
    }

    private static void supprimerLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.print("Entrez l'ID du livre à supprimer : ");
        int id = scanner.nextInt();
        bibliotheque.supprimerLivre(id);
        System.out.println("Livre supprimé avec succès.");
    }

    private static void emprunterRetournerLivre(Bibliotheque bibliotheque, Scanner scanner) {
        System.out.print("Entrez l'ID du livre à emprunter/retourner : ");
        int id = scanner.nextInt();
        Livre livre = bibliotheque.rechercherParId(id);
        if (livre == null) {
            System.out.println("Livre introuvable.");
            return;
        }

        if (livre.isEmprunte()) {
            livre.setEmprunte(false);
            System.out.println("Livre retourné avec succès.");
        } else {
            livre.setEmprunte(true);
            System.out.println("Livre emprunté avec succès.");
        }
    }
}