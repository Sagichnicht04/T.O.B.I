package flo.jasmin.projekt.domain.Values;

import java.util.Objects;

public class Erfahrung {
    private final int erfahrungsPunkte;

    private Erfahrung(int erfahrungsPunkte) {
        if (erfahrungsPunkte < 0) {
            throw new IllegalArgumentException("Erfahrung kann nicht negativ sein");
        }
        this.erfahrungsPunkte = erfahrungsPunkte;
    }

    public static Erfahrung von(int erfahrungsPunkte) {
        return new Erfahrung(erfahrungsPunkte);
    }

    public static Erfahrung keine() {
        return new Erfahrung(0);
    }

    public int getPunkte() {
        return erfahrungsPunkte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Erfahrung that = (Erfahrung) o;
        return erfahrungsPunkte == that.erfahrungsPunkte;
    }

    @Override
    public int hashCode() {
        return Objects.hash(erfahrungsPunkte);
    }

    @Override
    public String toString() {
        return erfahrungsPunkte + " XP";
    }
}