package main.java.flo.jasmin.projekt.domain.Gegenstaende;

public class Ausstattung extends Gegenstand{
    int wert;
    BeinflussterWert beinflussterWert;

    public Ausstattung(String name, int preis, String beschreibung, int wert, BeinflussterWert beinflussterWert) {
        super(name, preis, beschreibung);
        this.wert = wert;
        this.beinflussterWert = beinflussterWert;
    }




    public int getWert() {
        return wert;
    }

    public void setWert(int wert) {
        this.wert = wert;
    }

    public BeinflussterWert getBeinflussterWert() {
        return beinflussterWert;
    }

    public void setBeinflussterWert(BeinflussterWert beinflussterWert) {
        this.beinflussterWert = beinflussterWert;
    }




    enum BeinflussterWert{
        ANGRIFF,
        VERTEIDIGUNG
    }
}


