package com.example.bibliotheque;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class BibliothequeTest {

    private Bibliotheque bibliotheque;
    private Livre livre;

    @BeforeEach
    void setUp() {
        // Initialisation d'une nouvelle bibliothèque et d'un livre avant chaque test
        bibliotheque = new Bibliotheque();
        livre = new Livre(1, "Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "Fiction");
    }

    @Test
    void testAjouterLivre() {
        bibliotheque.ajouterLivre(livre);
        assertEquals(1, bibliotheque.getLivres().size(), "Le livre devrait être ajouté à la bibliothèque");
    }

    @Test
    void testRechercherParId() {
        bibliotheque.ajouterLivre(livre);
        Livre livreTrouve = bibliotheque.rechercherParId(1);
        assertNotNull(livreTrouve, "Le livre avec l'ID 1 devrait être trouvé");
        assertEquals(livre.getTitre(), livreTrouve.getTitre(), "Les titres des livres doivent correspondre");
    }

    @Test
    void testRechercherParTitreOuAuteur() {
        bibliotheque.ajouterLivre(livre);
        List<Livre> resultats = bibliotheque.rechercherParTitreOuAuteur("Petit Prince");
        assertFalse(resultats.isEmpty(), "Il devrait y avoir des résultats de recherche pour 'Petit Prince'");
        assertEquals(1, resultats.size(), "Il devrait y avoir exactement un livre trouvé");
    }

    @Test
    void testModifierLivre() {
        bibliotheque.ajouterLivre(livre);
        Livre livreToModify = bibliotheque.rechercherParId(1);
        assertNotNull(livreToModify, "Le livre à modifier devrait être trouvé");

        livreToModify.modifier("Le Petit Prince Modifié", "Antoine de Saint-Exupéry", 1943, "Aventure");
        assertEquals("Le Petit Prince Modifié", livreToModify.getTitre(), "Le titre du livre devrait être modifié");
        assertEquals("Aventure", livreToModify.getGenre(), "Le genre du livre devrait être modifié");
    }

    @Test
    void testSupprimerLivre() {
        bibliotheque.ajouterLivre(livre);
        bibliotheque.supprimerLivre(1);
        Livre livreSupprime = bibliotheque.rechercherParId(1);
        assertNull(livreSupprime, "Le livre devrait avoir été supprimé");
    }

    @Test
    void testEmprunterRetournerLivre() {
        bibliotheque.ajouterLivre(livre);
        Livre livreEmprunte = bibliotheque.rechercherParId(1);

        assertNotNull(livreEmprunte, "Le livre à emprunter devrait exister");
        assertFalse(livreEmprunte.isEmprunte(), "Le livre ne devrait pas être emprunté au départ");

        livreEmprunte.setEmprunte(true);
        assertTrue(livreEmprunte.isEmprunte(), "Le livre devrait être emprunté après avoir appelé setEmprunte(true)");

        livreEmprunte.setEmprunte(false);
        assertFalse(livreEmprunte.isEmprunte(), "Le livre devrait être retourné après avoir appelé setEmprunte(false)");
    }

    @Test
    void testSauvegardeEtChargement() {
        bibliotheque.ajouterLivre(livre);
        try {
            bibliotheque.sauvegarderLivres("livres_test.dat");

            Bibliotheque nouvelleBibliotheque = new Bibliotheque();
            nouvelleBibliotheque.chargerLivres("livres_test.dat");

            assertEquals(1, nouvelleBibliotheque.getLivres().size(), "Les livres sauvegardés doivent être correctement chargés");
            assertEquals("Le Petit Prince", nouvelleBibliotheque.getLivres().get(0).getTitre(), "Le titre du livre chargé doit correspondre");
        } catch (Exception e) {
            fail("Une exception a été lancée pendant la sauvegarde ou le chargement des livres : " + e.getMessage());
        }
    }
}
