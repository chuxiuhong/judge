/*
 * Copyright (c) 2017 Ziqi Yuan All rights reserved
 */

package com.chuxiuhong.judge;
import java.util.*;

public class PeopleText extends People{
    public PeopleText(int[] charList, int[] stateList, int[] timeList){
        super(charList,stateList,timeList);
    }
    public static Feature getFeature(PeopleText peopleText){
        Map<Integer,Float> press = new HashMap<>();
        Map<Integer[],Float> flight = new HashMap<>();
        Map<Integer,Integer> pressTimes = new HashMap<>();
        Map<Integer[],Integer> flightTimes = new HashMap<>();
        Integer[] flightList = new Integer[2];
        int lastPress = 0;
        int[] charList = peopleText.getCharList();
        int[] stateList = peopleText.getStateList();
        int[] timeList = peopleText.getStateList();
        for (int i = 0; i < peopleText.getCharList().length; i++) {
            if(stateList[i] == 0){
                if(lastPress != 0){

                }
            }
        }

    }
    public static void main(String[] args) {
        System.out.println("123");
    }
}
