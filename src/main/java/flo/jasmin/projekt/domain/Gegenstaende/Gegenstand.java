package main.java.flo.jasmin.projekt.domain.Gegenstaende;
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
    public void setPreis(int preis) {
        this.preis = preis;
    }
    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }



}