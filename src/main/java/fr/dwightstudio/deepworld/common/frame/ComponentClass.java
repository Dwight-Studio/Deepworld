package fr.dwightstudio.deepworld.common.frame;

public enum ComponentClass {

    // Classes
    PRIMARY(0),
    SECONDARY(1),
    TERTIARY(2),
    QUATERNARY(3),
    QUINARY(4),
    SENARY(5),
    SEPTENARY(6),
    OCTONARY(7);

    // Vars
    private int index;
    private int number;

    // Constructor
    ComponentClass(int index) {
        this.index = index;
        this.number = index + 1;
    }


    public int getIndex() {
        return index;
    }

    public int getNumber() {
        return number;
    }
}
