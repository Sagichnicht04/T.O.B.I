package flo.jasmin.projekt.domain;

public enum Befehl {
    HOCH,
    RUNTER,
    LINKS,
    RECHTS,
    KOCHEN,
    ZUTATEN, //Wenn im Kochmenü, soll Zutaten verwenden
    REDEN,
    ANGRIFF,
    KAUFEN, //Um im Dorf das Sortiment anzuzeigen ABER wenn der Spieler beim Kaufen ist, dann um die Items zu kaufen
    CAMPEN,
    SPEICHERN,
    HILFE,
    KREATURAUSSTATTEN,
    ZURÜCK,
    JA
}