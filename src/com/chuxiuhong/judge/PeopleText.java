/*
 * Copyright (c) 2017 Ziqi Yuan All rights reserved
 */

package com.chuxiuhong.judge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PeopleText extends People {
    public PeopleText(int[] charList, int[] stateList, int[] timeList) {
        super(charList, stateList, timeList);
    }
    public PeopleText(ArrayList<Integer> charList,ArrayList<Integer> stateList , ArrayList<Integer> timeList){
        super(charList,stateList,timeList);
    }
    public static Feature getFeature(PeopleText peopleText) {
        Map<Integer, Float> press = new HashMap<>();
        Map<Integer[], Float> flight = new HashMap<>();
        Map<Integer, Integer> pressTimes = new HashMap<>();
        Map<Integer[], Integer> flightTimes = new HashMap<>();
        Map<Integer, Integer> pressStack = new HashMap<>();
        Integer[] flightList = new Integer[2];
        int[] lastPress = {0, 0};
        int[] charList = peopleText.getCharList();
        int[] stateList = peopleText.getStateList();
        int[] timeList = peopleText.getTimeList();
        float times;
        int thisTime;
        for (int i = 0; i < peopleText.getCharList().length; i++) {
            if (stateList[i] == 0) {
                if (lastPress[0] != 0) {
                    flightList[0] = lastPress[0];
                    flightList[1] = charList[i];
                    thisTime = timeList[i] - lastPress[1];
                    if (!flight.containsKey(flightList)) {
                        flight.put(flightList.clone(), (float) thisTime);
                        flightTimes.put(flightList.clone(), 1);
                    } else {
                        times = (float) (flightTimes.get(flightList) + 1);
                        flight.put(flightList.clone(), flight.get(flightList) * flightTimes.get(flightList) / times +
                                thisTime / times);
                        flightTimes.put(flightList.clone(), (int) times);
                    }
                    pressStack.put(charList[i], timeList[i]);
                }
                else {
                    pressStack.put(charList[i], timeList[i]);
                }
            } else {
                if (!press.containsKey(charList[i])) {
                    press.put(charList[i], (float) timeList[i] - pressStack.get(charList[i]));
                    pressTimes.put(charList[i], 1);
                } else {
                    thisTime = timeList[i] - pressStack.get(charList[i]);
                    times = (float) pressTimes.get(charList[i]) + 1;
                    press.put(charList[i], press.get(charList[i]) * pressTimes.get(charList[i]) / times + thisTime / times);
                    pressTimes.put(charList[i], (int) times);
                }
                lastPress[0] = charList[i];
                lastPress[1] = timeList[i];
            }
        }
        return new Feature(press, flight, pressTimes, flightTimes);
    }

    public static Feature getFeature(PeopleText[] peopleTexts) {
        Map<Integer, Float> press = new HashMap<>();
        Map<Integer[], Float> flight = new HashMap<>();
        Map<Integer, Integer> pressTimes = new HashMap<>();
        Map<Integer[], Integer> flightTimes = new HashMap<>();
        Map<Integer, Integer> pressStack = new HashMap<>();
        Integer[] flightList = new Integer[2];
        float times;
        int thisTime;
        for (PeopleText peopleText : peopleTexts
                ) {
            int[] lastPress = {0, 0};
            int[] charList = peopleText.getCharList();
            int[] stateList = peopleText.getStateList();
            int[] timeList = peopleText.getStateList();
            pressStack.clear();


            for (int i = 0; i < peopleText.getCharList().length; i++) {

                if (stateList[i] == 0) {
                    if (lastPress[0] != 0) {
                        flightList[0] = lastPress[0];
                        flightList[1] = charList[i];
                        thisTime = timeList[i] - lastPress[1];
                        if (!flight.containsKey(flightList)) {
                            flight.put(flightList.clone(), (float) thisTime);
                            flightTimes.put(flightList.clone(), 1);
                        } else {
                            times = (float) (flightTimes.get(flightList) + 1);
                            flight.put(flightList.clone(), flight.get(flightList) * flightTimes.get(flightList) / times +
                                    thisTime / times);
                            flightTimes.put(flightList.clone(), (int) times);
                        }
                        pressStack.put(charList[i], timeList[i]);
                    }
                    else {
                        pressStack.put(charList[i], timeList[i]);
                    }
                } else {
                    thisTime = timeList[i] - pressStack.get(charList[i]);
                    if (!press.containsKey(charList[i])) {
                        press.put(charList[i], (float) thisTime);
                        pressTimes.put(charList[i], 1);
                    } else {
                        times = (float) pressTimes.get(charList[i]) + 1;
                        press.put(charList[i], press.get(charList[i]) * pressTimes.get(charList[i]) / times + thisTime / times);
                        pressTimes.put(charList[i], (int) times);
                    }
                    lastPress[0] = charList[i];
                    lastPress[1] = timeList[i];
                }
            }
        }
        return new Feature(press, flight, pressTimes, flightTimes);
    }

    public static void main(String[] args) {
        System.out.println("123");
    }
}
