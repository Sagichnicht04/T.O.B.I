package flo.jasmin.projekt.domain.Gegenstaende.konkreteAusstattung;

import flo.jasmin.projekt.domain.Gegenstaende.Ausstattung;


public class Axt extends Ausstattung {
    
    public Axt() {
        super("Axt",
              25,
              "Eine schwere Axt. Langsam aber kraftvoll.",
              10,  
              BeinflussterWert.ANGRIFF);
    }
}