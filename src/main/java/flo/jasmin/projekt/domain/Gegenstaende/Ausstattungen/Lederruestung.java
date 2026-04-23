package flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen;

public class Lederruestung extends Ausstattung {
    
    public Lederruestung() {
        super("Lederruestung",
              20,
              "Eine flexible Ruestung aus gehaertetem Leder. Schuetzt ohne zu verlangsamen.",
              5,  
              BeinflussterWert.VERTEIDIGUNG);
    }
}