package flo.jasmin.projekt.domain.Akteure;

import flo.jasmin.projekt.domain.Inventar;
import flo.jasmin.projekt.domain.Visualisierung.AsciiVisualisierung;

import java.util.ArrayList;

public class Gegner extends Wesen{
    private AsciiVisualisierung asciiVisualisierung;
    private Inventar inventar;

    public Gegner(int gesundheit, int verteidigung, int angriff, int initiative, int erfahrung, String name, AsciiVisualisierung asciiVisualisierung){
        super(gesundheit, verteidigung, angriff, initiative, erfahrung, name);
        setAsciiVisualisierung(asciiVisualisierung);
        inventar = new Inventar();
    }


    public Inventar getInventar() {
        return inventar;
    }

    public void setInventar(Inventar inventar) {
        this.inventar = inventar;
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
    
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o instanceof Gegner){
            Gegner other = (Gegner) o;
            if (this.getName().equals(other.getName())){
                return true;
            }
        }
        return false;
    }
}