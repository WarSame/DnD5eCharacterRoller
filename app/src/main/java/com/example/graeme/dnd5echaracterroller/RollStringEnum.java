package com.example.graeme.dnd5echaracterroller;

public enum RollStringEnum {
    FOUR_D6_DROP_WORST("4d6 Drop Worst Roll",R.drawable.fourd6b350),
    THREE_D6("3d6",R.drawable.threed6unassigned50),
    THREE_D6_AS_ROLLED("3d6 Assign As Rolled",R.drawable.threed6assigned50),
    STANDARD_ARRAY("Standard Array",R.drawable.standardarray50);

    private String rollName;
    private int rollIcon;

    RollStringEnum(String rollName, int rollIcon){
        this.rollName = rollName;
        this.rollIcon = rollIcon;
    }

    public String getRollString(){
        return rollName;
    }

    public int getRollIcon(){
        return rollIcon;
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
