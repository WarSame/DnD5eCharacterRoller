package com.example.graeme.dnd5echaracterroller;

public enum RollStringEnum {
    FOUR_D6_DROP_WORST("4d6 Drop Worst Roll"),THREE_D6("3d6"),THREE_D6_AS_ROLLED("3d6 Assign As Rolled"),
    STANDARD_ARRAY("Standard Array");

    private String rollName;

    RollStringEnum(String rollName){
        this.rollName = rollName;
    }

    public String getRollString(){
        return rollName;
    }

    public static RollStringEnum fromString(String stringIn) {
        for (RollStringEnum fname : values()) {
            if (fname.rollName.equals(stringIn)) {
                return fname;
            }
        }
        return null;
    }

}
