package com.example.graeme.dnd5echaracterroller;
//Enum for class string
public enum ClassStringEnum {
    BARBARIAN("Barbarian", new Integer[]{0, 2, 1, 5, 3, 4} ),BARD("Bard",new Integer[]{4, 2, 1, 5, 3, 0}),
    CLERIC("Cleric",new Integer[]{3, 2, 1, 5, 0, 4}),DRUID("Druid",new Integer[]{5, 1, 2, 4, 0, 3}),
    FIGHTER("Fighter",new Integer[]{0, 3, 1, 4, 2, 5}),MONK("Monk",new Integer[]{3, 0, 2, 5, 1, 4}),
    PALADIN("Paladin",new Integer[]{0, 4, 2, 5, 3, 1}),RANGER("Ranger",new Integer[]{4, 0, 1, 3, 2, 5}),
    ROGUE("Rogue",new Integer[]{4, 0, 2, 5, 3, 1}),SORCERER("Sorcerer",new Integer[]{5, 1, 2, 3, 4, 0}),
    WARLOCK("Warlock",new Integer[]{5, 1, 2, 4, 3, 0}), WIZARD("Wizard",new Integer[]{5, 1, 2, 0, 3, 4});

    private String className;
    private Integer[] statPriority;

    ClassStringEnum(String className, Integer[] statPriority){
        this.className = className;
        this.statPriority = statPriority;
    }

    public String getClassString(){
        return className;
    }

    public Integer[] getClassPriority(){
        return statPriority;
    }

    public static ClassStringEnum fromString(String stringIn) {
        for (ClassStringEnum fname : values()) {
            if (fname.className.equals(stringIn)) {
                return fname;
            }
        }
        return null;
    }

}
