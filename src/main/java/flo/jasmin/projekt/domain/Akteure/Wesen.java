package flo.jasmin.projekt.domain.Akteure;

public class Wesen {
    private int gesundheit;
    private int verteidigung;
    private int angriff;
    private int initiative;
    private int erfahrung;
    private int stufe;
    private String name;


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
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}