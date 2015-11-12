package com.example.graeme.dnd5echaracterroller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RollStats {
    public static int[] determineStats(RollStringEnum rollString, ClassStringEnum classString){
        ArrayList<Integer> rollList = new ArrayList<>();
        ArrayList<Integer> statList = new ArrayList<>();
        int[] finalStats = new int[6];

        if (rollString!=null){
            switch (rollString){
                case FOUR_D6_DROP_WORST:
                    finalStats = fourD6DropWorst(rollList,statList,classString);
                    break;
                case THREE_D6:
                    finalStats = threeD6(rollList, statList, classString);
                    break;
                case THREE_D6_AS_ROLLED:
                    finalStats=threeD6AsRolled(rollList,statList,classString);
                    break;
                case STANDARD_ARRAY:
                    finalStats=standardArray(statList, classString);
                    break;
            }
        }
        else {
            //In case of a null rollString, show the user the error by returning all 0s as stats
            for (int i = 0; i<statList.size();i++){
                finalStats[i] = 0;
            }
        }
        return finalStats;
    }

    private static int[] standardArray(ArrayList<Integer> statList, ClassStringEnum classString) {
        int[] standardArray = new int[]{15,14,13,12,10,8};
        //Assign the standard stats to the stat list
        for (int i =0;i<standardArray.length;i++){
            statList.add(standardArray[i]);
        }
        //Assort the stats upon class preference
        return assignStats(statList, classString);
    }

    private static int[] threeD6AsRolled(ArrayList<Integer> rollList, ArrayList<Integer> statList, ClassStringEnum classString) {
        //For 3d6, assign as rolled in order
        int[] finalStats = new int[6];
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 3; i++) {
                rollList.add(rollD6());
            }
            //Add the sum of the rolls to the stat list
            statList.add(sum(rollList));
            rollList = new ArrayList<>();
        }
        //Do not assort the stats - assign them in the order they are rolled
        for (int i = 0; i<statList.size();i++){
            finalStats[i] = statList.get(i);
        }
        return finalStats;
    }

    private static int[] threeD6(ArrayList<Integer> rollList, ArrayList<Integer> statList, ClassStringEnum classString) {
        //For 3d6 assign
        for (int j=0;j<6;j++) {
            for (int i = 0; i < 3; i++) {
                rollList.add(rollD6());
            }
            //Add the sum of the rolls to the stat list
            statList.add(sum(rollList));
            rollList=new ArrayList<>();
        }
        //Assort the stats upon class preference
        return assignStats(statList, classString);
    }

    private static int[] fourD6DropWorst(ArrayList<Integer> rollList, ArrayList<Integer> statList, ClassStringEnum classString){
        //For 4d6 drop one
        //Roll 4 dice for each stat
        for (int j=0;j<6;j++) {
            for (int i = 0; i < 4; i++) {
                rollList.add(rollD6());
            }
            //Remove the lowest roll and sum the rest, then add it to the list of stats
            rollList.remove(Collections.min(rollList));
            statList.add(sum(rollList));
            rollList=new ArrayList<>();
        }
        //Assort the stats based upon class preference
        return assignStats(statList, classString);
    }

    private static int[] assignStats(ArrayList<Integer> statList, ClassStringEnum classString) {
        //Given the stats that have been rolled, and the class name
        //This function orders the stats in the optimal order for that class
        Integer[] statPriority = classString.getClassPriority();

        int[] sortedStats = new int[6];

        int index;
        for (int i =0;i<6;i++){
            //Find index of highest priority stat
            index= Arrays.asList(statPriority).indexOf(i);
            //Fill the sorted stats array at that index with the current highest remaining stat
            sortedStats[index] = Collections.max(statList);
            statList.remove(Collections.max(statList));
        }
        return sortedStats;
    }

    private static Integer rollD6() {
        //Rolls a D6
        Random roller = new Random();
        return Math.abs(roller.nextInt() % 6)+1;
    }

    private static int sum(ArrayList<Integer> rollList) {
        //Sums the elements of a list
        int i;
        int sum = 0;
        for(i = 0; i < rollList.size(); i++) {
            sum += rollList.get(i);
        }
        return sum;
    }
}
