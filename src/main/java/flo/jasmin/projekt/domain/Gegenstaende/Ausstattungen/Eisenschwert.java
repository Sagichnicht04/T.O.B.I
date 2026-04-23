package flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen;

public class Eisenschwert extends Ausstattung {
    
    public Eisenschwert() {
        super("Eisenschwert",
              30,
              "Ein stabiles Schwert aus geschmiedetem Eisen. Scharf und zuverlaessig.",
              8,  
              BeinflussterWert.ANGRIFF);
    }
}