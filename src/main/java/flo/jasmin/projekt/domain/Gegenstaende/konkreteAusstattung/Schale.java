package flo.jasmin.projekt.domain.Gegenstaende.konkreteAusstattung;

import flo.jasmin.projekt.domain.Gegenstaende.Ausstattung;

public class Schale extends Ausstattung{
    public Schale() {
        super("Schale", 100, "Es gibt nichts härteres als die Schale einer Garnele.", 50,Ausstattung.BeinflussterWert.VERTEIDIGUNG);
    }
}
