package flo.jasmin.projekt.domain.Doerfer;

import java.util.Set;

import flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen.Holzschwert;
import flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen.Eisenschwert;
import flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen.Lederruestung;
import flo.jasmin.projekt.domain.Gegenstaende.Zutaten.Banane;
import flo.jasmin.projekt.domain.Gegenstaende.Zutaten.Karotte;
import flo.jasmin.projekt.domain.Gegenstaende.Zutaten.Tomate;
import flo.jasmin.projekt.domain.Gegenstaende.Zutaten.Fleisch;

public class Farore extends Dorf{

    public Farore() {
        super("Farore, ein wunderschoenes kleines Dorf. Alles was man braucht... ausser vllt Solarpanelen.",
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
