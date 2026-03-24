package flo.jasmin.projekt.domain.Karte;

import flo.jasmin.projekt.domain.Akteure.Gegner;
import flo.jasmin.projekt.domain.Akteure.Goblin;
import flo.jasmin.projekt.domain.Befehl;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;

import java.util.*;

public class GrasZelle extends Zellentyp{
    public GrasZelle(){
        super(
    "Schönes leckeres saftiges grünes Gras (kein Brokkoli)",
                new HashSet<>(List.of(Befehl.CAMPEN)),
                new ArrayList<>(List.of(new Goblin()))
        );
    }
}
