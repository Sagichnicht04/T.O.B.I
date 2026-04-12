package flo.jasmin.projekt.domain.Gegenstaende;

import java.util.Objects;

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




    public enum BeinflussterWert{
        ANGRIFF,
        VERTEIDIGUNG
    }

        @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o instanceof Ausstattung){
            Ausstattung other = (Ausstattung) o;
            if (this.getName().equals(other.getName())){
                return true;
            }
        }
        return false;
    }
}


