package flo.jasmin.projekt.domain.Karte.ZellenTypen;

import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Karte.Zellentyp;
import flo.jasmin.projekt.domain.Akteure.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class WaldZelle extends Zellentyp {
    
    public WaldZelle() {
        super("Du befindest dich in einem dichten Wald. Die Baeume stehen dicht beieinander und es riecht nach Moos.",
              erstelleErlaubteBefehle(),
              erstelleGegnerAuswahl());
    }
    
    private static Set<Befehl> erstelleErlaubteBefehle() {
        Set<Befehl> befehle = new HashSet<>();
        befehle.add(Befehl.CAMPEN);
        return befehle;
    }
    
    private static ArrayList<Gegner> erstelleGegnerAuswahl() {
        ArrayList<Gegner> gegner = new ArrayList<>();
        gegner.add(new Spinne());
        gegner.add(new Goblin());
        return gegner;
    }
}