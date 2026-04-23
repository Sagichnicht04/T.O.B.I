package flo.jasmin.projekt.domain.Values;

import java.util.Objects;

public class Stats {
    private final int angriff;
    private final int verteidigung;
    private final int initiative;

    private Stats(int angriff, int verteidigung, int initiative) {
        if (angriff < 0 || verteidigung < 0 || initiative < 0) {
            throw new IllegalArgumentException("Stats koennen nicht negativ sein");
        }
        this.angriff = angriff;
        this.verteidigung = verteidigung;
        this.initiative = initiative;
    }

    public static Stats von(int angriff, int verteidigung, int initiative) {
        return new Stats(angriff, verteidigung, initiative);
    }

    public int getAngriff() {
        return angriff;
    }

    public int getVerteidigung() {
        return verteidigung;
    }

    public int getInitiative() {
        return initiative;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return angriff == stats.angriff &&
               verteidigung == stats.verteidigung &&
               initiative == stats.initiative;
    }

    @Override
    public int hashCode() {
        return Objects.hash(angriff, verteidigung, initiative);
    }

    @Override
    public String toString() {
        return String.format("Stats[ATK:%d, DEF:%d, INI:%d]", angriff, verteidigung, initiative);
    }
}