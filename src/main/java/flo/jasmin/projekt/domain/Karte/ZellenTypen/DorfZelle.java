package flo.jasmin.projekt.domain.Karte.ZellenTypen;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Akteure.Gegner;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Karte.Zellentyp;

public class DorfZelle extends Zellentyp{

    public DorfZelle() {
        super("hier ist ein Kaff ääääh Dorf.", Set.of(Befehl.KAUFEN), null);
    }
}
