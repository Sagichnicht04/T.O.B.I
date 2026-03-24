package flo.jasmin.projekt.domain.Values;

public class Position {
    private final int horizontal;
    private final int vertikal;

    public Position(int horizontal, int vertikal) {
        this.horizontal = horizontal;
        this.vertikal = vertikal;
    }

    public int getHorizontal() { return horizontal; }
    public int getVertikal() { return vertikal; }

    public Position geheHoch() {
        return new Position(horizontal, vertikal - 1);
    }

    public Position geheRunter() {
        return new Position(horizontal, vertikal + 1);
    }

    public Position geheLinks() {
        return new Position(horizontal - 1, vertikal);
    }

    public Position geheRechts() {
        return new Position(horizontal + 1, vertikal);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Position position)) return false;
        return vertikal == position.vertikal && horizontal == position.horizontal;
    }

    @Override
    public int hashCode() {
        return 31 * vertikal + horizontal;
    }
}
