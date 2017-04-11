/*
 * Copyright (c) 2017 Ziqi Yuan All rights reserved
 */

package com.chuxiuhong.judge;

import java.util.ArrayList;


public class PeoplePassword extends People {
    public PeoplePassword(int[] charList, int[] stateList, int[] timeList) {
        super(charList, stateList, timeList);
    }

    public PeoplePassword(ArrayList<Integer> charList, ArrayList<Integer> stateList, ArrayList<Integer> timeList) {
        super(charList, stateList, timeList);
    }

    public static PwdFeature getFeature(PeoplePassword pp, String password) {
        password = password.toUpperCase();
        int len = password.length();
        int[] charList = pp.getCharList();
        int[] stateList = pp.getStateList();
        int[] timeList = pp.getTimeList();
        float[] pressTime = new float[len];
        float[] flightTime = new float[len - 1];
        int[] startTime = new int[len];
        int cur = 0;
        float deltaTime = 0;
        for (int i = 0; i < charList.length; i++) {
            if (password.charAt(i) != '_') {
                if (charList[i] == (int) password.charAt(cur) && stateList[i] == 0) {
                    startTime[cur] = timeList[i];
                    pressTime[cur] = timeList[i];
                    for (int j = i; j < charList.length; j++) {
                        if (charList[j] == (int) password.charAt(cur) && stateList[i] == 1) {
                            deltaTime = timeList[j] - pressTime[cur];
                            pressTime[cur] = deltaTime;
                        }
                    }
                    cur++;
                }
            } else {
                if (charList[i] == 189 && password.charAt(cur) == '_' && stateList[i] == 0) {
                    startTime[cur] = timeList[i];
                    pressTime[cur] = timeList[i];
                    for (int j = i; j < charList.length; j++) {
                        if (charList[i] == 189 && password.charAt(cur) == '_' && stateList[i] == 1) {
                            pressTime[cur] = timeList[j] - pressTime[cur];
                        }
                    }
                    cur++;
                }
            }
        }
        for (int i = 0; i < len - 1; i++) {
            flightTime[i] = startTime[i + 1] - pressTime[i] - startTime[i];
        }
        return new PwdFeature(pressTime, flightTime);
    }

    public static PwdFeature getFeature(PeoplePassword[] pp, String password) {
        int len = password.length();
        float[] pressTime = new float[len];
        float[] flightTime = new float[len - 1];
        float[] minPressTime = new float[len];
        float[] maxPressTime = new float[len];
        float[] maxFlightTime = new float[len - 1];
        float[] minFlightTime = new float[len - 1];
        float[] varPress = new float[len];
        float[] varFlight = new float[len - 1];
        float temp = 0;
        for (int i = 0; i < len; i++) {
            minPressTime[i] = 100000;
            maxPressTime[i] = -100;
        }
        for (int i = 0; i < len - 1; i++) {
            maxFlightTime[i] = -100;
            minFlightTime[i] = 100000;
        }
        for (int i = 1; i < pp.length; i++) {
            for (int j = 0; j < len; j++) {
                pressTime[j] += PeoplePassword.getFeature(pp[i], password).getPressTime()[j];
                if (PeoplePassword.getFeature(pp[i], password).getPressTime()[j] > maxPressTime[j]) {
                    maxPressTime[j] = PeoplePassword.getFeature(pp[i], password).getPressTime()[j];
                }
                if (PeoplePassword.getFeature(pp[i], password).getPressTime()[j]< minPressTime[j]) {
                    minPressTime[j] = PeoplePassword.getFeature(pp[i], password).getPressTime()[j];
                }
            }
            for (int j = 0; j < len - 1; j++) {
                flightTime[j] += PeoplePassword.getFeature(pp[i], password).getFlightTime()[j];
                if (PeoplePassword.getFeature(pp[i], password).getFlightTime()[j] > maxFlightTime[j]) {
                    maxFlightTime[j] = PeoplePassword.getFeature(pp[i], password).getFlightTime()[j];
                }
                if (PeoplePassword.getFeature(pp[i], password).getFlightTime()[j] < minFlightTime[j]) {
                    minFlightTime[j] = PeoplePassword.getFeature(pp[i], password).getFlightTime()[j];
                }
            }
        }
        for (int i = 0; i < len; i++) {
            pressTime[i] = pressTime[i] / pp.length;
        }
        for (int i = 0; i < len - 1; i++) {
            flightTime[i] = flightTime[i] / pp.length;
        }
        for (int i = 0; i < len; i++) {
            temp = 0;
            for (int j = 0; j < pp.length; j++) {
                temp += Math.pow((pressTime[i] - PeoplePassword.getFeature(pp[j], password).getPressTime()[i]), 2);
            }
            varPress[i] = temp / pp.length;
        }
        for (int i = 0; i < len - 1; i++) {
            temp = 0;
            for (int j = 0; j < pp.length; j++) {
                temp += Math.pow((flightTime[i] - PeoplePassword.getFeature(pp[j], password).getFlightTime()[i]), 2);
            }
            varFlight[i] = temp / pp.length;
        }
        return new PwdFeature(pressTime, flightTime, maxPressTime, minPressTime, maxFlightTime, minFlightTime, varPress, varFlight);
    }

    public static void main(String[] args) {
        String a = "abBB-_234";
        System.out.println((int) a.toUpperCase().charAt(0));
    }
}
