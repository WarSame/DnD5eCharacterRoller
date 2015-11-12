package com.example.graeme.dnd5echaracterroller;
//Enum for class string
public enum ClassStringEnum {
    BARBARIAN("Barbarian"),BARD("Bard"),CLERIC("Cleric"),DRUID("Druid"),
    FIGHTER("Fighter"),MONK("Monk"),PALADIN("Paladin"),RANGER("Ranger"),
    ROGUE("Rogue"),SORCERER("Sorcerer"), WARLOCK("Warlock"), WIZARD("Wizard");

    private String className;

    ClassStringEnum(String className){
        this.className = className;
    }

    public String getClassString(){
        return className;
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
