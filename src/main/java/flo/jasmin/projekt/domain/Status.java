package flo.jasmin.projekt.domain;

public enum Status {
    KAMPF,
    KOCHEN,
    INVENTAR,
    EXISTIEREN,
    DORF,
    EINKAUF,
    DIALOG,
    CAMPEN,
    AUSRÜSTEN
}

// status für generell:
/* 
existieren
    ->kampf
    ->Campen
        ->Kochen
    ->Dorf
        -> Einkauf
        

*/