package flo.jasmin.projekt.domain.Akteure;

import flo.jasmin.projekt.domain.Gegenstaende.Ausstattung;

public class TeamWesen extends Wesen{
    private Ausstattung ausgerüsteteWaffe;
    private Ausstattung ausgerüsteteRüstung;

    @Override
    public int getAngriff(){
        int bonus = ausgerüsteteWaffe != null ? ausgerüsteteWaffe.getWert() : 0;
        return super.getAngriff() + bonus;
    }

    @Override
    public int getVerteidigung(){
        int bonus = ausgerüsteteRüstung != null ? ausgerüsteteRüstung.getWert() : 0;
        return super.getVerteidigung() + bonus;
    }

    public TeamWesen(int gesundheit, int verteidigung, int angriff, int initiative, int erfahrung, String name) {
        super(gesundheit, verteidigung, angriff, initiative, erfahrung, name);
        this.ausgerüsteteWaffe = null;
        this.ausgerüsteteRüstung = null;
    }

    public void rüsteAus(Ausstattung ausstattung) {
        if (ausstattung == null) {
            return;
        }
        
        if (ausstattung.getBeinflussterWert() == Ausstattung.BeinflussterWert.ANGRIFF) {
            ausgerüsteteWaffe = ausstattung;
        } else if (ausstattung.getBeinflussterWert() == Ausstattung.BeinflussterWert.VERTEIDIGUNG) {
            ausgerüsteteRüstung = ausstattung;
        }
    }

    public Ausstattung entferneWaffe() {
        Ausstattung alte = ausgerüsteteWaffe;
        ausgerüsteteWaffe = null;
        return alte;
    }

    public Ausstattung entferneRüstung() {
        Ausstattung alte = ausgerüsteteRüstung;
        ausgerüsteteRüstung = null;
        return alte;
    }

    public Ausstattung getAusgerüsteteWaffe() {
        return ausgerüsteteWaffe;
    }

    public Ausstattung getAusgerüsteteRüstung() {
        return ausgerüsteteRüstung;
    }

    public String getAusrüstungsStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(": ");
        sb.append("⚔️  ");
        sb.append(ausgerüsteteWaffe != null ? ausgerüsteteWaffe.getName() + " (+" + ausgerüsteteWaffe.getWert() + ")" : "Keine Waffe");
        sb.append(" | 🛡️  ");
        sb.append(ausgerüsteteRüstung != null ? ausgerüsteteRüstung.getName() + " (+" + ausgerüsteteRüstung.getWert() + ")" : "Keine Rüstung");
        return sb.toString();
    }
}
