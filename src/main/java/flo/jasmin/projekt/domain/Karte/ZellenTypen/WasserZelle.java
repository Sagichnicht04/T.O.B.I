package flo.jasmin.projekt.domain.Karte.ZellenTypen;

import flo.jasmin.projekt.domain.Akteure.Garnele;
import flo.jasmin.projekt.domain.Karte.Zellentyp;
import flo.jasmin.projekt.domain.Befehl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class WasserZelle extends Zellentyp{
    public WasserZelle(){
        super(
    "Ganz schön nass",
                new HashSet<>(List.of()),
                new ArrayList<>(List.of(new Garnele()))
        );
    }
}
