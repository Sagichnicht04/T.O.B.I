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
    AUSRUeSTEN
}

// status fuer generell:
/* 
existieren
    ->kampf
    ->Campen
        ->Kochen
    ->Dorf
        -> Einkauf
        

*/