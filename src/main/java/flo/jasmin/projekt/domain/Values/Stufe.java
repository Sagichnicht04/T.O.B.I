package flo.jasmin.projekt.domain.Values;

import java.util.Objects;

public class Stufe {
    private final int stufe;

    private Stufe(int stufe) {
        if (stufe < 1) {
            throw new IllegalArgumentException("Stufe muss mindestens 1 sein");
        }
        this.stufe = stufe;
    }

    public static Stufe von(int stufe) {
        return new Stufe(stufe);
    }

    public static Stufe anfangsstufe() {
        return new Stufe(1);
    }

    public Stufe erhoehen() {
        return new Stufe(this.stufe + 1);
    }

    public Stufe erhoehenUm(int stufen) {
        if (stufen < 0) {
            throw new IllegalArgumentException("Stufenerhoehung kann nicht negativ sein");
        }
        return new Stufe(this.stufe + stufen);
    }

    public int getStufe() {
        return stufe;
    }

    public int getMultiplikator() {
        return stufe;
    }

    public boolean istHoeherAls(Stufe andere) {
        return this.stufe > andere.stufe;
    }

    public boolean istNiedrigerAls(Stufe andere) {
        return this.stufe < andere.stufe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stufe stufe1 = (Stufe) o;
        return stufe == stufe1.stufe;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stufe);
    }

    @Override
    public String toString() {
        return "Level " + stufe;
    }
}