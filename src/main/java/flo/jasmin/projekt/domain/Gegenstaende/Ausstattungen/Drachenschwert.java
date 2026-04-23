package flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen;


public class Drachenschwert extends Ausstattung {
    
    public Drachenschwert() {
        super("Drachenschwert",
              100,
              "Ein legendaeres Schwert, geschmiedet aus Drachenschuppen. Glueht rot im Kampf.",
              15,  
              BeinflussterWert.ANGRIFF);
    }
}