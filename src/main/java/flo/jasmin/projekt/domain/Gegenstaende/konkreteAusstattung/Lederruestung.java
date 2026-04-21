package flo.jasmin.projekt.domain.Gegenstaende.konkreteAusstattung;

import flo.jasmin.projekt.domain.Gegenstaende.Ausstattung;

public class Lederruestung extends Ausstattung {
    
    public Lederruestung() {
        super("Lederrüstung",
              20,
              "Eine flexible Rüstung aus gehärtetem Leder. Schützt ohne zu verlangsamen.",
              5,  
              BeinflussterWert.VERTEIDIGUNG);
    }
}