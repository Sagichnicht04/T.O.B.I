package flo.jasmin.projekt;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import flo.jasmin.projekt.domain.Inventar;
import flo.jasmin.projekt.domain.Exceptions.NichtGenugZutatenImInventar;
import flo.jasmin.projekt.domain.Gegenstaende.Zutaten.Zutat;
import flo.jasmin.projekt.domain.Gegenstaende.Zutaten.Banane;
import flo.jasmin.projekt.domain.Gegenstaende.Zutaten.Karotte;

public class ZutatenInventarTest {

    public Inventar testInventar = new Inventar();
    
    @BeforeEach
    void fuellInventar(){
        Map<Zutat, Integer> zutaten = new HashMap();
        zutaten.put(new Karotte(), 5);
        zutaten.put(new Banane(), 2);
        testInventar.setZutaten(zutaten);
    }

    @Test
    void zutatenImInvenatrVorhanden() throws NichtGenugZutatenImInventar{
        Map<Zutat, Integer> auswahl = new HashMap<>();
        auswahl.put(new Karotte(), 2);
        assertDoesNotThrow(() -> testInventar.checkGenugZutatenImImventar(auswahl));
    }

    @Test
    void werfeFehlerWennNichtGenugZutaten(){
        Map<Zutat, Integer> auswahl = new HashMap<>();
        auswahl.put(new Banane(), 15);
        assertThrows(NichtGenugZutatenImInventar.class, () -> testInventar.checkGenugZutatenImImventar(auswahl));
    }

    @Test
    void erfolgreichEntferneZutaten() throws NichtGenugZutatenImInventar{
        Map<Zutat, Integer> auswahl = new HashMap<>();
        auswahl.put(new Karotte(), 5);
        auswahl.put(new Banane(), 1);

        Map<Zutat, Integer> ergebnis = new HashMap<>();
        ergebnis.put(new Banane(), 1);
        testInventar.entferneZutaten(auswahl);
        assertEquals(ergebnis, testInventar.getZutaten());
    }

    @Test
    void fehlerWeilMehrEntfernenAlsVorhanden(){
        Map<Zutat, Integer> auswahl = new HashMap<>();
        auswahl.put(new Karotte(), 5);
        auswahl.put(new Banane(), 8);

        Map<Zutat, Integer> zutaten = new HashMap();
        zutaten.put(new Karotte(), 5);
        zutaten.put(new Banane(), 2);

        assertThrows(NichtGenugZutatenImInventar.class, () -> testInventar.entferneZutaten(auswahl));
    }

    @Test
    void beiFehlerKeineZutatenEntfernt(){
        Map<Zutat, Integer> auswahl = new HashMap<>();
        auswahl.put(new Karotte(), 5);
        auswahl.put(new Banane(), 8);

        Map<Zutat, Integer> zutaten = new HashMap();
        zutaten.put(new Karotte(), 5);
        zutaten.put(new Banane(), 2);

        try{
            testInventar.entferneZutaten(auswahl);
        } catch (NichtGenugZutatenImInventar n){
            //ist so vorgesehen. Wir wollen Fehler um sicherzugegen, dass bei dem fehler keine Zutaten hinzugefuegt wurden
        }

        assertEquals(zutaten, testInventar.getZutaten());
    }
}
