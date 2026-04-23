package flo.jasmin.projekt.domain.Gegenstaende.konkreteAusstattung;

import flo.jasmin.projekt.domain.Gegenstaende.Ausstattung;


public class Drachenschwert extends Ausstattung {
    
    public Drachenschwert() {
        super("Drachenschwert",
              100,
              "Ein legendaeres Schwert, geschmiedet aus Drachenschuppen. Glueht rot im Kampf.",
              15,  
              BeinflussterWert.ANGRIFF);
    }
}