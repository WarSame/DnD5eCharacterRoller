package com.example.graeme.dnd5echaracterroller;

public enum RollStringEnum {
    FOURD6DROPWORST("4d6 Drop Worst Roll"),THREED6("3d6"),THREED6ASROLLED("3d6 Assign As Rolled");

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
