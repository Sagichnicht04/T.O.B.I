package flo.jasmin.projekt.domain.Akteure;

import flo.jasmin.projekt.domain.Gegenstaende.Zutat;
import flo.jasmin.projekt.domain.Gegenstaende.konkreteZutaten.Banane;
import flo.jasmin.projekt.domain.Visualisierung.GoblinVisualisierung;

import java.util.ArrayList;
import java.util.Comparator;

public class Goblin extends Gegner {
    public Goblin(){
        super(10, 5, 5, 3, 2, "Goblin", new GoblinVisualisierung());
        super.getInventar().fügeGegenstandHinzu(new Banane());
    }

    //Goblin greift standardmäßig schwächstes Wesen an
    @Override
    public Wesen ausgewähltesZiel(ArrayList<Wesen> wesen) {
        wesen.sort(Comparator.comparingInt(Wesen::getGesundheit));
        return wesen.get(0);
    }
}
