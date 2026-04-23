package flo.jasmin.projekt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flo.jasmin.projekt.domain.Inventar;
import flo.jasmin.projekt.domain.Exceptions.NichtGenugErsparrtes;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.Zutat;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteAusstattung.Holzschwert;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Karotte;

public class InventarTests {

    Inventar testInventar;

    @BeforeEach
    void setup(){
        testInventar = new Inventar();
        testInventar.setErspartes(5);
    }

    @Test
    void nichtGenugErsparrtesFehlerWennZuWenigGeld(){
        assertThrows(NichtGenugErsparrtes.class, () -> testInventar.geldEntfernen(6));
    }

    @Test 
    void gemischteGegenstaendeKorrektEingeordnet(){
        Map<Gegenstand, Integer> eingabe = new HashMap<>();
        eingabe.put(new Karotte(), 5);
        eingabe.put(new Holzschwert(), 2);

        testInventar.fuegeGemischteGegenstaendeHinzu(eingabe);
        Map<Zutat, Integer> korrekteZutaten = new HashMap<>();
        korrekteZutaten.put(new Karotte(), 5);

        List<Gegenstand> korrekteGegenstaende = new ArrayList<>();
        korrekteGegenstaende.add(new Holzschwert());
        korrekteGegenstaende.add(new Holzschwert());

        assertEquals(korrekteZutaten, testInventar.getZutaten());
        assertEquals(korrekteGegenstaende, testInventar.getGegenstaende());
    }
}
