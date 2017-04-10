/*
 * Copyright (c) 2017 Ziqi Yuan All rights reserved
 */

package com.chuxiuhong.judge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PeoplePassword extends People {
    public static final Map<String,Integer>

    public PeoplePassword(int[] charList, int[] stateList, int[] timeList) {
        super(charList, stateList, timeList);
    }
    public PeoplePassword(ArrayList<Integer> charList,ArrayList<Integer> stateList,ArrayList<Integer> timeList){
        super(charList,stateList,timeList);
    }
    public PwdFeature getFeature(String password){
        int len = password.length();
        int[] pressTime = new int[len];
        int[] flightTime = new int[len-1];
        for (int i = 0; i < len; i++) {

        }
    }
    public PwdFeature[] getFeature(String password){

    }
}
