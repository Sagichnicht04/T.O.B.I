package flo.jasmin.projekt.domain.Akteure;

import flo.jasmin.projekt.domain.Gegenstaende.Ausstattung;
import flo.jasmin.projekt.domain.Gegenstaende.Zutat;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteAusstattung.Schale;
import flo.jasmin.projekt.domain.Visualisierung.GarneleVisualisierung;
import flo.jasmin.projekt.domain.Visualisierung.GoblinVisualisierung;

import java.util.ArrayList;
import java.util.Comparator;

public class Garnele extends Gegner {
    public Garnele(){
        super(10, 10, 50, 1, 50, "Garnele", new GarneleVisualisierung());
        super.getInventar().add(new Schale());
    }

    //Goblin greift standardmäßig schwächstes Wesen an
    @Override
    public Wesen ausgewähltesZiel(ArrayList<Wesen> wesen) {
        wesen.sort(Comparator.comparingInt(Wesen::getGesundheit));
        return wesen.get(0);
    }
}
