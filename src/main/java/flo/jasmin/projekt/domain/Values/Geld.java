package flo.jasmin.projekt.domain.Values;

import java.util.Objects;

public class Geld {
    private final int betrag;

    private Geld(int betrag) {
        if (betrag < 0) {
            throw new IllegalArgumentException("Geld kann nicht negativ sein");
        }
        this.betrag = betrag;
    }

    public static Geld von(int betrag) {
        return new Geld(betrag);
    }

    public static Geld nichts() {
        return new Geld(0);
    }

    public Geld addiere(Geld anderes) {
        return new Geld(this.betrag + anderes.betrag);
    }

    public Geld subtrahiere(Geld anderes) {
        if (this.betrag < anderes.betrag) {
            throw new IllegalArgumentException("Nicht genug Geld vorhanden");
        }
        return new Geld(this.betrag - anderes.betrag);
    }

    public boolean istMindestens(Geld mindestBetrag) {
        return this.betrag >= mindestBetrag.betrag;
    }

    public boolean kannBezahlen(Geld preis) {
        return istMindestens(preis);
    }

    public int getBetrag() {
        return betrag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geld geld = (Geld) o;
        return betrag == geld.betrag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(betrag);
    }

    @Override
    public String toString() {
        return betrag + " Münzen";
    }
}