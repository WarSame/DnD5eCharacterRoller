package com.example.graeme.dnd5echaracterroller;

/**
 * Created by Graeme on 11/11/2015.
 */
public class GetImage {
    public static int SelectImage(String classString) {

        switch (classString){
            case "Barbarian":
                return R.drawable.barbarianicon50;
            case "Bard":
                return R.drawable.bardicon50;
            case "Cleric":
                return R.drawable.clericicon50;
            case "Druid":
                return R.drawable.druidicon50;
            case "Fighter":
                return R.drawable.fightericon50;
            case "Monk":
                return R.drawable.monkicon50;
            case "Paladin":
                return R.drawable.paladinicon50;
            case "Ranger":
                return R.drawable.rangericon50;
            case "Rogue":
                return R.drawable.rogueicon50;
            case "Sorcerer":
                return R.drawable.sorcerericon50;
            case "Warlock":
                return R.drawable.warlockicon50;
            case "Wizard":
                return R.drawable.wizardicon50;
        }
        return 0;
    }
}
