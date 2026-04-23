package flo.jasmin.projekt.domain.Akteure;

import java.util.Objects;

import flo.jasmin.projekt.domain.Values.Erfahrung;
import flo.jasmin.projekt.domain.Values.Gesundheit;
import flo.jasmin.projekt.domain.Values.Schaden;
import flo.jasmin.projekt.domain.Values.Stats;
import flo.jasmin.projekt.domain.Values.Stufe;

public class Wesen {
    private Gesundheit gesundheit;
    private Stats stats;
    private Erfahrung erfahrung;
    private Stufe stufe;
    private String name;

    public Wesen(int gesundheit, int verteidigung, int angriff, int initiative, int erfahrung, String name) {
        this.gesundheit = Gesundheit.von(gesundheit);
        this.stats = Stats.von(angriff, verteidigung, initiative);
        this.erfahrung = Erfahrung.von(erfahrung);
        this.stufe = Stufe.anfangsstufe();
        this.name = name;
    }


    public boolean kampfFaehig(){
        return gesundheit.istKampfFaehig();
    }

    public void nehmeSchaden(Schaden schaden){
        Schaden reduzierterSchaden = schaden.reduziereDurch(getVerteidigung());
        this.gesundheit = gesundheit.nehmeSchaden(reduzierterSchaden);
    }

    public int getAngriffsStaerke() {
        return stats.getAngriff() * stufe.getMultiplikator();
    }

    public Schaden berechneSchaden() {
        return Schaden.von(getAngriffsStaerke());
    }

    public void heile(int heilung){
        this.gesundheit = gesundheit.heile(heilung);
    }

    public int getGesundheit() {
        return gesundheit.getAktuelleGesundheit();
    }

    public void setGesundheit(int gesundheitWert) {
        this.gesundheit = Gesundheit.von(gesundheitWert, gesundheit.getMaxGesundheit());
    }

    public int getMaxGesundheit() {
        return gesundheit.getMaxGesundheit();
    }

    public int getVerteidigung() {
        return stats.getVerteidigung();
    }

    public void setVerteidigung(int verteidigung) {
        this.stats = Stats.von(stats.getAngriff(), verteidigung, stats.getInitiative());
    }

    public int getAngriff() {
        return stats.getAngriff();
    }

    public void setAngriff(int angriff) {
        this.stats = Stats.von(angriff, stats.getVerteidigung(), stats.getInitiative());
    }

    public int getInitiative() {
        return stats.getInitiative();
    }

    public void setInitiative(int initiative) {
        this.stats = Stats.von(stats.getAngriff(), stats.getVerteidigung(), initiative);
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public int getErfahrung() {
        return erfahrung.getPunkte();
    }

    public void setErfahrung(int erfahrungsPunkte) {
        this.erfahrung = Erfahrung.von(erfahrungsPunkte);
    }


    public int getStufe() {
        return stufe.getStufe();
    }

    public void setStufe(int stufeWert) {
        int alterMultiplikator = this.stufe.getMultiplikator();
        this.stufe = Stufe.von(stufeWert);
        int neuerMultiplikator = this.stufe.getMultiplikator();
        
        // Gesundheit mit neuer Stufe skalieren
        int neueMaxGesundheit = gesundheit.getMaxGesundheit() / alterMultiplikator * neuerMultiplikator;
        this.gesundheit = gesundheit.mitNeuerMaxGesundheit(neueMaxGesundheit);
        
        // Stats mit neuer Stufe skalieren
        this.stats = Stats.von(
            stats.getAngriff() / alterMultiplikator * neuerMultiplikator,
            stats.getVerteidigung() / alterMultiplikator * neuerMultiplikator,
            stats.getInitiative()
        );
        
        // Erfahrung mit neuer Stufe skalieren
        this.erfahrung = Erfahrung.von(erfahrung.getPunkte() / alterMultiplikator * neuerMultiplikator);
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
        //dadurch kann man sich das Pruefen in den Subklassen sparren
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