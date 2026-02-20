package flo.jasmin.projekt.domain.Karte;

import java.util.ArrayList;
import java.util.Set;
import flo.jasmin.projekt.Befehle;
import flo.jasmin.projekt.domain.Akteure.Gegner;

public class Zellentyp {

    String beschreibung;
    Set<Befehle> erlaubteBefehle;
    ArrayList<Gegner> gegnerAuswahl;


    public Zellentyp(String beschreibung, Set<Befehle> erlaubteBefehle, ArrayList<Gegner> gegnerAuswahl) {
        this.beschreibung = beschreibung;
        this.erlaubteBefehle = erlaubteBefehle;
        this.gegnerAuswahl = gegnerAuswahl;
    }


    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    public Set<Befehle> getErlaubteBefehle() {
        return erlaubteBefehle;
    }
    public void setErlaubteBefehle(Set<Befehle> erlaubteBefehle) {
        this.erlaubteBefehle = erlaubteBefehle;
    }

    public ArrayList<Gegner> getGegnerAuswahl() {
        return gegnerAuswahl;
    }


    public void setGegnerAuswahl(ArrayList<Gegner> gegnerAuswahl) {
        this.gegnerAuswahl = gegnerAuswahl;
    }


    
}
