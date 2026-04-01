package flo.jasmin.projekt.domain.Karte;

import java.util.*;

import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Akteure.Gegner;

public class Zellentyp {

    String beschreibung;
    Set<Befehl> erlaubteBefehle = new HashSet<>(List.of(Befehl.LINKS, Befehl.HOCH, Befehl.RECHTS, Befehl.RUNTER, Befehl.HILFE));
    ArrayList<Gegner> gegnerAuswahl;


    public Zellentyp(String beschreibung, Set<Befehl> erlaubteBefehle, ArrayList<Gegner> gegnerAuswahl) {
        this.beschreibung = beschreibung;
        this.erlaubteBefehle.addAll(erlaubteBefehle);
        this.gegnerAuswahl = gegnerAuswahl;
    }


    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    public Set<Befehl> getErlaubteBefehle() {
        return erlaubteBefehle;
    }
    public void setErlaubteBefehle(Set<Befehl> erlaubteBefehle) {
        this.erlaubteBefehle = erlaubteBefehle;
    }

    public ArrayList<Gegner> getGegnerAuswahl() {
        return gegnerAuswahl;
    }


    public void setGegnerAuswahl(ArrayList<Gegner> gegnerAuswahl) {
        this.gegnerAuswahl = gegnerAuswahl;
    }


    
}
