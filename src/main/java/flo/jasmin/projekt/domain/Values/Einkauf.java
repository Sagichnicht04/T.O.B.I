package flo.jasmin.projekt.domain.Values;

import java.util.Map;

import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;

public class Einkauf {
    Map<Gegenstand, Integer> auswahl;
    Geld gesamtpreis;
    
    public Einkauf(Map<Gegenstand, Integer> auswahl, int gesamtpreis) {
        this.auswahl = auswahl;
        this.gesamtpreis = Geld.von(gesamtpreis);
    }

    public Map<Gegenstand, Integer> getAuswahl() {
        return auswahl;
    }

    public void setAuswahl(Map<Gegenstand, Integer> auswahl) {
        this.auswahl = auswahl;
    }

    public int getGesamtpreis() {
        return gesamtpreis.getBetrag();
    }

    public void setGesamtpreis(int gesamtpreis) {
        this.gesamtpreis = Geld.von(gesamtpreis);
    } 

    
}
