package flo.jasmin.projekt.domain.Values;

import java.util.Objects;

public class Schaden {
    private final int wert;

    private Schaden(int wert) {
        if (wert < 0) {
            throw new IllegalArgumentException("Schaden kann nicht negativ sein");
        }
        this.wert = wert;
    }

    public static Schaden von(int wert) {
        return new Schaden(wert);
    }

    public Schaden reduziereDurch(int verteidigung) {
        int reduzierterSchaden = Math.max(0, this.wert - verteidigung);
        return new Schaden(reduzierterSchaden);
    }

    public int getWert() {
        return wert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schaden schaden = (Schaden) o;
        return wert == schaden.wert;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wert);
    }

    @Override
    public String toString() {
        return wert + " Schaden";
    }
}