package flo.jasmin.projekt.domain.Karte.ZellenTypen;

import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Karte.Zellentyp;
import flo.jasmin.projekt.domain.Akteure.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class FriedhofZelle extends Zellentyp {
    
    public FriedhofZelle() {
        super("Du befindest dich auf einem verlassenen Friedhof. Alte Grabsteine ragen aus dem Boden und Nebel wabert zwischen den Graebern.",
              erstelleErlaubteBefehle(),
              erstelleGegnerAuswahl());
    }
    
    private static Set<Befehl> erstelleErlaubteBefehle() {
        Set<Befehl> befehle = new HashSet<>();
        return befehle;
    }
    
    private static ArrayList<Gegner> erstelleGegnerAuswahl() {
        ArrayList<Gegner> gegner = new ArrayList<>();
        gegner.add(new Skelett());
        gegner.add(new Skelett());
        gegner.add(new Garnele());
        return gegner;
    }
}