package com.example.bibliotheque;

import java.io.*;
import java.util.*;

class Bibliotheque {
    private List<Livre> livres;

    public Bibliotheque() {
        this.livres = new ArrayList<>();
    }

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public Livre rechercherParId(int id) {
        for (Livre livre : livres) {
            if (livre.getId() == id) {
                return livre;
            }
        }
        return null;
    }

    public List<Livre> rechercherParTitreOuAuteur(String recherche) {
        List<Livre> resultats = new ArrayList<>();
        for (Livre livre : livres) {
            if (livre.getTitre().toLowerCase().contains(recherche.toLowerCase()) ||
                    livre.getAuteur().toLowerCase().contains(recherche.toLowerCase())) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    public void supprimerLivre(int id) {
        Livre livre = rechercherParId(id);
        if (livre != null) {
            livres.remove(livre);
        }
    }

    public void sauvegarderLivres(String fichier) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fichier))) {
            out.writeObject(livres);
        }
    }

    public void chargerLivres(String fichier) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fichier))) {
            livres = (List<Livre>) in.readObject();
        }
    }
}