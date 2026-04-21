package flo.jasmin.projekt.domain.Values;

import java.util.Objects;

public class Gesundheit {
    private final int aktuelleGesundheit;
    private final int maxGesundheit;

    private Gesundheit(int aktuelleGesundheit, int maxGesundheit) {
        if (maxGesundheit <= 0) {
            throw new IllegalArgumentException("Maximale Gesundheit muss größer als 0 sein");
        }
        if (aktuelleGesundheit < 0) {
            throw new IllegalArgumentException("Gesundheit kann nicht negativ sein");
        }
        this.aktuelleGesundheit = Math.min(aktuelleGesundheit, maxGesundheit);
        this.maxGesundheit = maxGesundheit;
    }

    public static Gesundheit von(int gesundheit) {
        return new Gesundheit(gesundheit, gesundheit);
    }

    public static Gesundheit von(int aktuelleGesundheit, int maxGesundheit) {
        return new Gesundheit(aktuelleGesundheit, maxGesundheit);
    }

    public Gesundheit nehmeSchaden(Schaden schaden) {
        int neueGesundheit = Math.max(0, this.aktuelleGesundheit - schaden.getWert());
        return new Gesundheit(neueGesundheit, this.maxGesundheit);
    }

    public Gesundheit heile(int heilung) {
        if (heilung < 0) {
            throw new IllegalArgumentException("Heilung kann nicht negativ sein");
        }
        int neueGesundheit = Math.min(this.maxGesundheit, this.aktuelleGesundheit + heilung);
        return new Gesundheit(neueGesundheit, this.maxGesundheit);
    }

    public Gesundheit vollständigHeilen() {
        return new Gesundheit(this.maxGesundheit, this.maxGesundheit);
    }

    public Gesundheit mitNeuerMaxGesundheit(int neueMaxGesundheit) {
        if (neueMaxGesundheit <= 0) {
            throw new IllegalArgumentException("Maximale Gesundheit muss größer als 0 sein");
        }
        return new Gesundheit(this.aktuelleGesundheit, neueMaxGesundheit);
    }

    public boolean istKampfFähig() {
        return aktuelleGesundheit > 0;
    }

    public boolean istVollständigGeheilt() {
        return aktuelleGesundheit == maxGesundheit;
    }

    public int getAktuelleGesundheit() {
        return aktuelleGesundheit;
    }

    public int getMaxGesundheit() {
        return maxGesundheit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gesundheit that = (Gesundheit) o;
        return aktuelleGesundheit == that.aktuelleGesundheit &&
               maxGesundheit == that.maxGesundheit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aktuelleGesundheit, maxGesundheit);
    }

    @Override
    public String toString() {
        return aktuelleGesundheit + "/" + maxGesundheit + " HP";
    }
}