package flo.jasmin.projekt.domain.Dörfer;

import java.util.Set;

import flo.jasmin.projekt.domain.Dorf;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteAusstattung.Holzschwert;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Banane;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Karotte;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Tomate;

public class Farore extends Dorf{

    public Farore() {
        super("Farore, ein wunderschönes kleines Dorf. Alles was man braucht... außer vllt ", Set.of(new Karotte(), new Tomate(), new Holzschwert()));
    }
    
}
