package flo.jasmin.projekt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen.Ausstattung;
import flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen.Holzschwert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flo.jasmin.projekt.application.Spiel;
import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Doerfer.Dorf;
import flo.jasmin.projekt.domain.Status;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.Zutaten.Zutat;
import flo.jasmin.projekt.domain.Gegenstaende.Zutaten.Karotte;
import flo.jasmin.projekt.domain.Values.Einkauf;

public class EinkaufTests {
    Spiel testSpiel;


    @BeforeEach
    void setup(){
        testSpiel = new Spiel();
        testSpiel.getTeam().getInventar().setErspartes(12);
    }

    @Test
    void erfolgreicherKauf(){
        Map<Gegenstand, Integer> auswahl = new HashMap<>();
        auswahl.put(new Karotte(), 2);
        auswahl.put(new Holzschwert(), 1);

        int gesamtPreis = Dorf.gesamtpreisBerechnen(auswahl);
        testSpiel.setEinkauf(new Einkauf(auswahl, gesamtPreis));
        testSpiel.setStatus(Status.EINKAUF);
        testSpiel.spieleBefehl(Befehl.JA, null);

        Map<Zutat, Integer> korrektesErgebnisZutaten = new HashMap<>();
        korrektesErgebnisZutaten.put(new Karotte(), 2);
        ArrayList<Ausstattung> korrektesErgebnisAusruestung = new ArrayList<>();
        korrektesErgebnisAusruestung.add(new Holzschwert());
        assertEquals(korrektesErgebnisZutaten, testSpiel.getTeam().getInventar().getZutaten());
        assertEquals(korrektesErgebnisAusruestung, testSpiel.getTeam().getInventar().getAusruestung());
    }

    @Test
    void zuTeurerEinkauf(){
        Map<Gegenstand, Integer> auswahl = new HashMap<>();
        auswahl.put(new Karotte(), 100);
        int gesamtPreis = Dorf.gesamtpreisBerechnen(auswahl);
        testSpiel.setEinkauf(new Einkauf(auswahl, gesamtPreis));
        testSpiel.setStatus(Status.EINKAUF);
        testSpiel.spieleBefehl(Befehl.JA, null);

        Map<Zutat, Integer> korrektesErgebnis = new HashMap<>();
        assertEquals(korrektesErgebnis, testSpiel.getTeam().getInventar().getZutaten());
    }
}
