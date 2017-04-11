/*
 * Copyright (c) 2017 Ziqi Yuan All rights reserved
 */

package com.chuxiuhong.judge;

public class PwdJudger {
    private float pressWeight;

    public PwdJudger(float pressWeight) {
        this.pressWeight = pressWeight;
    }

    public float compare(PeoplePassword[] trainList, PeoplePassword test, String password) throws IllegalArgumentException{
        float pressSimilar = 0;
        float flightSimilar = 0;
        PwdFeature trainFeature = PeoplePassword.getFeature(trainList,password);
        PwdFeature testFeature = PeoplePassword.getFeature(test,password);
        float[] trainPress = trainFeature.getPressTime();
        float[] trainFlight = trainFeature.getFlightTime();
        float[] testPress = testFeature.getPressTime();
        float[] testFlight = testFeature.getFlightTime();
        float[] varPress = trainFeature.getVarPress();
        float[] varFlight = trainFeature.getVarFlight();
        float[] maxPress = trainFeature.getMaxPressTime();
        float[] minPress = trainFeature.getMinPressTime();
        float[] maxFlight = trainFeature.getMaxFlightTime();
        float[] minFlight = trainFeature.getMinFlightTime();
        float[] pressSimilarList = new float[trainList.length];
        float[] flightSimilarList = new float[trainFlight.length];
        if (trainList.length != testPress.length || trainFlight.length != testFlight.length){
            throw new IllegalArgumentException("\nmust input the same password!");
        }
        for (int i = 0; i < trainPress.length; i++) {
            if (testPress[i] < maxPress[i] && testPress[i] > minPress[i]){
                pressSimilarList[i] = 1;
            }
            else {
                pressSimilarList[i] = (float) (Math.pow(Math.E,(-1) * Math.pow(testPress[i]-trainPress[i],2) / (2 * varPress[i])) / (Math.sqrt(varPress[i]) * Math.sqrt(2 * Math.PI)));
            }
        }
        for (int i = 0; i < trainFlight.length; i++) {
            if (testFlight[i] < maxFlight[i] && testFlight[i] > minFlight[i]){
                flightSimilarList[i] = 1;
            }
            else {
                flightSimilarList[i] = (float) (Math.pow(Math.E,(-1) * Math.pow(testFlight[i]-trainFlight[i],2) / (2 * varFlight[i])) / (Math.sqrt(varFlight[i]) * Math.sqrt(2 * Math.PI)));
            }
        }
        for (int i = 0; i < pressSimilarList.length; i++) {
            pressSimilar += pressSimilarList[i];
        }
        pressSimilar = pressSimilar / pressSimilarList.length;
        for (int i = 0; i < flightSimilarList.length; i++) {
            flightSimilar += flightSimilarList[i];
        }
        flightSimilar = flightSimilar / flightSimilarList.length;
        return pressSimilar * pressWeight + flightSimilar * (1 - pressWeight);
    }

    public static void main(String[] args) {

    }
}
