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


    public boolean kampfFähig(){
        return gesundheit.istKampfFähig();
    }

    public void nehmeSchaden(Schaden schaden){
        Schaden reduzierterSchaden = schaden.reduziereDurch(getVerteidigung());
        this.gesundheit = gesundheit.nehmeSchaden(reduzierterSchaden);
    }

    public int getAngriffsStärke() {
        return stats.getAngriff() * stufe.getMultiplikator();
    }

    public Schaden berechneSchaden() {
        return Schaden.von(getAngriffsStärke());
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

    public void setMaxGesundheit(int maxGesundheit) {
        this.gesundheit = gesundheit.mitNeuerMaxGesundheit(maxGesundheit);
    }

    public Gesundheit getGesundheitObjekt() {
        return gesundheit;
    }

    public void setGesundheitObjekt(Gesundheit gesundheit) {
        this.gesundheit = gesundheit;
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

    public Erfahrung getErfahrungObjekt() {
        return erfahrung;
    }

    public void setErfahrungObjekt(Erfahrung erfahrung) {
        this.erfahrung = erfahrung;
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

    public Stufe getStufeObjekt() {
        return stufe;
    }

    public void setStufeObjekt(Stufe stufe) {
        setStufe(stufe.getStufe());
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