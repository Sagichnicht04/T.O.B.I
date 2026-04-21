package flo.jasmin.projekt.domain.Gegenstaende.konkreteAusstattung;

import flo.jasmin.projekt.domain.Gegenstaende.Ausstattung;

public class Eisenschwert extends Ausstattung {
    
    public Eisenschwert() {
        super("Eisenschwert",
              30,
              "Ein stabiles Schwert aus geschmiedetem Eisen. Scharf und zuverlässig.",
              8,  
              BeinflussterWert.ANGRIFF);
    }
}