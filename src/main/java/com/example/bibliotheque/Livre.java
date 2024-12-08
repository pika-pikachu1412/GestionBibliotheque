package com.example.bibliotheque;

import java.io.*;
import java.util.*;

class Livre implements Serializable {
    private int id;
    private String titre;
    private String auteur;
    private int anneePublication;
    private String genre;
    private boolean estEmprunte;

    public Livre(int id, String titre, String auteur, int anneePublication, String genre) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.genre = genre;
        this.estEmprunte = false;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isEmprunte() {
        return estEmprunte;
    }

    public void setEmprunte(boolean estEmprunte) {
        this.estEmprunte = estEmprunte;
    }

    public void modifier(String titre, String auteur, int anneePublication, String genre) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Titre: " + titre + ", Auteur: " + auteur +
                ", Année de publication: " + anneePublication + ", Genre: " + genre +
                ", Emprunté: " + (estEmprunte ? "Oui" : "Non");
    }
}
