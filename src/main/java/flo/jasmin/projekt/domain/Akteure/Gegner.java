package flo.jasmin.projekt.domain.Akteure;

import flo.jasmin.projekt.domain.Visualisierung.AsciiVisualisierung;

import java.util.ArrayList;

public class Gegner extends Wesen{
    private AsciiVisualisierung asciiVisualisierung;

    public Gegner(int gesundheit, int verteidigung, int angriff, int initiative, int erfahrung, String name, AsciiVisualisierung asciiVisualisierung){
        super(gesundheit, verteidigung, angriff, initiative, erfahrung, name);
        setAsciiVisualisierung(asciiVisualisierung);
    }


    public AsciiVisualisierung getAsciiVisualisierung() {
        return asciiVisualisierung;
    }

    public void setAsciiVisualisierung(AsciiVisualisierung asciiVisualisierung) {
        this.asciiVisualisierung = asciiVisualisierung;
    }

    //Im Kampf
    public Wesen ausgewähltesZiel(ArrayList<Wesen> wesen){
        return wesen.get(0);
    }
}