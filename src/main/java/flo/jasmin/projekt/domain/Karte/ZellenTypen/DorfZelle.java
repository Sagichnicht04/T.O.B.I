package flo.jasmin.projekt.domain.Karte.ZellenTypen;

import java.util.Set;

import flo.jasmin.projekt.domain.Befehl;

public class DorfZelle extends Zellentyp{

    public DorfZelle() {
        super("hier ist ein Kaff aeaeaeaeh Dorf.", Set.of(Befehl.KAUFEN), null);
    }
}
