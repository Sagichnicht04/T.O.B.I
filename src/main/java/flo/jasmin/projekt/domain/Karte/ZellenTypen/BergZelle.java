package flo.jasmin.projekt.domain.Karte.ZellenTypen;

import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Karte.Zellentyp;
import flo.jasmin.projekt.domain.Akteure.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BergZelle extends Zellentyp {
    
    public BergZelle() {
        super("Du stehst in einem felsigen Berggebiet. Der Wind pfeift durch die Schluchten und Geroell liegt ueberall.",
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
        gegner.add(new Ork());
        gegner.add(new Goblin());
        gegner.add(new Drache());
        return gegner;
    }
}