package flo.jasmin.projekt.domain.Akteure;

import flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen.Schale;
import flo.jasmin.projekt.domain.Visualisierung.GarneleVisualisierung;

import java.util.ArrayList;
import java.util.Comparator;

public class Garnele extends Gegner {
    public Garnele(){
        super(10, 10, 50, 1, 50, "Garnele", new GarneleVisualisierung());
        super.getInventar().add(new Schale());
    }

    //Goblin greift standardmaessig schwaechstes Wesen an
    @Override
    public Wesen ausgewaehltesZiel(ArrayList<Wesen> wesen) {
        wesen.sort(Comparator.comparingInt(Wesen::getGesundheit));
        return wesen.get(0);
    }
}
