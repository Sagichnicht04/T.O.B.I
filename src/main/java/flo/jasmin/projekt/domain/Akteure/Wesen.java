package flo.jasmin.projekt.domain.Akteure;

import java.util.Objects;

import flo.jasmin.projekt.domain.Gegenstaende.Zutat;

public class Wesen {
    private int gesundheit;
    private int verteidigung;
    private int angriff;
    private int initiative;
    private int erfahrung;
    private int stufe = 1;
    private String name;

    public Wesen(int gesundheit, int verteidigung, int angriff, int initiative, int erfahrung, String name) {
        this.gesundheit = gesundheit;
        this.verteidigung = verteidigung;
        this.angriff = angriff;
        this.initiative = initiative;
        this.erfahrung = erfahrung;
        this.name = name;
    }


    public boolean kampfFähig(){
        return gesundheit>0;
    }

    public void nehmeSchaden(int lebensPunkte){
        if(gesundheit-lebensPunkte>0){
            gesundheit -= lebensPunkte;
        }
        else{
            gesundheit = 0;
        }
    }

    public int getAngriffsStärke() {
        return angriff*stufe;
    }


    public int getGesundheit() {
        return gesundheit;
    }

    public void setGesundheit(int gesundheit) {
        this.gesundheit = gesundheit;
    }

    public int getVerteidigung() {
        return verteidigung;
    }

    public void setVerteidigung(int verteidigung) {
        this.verteidigung = verteidigung;
    }

    public int getAngriff() {
        return angriff;
    }

    public void setAngriff(int angriff) {
        this.angriff = angriff;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getErfahrung() {
        return erfahrung;
    }

    public void setErfahrung(int erfahrung) {
        this.erfahrung = erfahrung;
    }

    public int getStufe() {
        return stufe;
    }

    public void setStufe(int stufe) {
        this.stufe = stufe;
        gesundheit = gesundheit * stufe;
        verteidigung = verteidigung * stufe;
        angriff = angriff * stufe;
        erfahrung = erfahrung * stufe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        //dadurch kann man sich das Prüfen in den Subklassen sparren
        if(o.getClass() == this.getClass()){
            Wesen other = (Wesen) o;
            if (this.getName().equals(other.getName())
                && this.getInitiative() == other.getInitiative()
                && this.getAngriff() == other.getAngriff()
                && this.getVerteidigung() == other.getVerteidigung()
                && this.getStufe() == other.getStufe()
                && this.getGesundheit() == other.getGesundheit()
            ){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getVerteidigung(), getAngriff(), getInitiative(), getGesundheit());
    }
}