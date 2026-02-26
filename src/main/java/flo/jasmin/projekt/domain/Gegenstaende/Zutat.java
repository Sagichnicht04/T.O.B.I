package flo.jasmin.projekt.domain.Gegenstaende;

public class Zutat extends Gegenstand{
    
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

    
    
}
