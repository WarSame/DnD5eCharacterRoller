package com.example.graeme.dnd5echaracterroller;

//Enum for class string
public enum ClassStringEnum {
    BARBARIAN("Barbarian", new Integer[]{0, 2, 1, 5, 3, 4},R.drawable.initial_b ),
    BARD("Bard",new Integer[]{4, 2, 1, 5, 3, 0},R.drawable.initial_b),
    CLERIC("Cleric",new Integer[]{3, 2, 1, 5, 0, 4}, R.drawable.initial_c),
    DRUID("Druid",new Integer[]{5, 1, 2, 4, 0, 3},R.drawable.initial_d),
    FIGHTER("Fighter",new Integer[]{0, 3, 1, 4, 2, 5},R.drawable.initial_f),
    MONK("Monk",new Integer[]{3, 0, 2, 5, 1, 4},R.drawable.initial_m),
    PALADIN("Paladin",new Integer[]{0, 4, 2, 5, 3, 1},R.drawable.initial_p),
    RANGER("Ranger",new Integer[]{4, 0, 1, 3, 2, 5},R.drawable.initial_r),
    ROGUE("Rogue",new Integer[]{4, 0, 2, 5, 3, 1},R.drawable.initial_r),
    SORCERER("Sorcerer",new Integer[]{5, 1, 2, 3, 4, 0}, R.drawable.initial_s),
    WARLOCK("Warlock",new Integer[]{5, 1, 2, 4, 3, 0},R.drawable.initial_w),
    WIZARD("Wizard",new Integer[]{5, 1, 2, 0, 3, 4},R.drawable.initial_w);

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
