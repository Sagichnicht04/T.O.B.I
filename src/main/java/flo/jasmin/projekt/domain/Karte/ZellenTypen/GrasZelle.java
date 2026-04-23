package flo.jasmin.projekt.domain.Karte.ZellenTypen;

import flo.jasmin.projekt.domain.Akteure.Gegner;
import flo.jasmin.projekt.domain.Akteure.Goblin;
import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Karte.Zellentyp;

import java.util.*;

public class GrasZelle extends Zellentyp{
    public GrasZelle(){
        super(
    "Schoenes leckeres saftiges gruenes Gras (kein Brokkoli)",
                new HashSet<>(List.of(Befehl.CAMPEN)),
                new ArrayList<>(List.of(new Goblin()))
        );
    }
}
