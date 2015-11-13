package com.example.graeme.dnd5echaracterroller;

//Enum for class string
public enum ClassStringEnum {
    BARBARIAN("Barbarian", new Integer[]{0, 2, 1, 5, 3, 4},R.drawable.barbarianicon50 ),
    BARD("Bard",new Integer[]{4, 2, 1, 5, 3, 0},R.drawable.bardicon50),
    CLERIC("Cleric",new Integer[]{3, 2, 1, 5, 0, 4}, R.drawable.clericicon50),
    DRUID("Druid",new Integer[]{5, 1, 2, 4, 0, 3},R.drawable.druidicon50),
    FIGHTER("Fighter",new Integer[]{0, 3, 1, 4, 2, 5},R.drawable.fightericon50),
    MONK("Monk",new Integer[]{3, 0, 2, 5, 1, 4},R.drawable.monkicon50),
    PALADIN("Paladin",new Integer[]{0, 4, 2, 5, 3, 1},R.drawable.paladinicon50),
    RANGER("Ranger",new Integer[]{4, 0, 1, 3, 2, 5},R.drawable.rangericon50),
    ROGUE("Rogue",new Integer[]{4, 0, 2, 5, 3, 1},R.drawable.rogueicon50),
    SORCERER("Sorcerer",new Integer[]{5, 1, 2, 3, 4, 0}, R.drawable.sorcerericon50),
    WARLOCK("Warlock",new Integer[]{5, 1, 2, 4, 3, 0},R.drawable.warlockicon50),
    WIZARD("Wizard",new Integer[]{5, 1, 2, 0, 3, 4},R.drawable.wizardicon50);

    private String className;
    private Integer[] statPriority;
    private int classIcon;


    ClassStringEnum(String className, Integer[] statPriority, int classIcon){
        this.className = className;
        this.statPriority = statPriority;
        this.classIcon = classIcon;
    }

    public String getClassString(){
        return className;
    }

    public Integer[] getClassPriority(){
        return statPriority;
    }

    public int getClassIcon(){
        return classIcon;
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
