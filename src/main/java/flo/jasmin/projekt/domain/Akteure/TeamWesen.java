package flo.jasmin.projekt.domain.Akteure;

import flo.jasmin.projekt.domain.Gegenstaende.Ausstattungen.Ausstattung;

public class TeamWesen extends Wesen{
    private Ausstattung ausgeruesteteWaffe;
    private Ausstattung ausgeruesteteRuestung;

    @Override
    public int getAngriff(){
        int bonus = ausgeruesteteWaffe != null ? ausgeruesteteWaffe.getWert() : 0;
        return super.getAngriff() + bonus;
    }

    @Override
    public int getVerteidigung(){
        int bonus = ausgeruesteteRuestung != null ? ausgeruesteteRuestung.getWert() : 0;
        return super.getVerteidigung() + bonus;
    }

    public TeamWesen(int gesundheit, int verteidigung, int angriff, int initiative, int erfahrung, String name) {
        super(gesundheit, verteidigung, angriff, initiative, erfahrung, name);
        this.ausgeruesteteWaffe = null;
        this.ausgeruesteteRuestung = null;
    }

    public void ruesteAus(Ausstattung ausstattung) {
        if (ausstattung == null) {
            return;
        }
        
        if (ausstattung.getBeinflussterWert() == Ausstattung.BeinflussterWert.ANGRIFF) {
            ausgeruesteteWaffe = ausstattung;
        } else if (ausstattung.getBeinflussterWert() == Ausstattung.BeinflussterWert.VERTEIDIGUNG) {
            ausgeruesteteRuestung = ausstattung;
        }
    }

    public Ausstattung entferneWaffe() {
        Ausstattung alte = ausgeruesteteWaffe;
        ausgeruesteteWaffe = null;
        return alte;
    }

    public Ausstattung entferneRuestung() {
        Ausstattung alte = ausgeruesteteRuestung;
        ausgeruesteteRuestung = null;
        return alte;
    }

    public Ausstattung getAusgeruesteteWaffe() {
        return ausgeruesteteWaffe;
    }

    public Ausstattung getAusgeruesteteRuestung() {
        return ausgeruesteteRuestung;
    }

    public String getAusruestungsStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(": ");
        sb.append("⚔️  ");
        sb.append(ausgeruesteteWaffe != null ? ausgeruesteteWaffe.getName() + " (+" + ausgeruesteteWaffe.getWert() + ")" : "Keine Waffe");
        sb.append(" | 🛡️  ");
        sb.append(ausgeruesteteRuestung != null ? ausgeruesteteRuestung.getName() + " (+" + ausgeruesteteRuestung.getWert() + ")" : "Keine Ruestung");
        return sb.toString();
    }
}
