package flo.jasmin.projekt.domain.Gegenstaende.Zutaten;

import flo.jasmin.projekt.domain.Gegenstaende.Gegenstand;

public class Zutat extends Gegenstand {
    
    private int heilwert;

    public Zutat(String name, int preis, String beschreibung, int heilwert) {
        super(name, preis, beschreibung);
        this.heilwert = heilwert;
    }

    public int getHeilwert() {
        return heilwert;
    }

    public void setHeilwert(int heilwert) {
        this.heilwert = heilwert;
    }


// werden gebraucht, damit sie im Kochsystem gestacked werden
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o instanceof Zutat){
            Zutat other = (Zutat) o;
            if (this.getName().equals(other.getName())){
                return true;
            }
        }
        return false;
    }
}
