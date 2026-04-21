package flo.jasmin.projekt.domain.Dörfer;

import java.util.Set;

import flo.jasmin.projekt.domain.Dorf;
import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteAusstattung.Holzschwert;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteAusstattung.Eisenschwert;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteAusstattung.Lederruestung;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Banane;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Karotte;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Tomate;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Fleisch;

public class Farore extends Dorf{

    public Farore() {
        super("Farore, ein wunderschönes kleines Dorf. Alles was man braucht... außer vllt ",
         Set.of(
             new Karotte(), 
             new Tomate(), 
             new Banane(),
             new Fleisch(),
             new Holzschwert(),
             new Eisenschwert(),
             new Lederruestung()
         ));
    }
    
}
