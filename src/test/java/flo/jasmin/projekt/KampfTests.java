package flo.jasmin.projekt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import flo.jasmin.projekt.application.Kampf;
import flo.jasmin.projekt.domain.Akteure.Goblin;
import flo.jasmin.projekt.domain.Akteure.TeamWesen;
import flo.jasmin.projekt.domain.Akteure.Wesen;
import flo.jasmin.projekt.domain.Exceptions.ZielIstSpielerWesen;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Banane;

public class KampfTests {

    Kampf testKampf;

    @Test
    void reihenfolgeNachHöchsterInitiativeGestellt(){
        ArrayList<Wesen> alleWesen = new ArrayList<>();
        TeamWesen wesen1 = new TeamWesen(0, 0, 0, 2, 0, "Wesen1");
        TeamWesen wesen2 = new TeamWesen(0, 0, 0, 1, 0, "Wesen2");
        Goblin goblin1 = new Goblin(); //Goblins haben initiative 3

        alleWesen.add(goblin1);
        alleWesen.add(wesen1);
        alleWesen.add(wesen2);

        ArrayList<Wesen> erwartetesErgebnis = new ArrayList<>();
        erwartetesErgebnis.add(goblin1);
        erwartetesErgebnis.add(wesen1);
        erwartetesErgebnis.add(wesen2);

        testKampf = new Kampf(alleWesen);
        assertEquals(erwartetesErgebnis, testKampf.getAlleWesen());
    }


    @Test
    void gegnerGreiftZuerstAnWennInitiativeAmHöchsten(){
        ArrayList<Wesen> alleWesen = new ArrayList<>();
        TeamWesen wesen1 = new TeamWesen(10, 0, 0, 2, 0, "Wesen1");
        TeamWesen wesen2 = new TeamWesen(10, 0, 0, 1, 0, "Wesen2");
        Goblin goblin1 = new Goblin(); //Goblins haben initiative 3

        alleWesen.add(goblin1);
        alleWesen.add(wesen1);
        alleWesen.add(wesen2);
        testKampf = new Kampf(alleWesen);
        ArrayList<String> ergebnis = new ArrayList<>();
        ergebnis.add("Goblin greift an.");
        ergebnis.add("Wesen1 nimmt 5 Schaden. HP übrig: 5");
        ergebnis.add("Goblin : 0");
        ergebnis.add("Wesen1 : 1");
        ergebnis.add("Wesen2 : 2");
        ergebnis.add("Wesen1 ist an der Reihe");
        ergebnis.add("Gegnerauswahl: ");
        ergebnis.add("Goblin : 0\n");

        assertEquals(ergebnis, testKampf.gegnerGreiftAn());

    }
    @Test
    void spielerVorGegnernAmZug(){
        ArrayList<Wesen> alleWesen = new ArrayList<>();
        TeamWesen wesen1 = new TeamWesen(0, 0, 0, 8, 0, "Wesen1");
        TeamWesen wesen2 = new TeamWesen(0, 0, 0, 1, 0, "Wesen2");
        Goblin goblin1 = new Goblin(); //Goblins haben initiative 3

        alleWesen.add(goblin1);
        alleWesen.add(wesen1);
        alleWesen.add(wesen2);
        testKampf = new Kampf(alleWesen);
        ArrayList<String> ergebnis = new ArrayList<>();
        ergebnis.add("Wesen1 : 0");
        ergebnis.add("Goblin : 1");
        ergebnis.add("Wesen2 : 2");
        ergebnis.add("Wesen1 ist an der Reihe");
        ergebnis.add("Gegnerauswahl: ");
        ergebnis.add("Goblin : 1\n");

        assertEquals(ergebnis, testKampf.gegnerGreiftAn());
    }

    @Test
    void spielerGreiftGegnerAn() throws IndexOutOfBoundsException, ZielIstSpielerWesen{
        ArrayList<Wesen> alleWesen = new ArrayList<>();
        TeamWesen wesen1 = new TeamWesen(10, 0, 5, 8, 0, "Wesen1");
        TeamWesen wesen2 = new TeamWesen(10, 0, 0, 1, 0, "Wesen2");
        Goblin goblin1 = new Goblin(); //Goblins haben initiative 3

        alleWesen.add(goblin1);
        alleWesen.add(wesen1);
        alleWesen.add(wesen2);
        testKampf = new Kampf(alleWesen);
        
        ArrayList<String> ergebnis = new ArrayList<>();
        ergebnis.add("Goblin nimmt 5 Schaden. HP übrig: 5");
        ergebnis.add("Goblin greift an.");
        ergebnis.add("Wesen1 nimmt 5 Schaden. HP übrig: 5");
        ergebnis.add("Wesen1 : 0");
        ergebnis.add("Goblin : 1");
        ergebnis.add("Wesen2 : 2");
        ergebnis.add("Wesen2 ist an der Reihe");
        ergebnis.add("Gegnerauswahl: ");
        ergebnis.add("Goblin : 1\n");


        assertEquals(ergebnis, testKampf.überMittelZiel(1));
        assertEquals(5, goblin1.getGesundheit());
    }

    @Test
    void zielIstSpielerWesenWirftFehler(){
        ArrayList<Wesen> alleWesen = new ArrayList<>();
        TeamWesen wesen1 = new TeamWesen(10, 0, 5, 8, 0, "Wesen1");
        TeamWesen wesen2 = new TeamWesen(10, 0, 0, 1, 0, "Wesen2");
        Goblin goblin1 = new Goblin(); //Goblins haben initiative 3

        alleWesen.add(goblin1);
        alleWesen.add(wesen1);
        alleWesen.add(wesen2);
        testKampf = new Kampf(alleWesen);
        
        assertThrows(ZielIstSpielerWesen.class, () -> testKampf.überMittelZiel(2));
    }

    @Test
    void zielExistiertNicht(){
        ArrayList<Wesen> alleWesen = new ArrayList<>();
        TeamWesen wesen1 = new TeamWesen(10, 0, 5, 8, 0, "Wesen1");
        TeamWesen wesen2 = new TeamWesen(10, 0, 0, 1, 0, "Wesen2");
        Goblin goblin1 = new Goblin(); //Goblins haben initiative 3

        alleWesen.add(goblin1);
        alleWesen.add(wesen1);
        alleWesen.add(wesen2);
        testKampf = new Kampf(alleWesen);
        
        assertThrows(IndexOutOfBoundsException.class, () -> testKampf.überMittelZiel(8));
    }

    //Test für gegner geht down und droppt Items
    @Test
    void gegnerGibtGegenständeNachKO() throws IndexOutOfBoundsException, ZielIstSpielerWesen{
        ArrayList<Wesen> alleWesen = new ArrayList<>();
        TeamWesen wesen1 = new TeamWesen(10, 0, 50, 8, 0, "Wesen1");
        TeamWesen wesen2 = new TeamWesen(10, 0, 0, 1, 0, "Wesen2");
        Goblin goblin1 = new Goblin(); //Goblins haben initiative 3

        alleWesen.add(goblin1);
        alleWesen.add(wesen1);
        alleWesen.add(wesen2);
        testKampf = new Kampf(alleWesen);

        testKampf.überMittelZiel(1);
        ArrayList<Gegenstand> ergebnis = new ArrayList<>();
        ergebnis.add(new Banane());
        assertEquals(ergebnis, testKampf.getVerloreneGegenstände());
    }
    

    //Test für alle gegner sind besiegt
    @Test
    void alleGegnerBesigt() throws IndexOutOfBoundsException, ZielIstSpielerWesen{
        ArrayList<Wesen> alleWesen = new ArrayList<>();
        TeamWesen wesen1 = new TeamWesen(10, 0, 50, 8, 0, "Wesen1");
        TeamWesen wesen2 = new TeamWesen(10, 0, 0, 1, 0, "Wesen2");
        Goblin goblin1 = new Goblin(); //Goblins haben initiative 3

        alleWesen.add(goblin1);
        alleWesen.add(wesen1);
        alleWesen.add(wesen2);
        testKampf = new Kampf(alleWesen);

        assertTrue(testKampf.überMittelZiel(1).contains("Du hast gewonnen. Alle Gegner wurden besiegt!"));
        assertFalse(testKampf.isKampfImGange());
    }
    //Test für alle SpielerWesen sind besiegt
    @Test
    void alleTeamwesenBesiegt() throws IndexOutOfBoundsException, ZielIstSpielerWesen{
        ArrayList<Wesen> alleWesen = new ArrayList<>();
        TeamWesen wesen1 = new TeamWesen(1, 0, 50, 1, 0, "Wesen1");
        Goblin goblin1 = new Goblin(); //Goblins haben initiative 3

        alleWesen.add(goblin1);
        alleWesen.add(wesen1);
        testKampf = new Kampf(alleWesen);

        assertTrue(testKampf.gegnerGreiftAn().contains("Dein gesamtes Team wurde besiegt!"));
        assertFalse(testKampf.isKampfImGange());
    }

    // test für das Index richtig ist, wenn ein Wesen aus der Liste entfernt wird, damit das nicht ausversehen geskippt wird

    @Test
    void gegnerBesiegtIndexRichtigBewegt_BesiegterGegnerVorMomentanemWesen() throws IndexOutOfBoundsException, ZielIstSpielerWesen{
        ArrayList<Wesen> alleWesen = new ArrayList<>();
        TeamWesen wesen1 = new TeamWesen(10, 0, 50, 2, 0, "Wesen1");
        TeamWesen wesen2 = new TeamWesen(10, 0, 0, 1, 0, "Wesen2");
        Goblin goblin1 = new Goblin(); //Goblins haben initiative 3

        alleWesen.add(goblin1);
        alleWesen.add(wesen1);
        alleWesen.add(wesen2);
        testKampf = new Kampf(alleWesen);

        testKampf.gegnerGreiftAn();
        testKampf.überMittelZiel(0);
        assertEquals(wesen2, testKampf.getMomentanesWesen());
    }

        @Test
    void gegnerBesiegtIndexRichtigBewegt_NachMomentanenWesen() throws IndexOutOfBoundsException, ZielIstSpielerWesen{
        ArrayList<Wesen> alleWesen = new ArrayList<>();
        TeamWesen wesen1 = new TeamWesen(10, 0, 50, 8, 0, "Wesen1");
        TeamWesen wesen2 = new TeamWesen(10, 0, 0, 1, 0, "Wesen2");
        Goblin goblin1 = new Goblin(); //Goblins haben initiative 3

        alleWesen.add(goblin1);
        alleWesen.add(wesen1);
        alleWesen.add(wesen2);
        testKampf = new Kampf(alleWesen);

        testKampf.gegnerGreiftAn();
        testKampf.überMittelZiel(1);
        assertEquals(wesen2, testKampf.getMomentanesWesen());
    }
    

}
