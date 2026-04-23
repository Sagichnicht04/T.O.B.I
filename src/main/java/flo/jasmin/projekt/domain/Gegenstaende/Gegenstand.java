package flo.jasmin.projekt.domain.Gegenstaende;

import java.util.Objects;

public abstract class Gegenstand{
    
    private String name;
    private int preis;
    private String beschreibung;


    public Gegenstand(String name, int preis, String beschreibung) {
        this.name = name;
        this.preis = preis;
        this.beschreibung = beschreibung;
    }


    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPreis() {
        return preis;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }


    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(o instanceof Gegenstand){
            Gegenstand other = (Gegenstand) o;
            if (this.getName().equals(other.getName())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName());
    }
}