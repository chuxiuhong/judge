/*
 * Copyright (c) 2017 Ziqi Yuan All rights reserved
 */

package com.chuxiuhong.judge;

import java.util.ArrayList;


public class TextJudger {

    private final int flightTimesThreshold;
    private final int flightTimeThreshold;
    private final int pressTimeThreshold;
    private final int pressTimesThreshold;
    private final float pressWeight;


    public TextJudger(int flightTimesThreshold, int flightTimeThreshold, int pressTimeThreshold, int pressTimesThreshold, float pressWeight) {
        this.flightTimesThreshold = flightTimesThreshold;
        this.flightTimeThreshold = flightTimeThreshold;
        this.pressTimeThreshold = pressTimeThreshold;
        this.pressTimesThreshold = pressTimesThreshold;
        this.pressWeight = pressWeight;
    }

    public TextJudger() {
        this.flightTimesThreshold = 3;
        this.flightTimeThreshold = 2000;
        this.pressTimeThreshold = 500;
        this.pressTimesThreshold = 3;
        this.pressWeight = 0.5f;
    }

    /**
     *
     * @param train train data
     * @param test test data
     * @return The similarity between train data and test data. Value from 0 to 1
     */
    public float compare(PeopleText train, PeopleText test) {
        Feature trainFeature = PeopleText.getFeature(train);
        Feature testFeature = PeopleText.getFeature(test);
        ArrayList<Float> trainPressVec = new ArrayList<>();
        ArrayList<Float> testPressVec = new ArrayList<>();
        ArrayList<Float> trainFlightVec = new ArrayList<>();
        ArrayList<Float> testFlightVec = new ArrayList<>();
        ArrayList<Integer> trainPressTimesVec = new ArrayList<>();
        ArrayList<Integer> testPressTimesVec = new ArrayList<>();
        ArrayList<Integer> trainFlightTimesVec = new ArrayList<>();
        ArrayList<Integer> testFlightTimesVec = new ArrayList<>();
        for (Integer i : trainFeature.getPress().keySet()
                ) {
            if (testFeature.getPress().keySet().contains(i)) {
                if (trainFeature.getPress().get(i) <= pressTimeThreshold && testFeature.getPress().get(i) <= pressTimeThreshold
                        && trainFeature.getPressTimes().get(i) >= pressTimesThreshold && testFeature.getPressTimes().get(i) >= pressTimesThreshold) {
                    trainPressVec.add(trainFeature.getPress().get(i));
                    testPressVec.add(testFeature.getPress().get(i));
                    trainPressTimesVec.add(trainFeature.getPressTimes().get(i));
                    testPressTimesVec.add(testFeature.getPressTimes().get(i));
                }
            }
        }
        for (Integer[] i : trainFeature.getFlight().keySet()
                ) {
            if (testFeature.getFlight().keySet().contains(i)) {
                if (trainFeature.getFlight().get(i) <= flightTimeThreshold && testFeature.getFlight().get(i) <= flightTimeThreshold
                        && trainFeature.getFlightTimes().get(i) >= flightTimesThreshold && testFeature.getFlightTimes().get(i) >= flightTimesThreshold) {
                    trainFlightVec.add(trainFeature.getFlight().get(i));
                    testFlightVec.add(testFeature.getFlight().get(i));
                    trainFlightTimesVec.add(trainFeature.getFlightTimes().get(i));
                    testFlightTimesVec.add(testFeature.getFlightTimes().get(i));
                }
            }
        }
        System.out.println(trainPressVec);
        System.out.println(testPressVec);
        System.out.println(trainFlightVec);
        System.out.println(testFlightVec);
        float pressSimilar = TextJudger.cosSimilar(trainPressVec,testPressVec);
        float flightSimilar = TextJudger.cosSimilar(trainFlightVec,testFlightVec);
//        float pressSimilar = TextJudger.weightCosSimilar(trainPressVec,trainPressTimesVec,testFlightVec,testFlightTimesVec);
//        float flightSimilar = TextJudger.weightCosSimilar(trainFlightVec,trainFlightTimesVec,testFlightVec,testFlightTimesVec);
        return pressWeight * pressSimilar + (1-pressSimilar) * flightSimilar;

    }

    /**
     *
     * @param trainList train data List
     * @param test test data
     * @return The similarity between train data List and test data. Value from 0 to 1
     */
    public float compare(PeopleText[] trainList,PeopleText test){
        Feature trainFeature = PeopleText.getFeature(trainList);
        Feature testFeature = PeopleText.getFeature(test);
        ArrayList<Float> trainPressVec = new ArrayList<>();
        ArrayList<Float> testPressVec = new ArrayList<>();
        ArrayList<Float> trainFlightVec = new ArrayList<>();
        ArrayList<Float> testFlightVec = new ArrayList<>();
        ArrayList<Integer> trainPressTimesVec = new ArrayList<>();
        ArrayList<Integer> testPressTimesVec = new ArrayList<>();
        ArrayList<Integer> trainFlightTimesVec = new ArrayList<>();
        ArrayList<Integer> testFlightTimesVec = new ArrayList<>();
        for (Integer i : trainFeature.getPress().keySet()
                ) {
            if (testFeature.getPress().keySet().contains(i)) {
                if (trainFeature.getPress().get(i) <= pressTimeThreshold && testFeature.getPress().get(i) <= pressTimeThreshold
                        && trainFeature.getPressTimes().get(i) >= pressTimesThreshold && testFeature.getPressTimes().get(i) >= pressTimesThreshold) {
                    trainPressVec.add(trainFeature.getPress().get(i));
                    testPressVec.add(testFeature.getPress().get(i));
                    trainPressTimesVec.add(trainFeature.getPressTimes().get(i));
                    testPressTimesVec.add(testFeature.getPressTimes().get(i));
                }
            }
        }
        for (Integer[] i : trainFeature.getFlight().keySet()
                ) {
            if (testFeature.getFlight().keySet().contains(i)) {
                if (trainFeature.getFlight().get(i) <= flightTimeThreshold && testFeature.getFlight().get(i) <= flightTimeThreshold
                        && trainFeature.getFlightTimes().get(i) >= flightTimesThreshold && testFeature.getFlightTimes().get(i) >= flightTimesThreshold) {
                    trainFlightVec.add(trainFeature.getFlight().get(i));
                    testFlightVec.add(testFeature.getFlight().get(i));
                    trainFlightTimesVec.add(trainFeature.getFlightTimes().get(i));
                    testFlightTimesVec.add(testFeature.getFlightTimes().get(i));
                }
            }
        }
        float pressSimilar = TextJudger.cosSimilar(trainPressVec,testPressVec);
        float flightSimilar = TextJudger.cosSimilar(trainFlightVec,testFlightVec);
//        float pressSimilar = TextJudger.weightCosSimilar(trainPressVec,trainPressTimesVec,testFlightVec,testFlightTimesVec);
//        float flightSimilar = TextJudger.weightCosSimilar(trainFlightVec,trainFlightTimesVec,testFlightVec,testFlightTimesVec);
        return pressWeight * pressSimilar + (1-pressSimilar) * flightSimilar;
    }
    /**
     *
     * @param a input vector a
     * @param b input vector b
     * @return normalized Cos Similar ,Value from 0 to 1
     * @throws IllegalArgumentException when len(a) != len(b)
     */
    private static float cosSimilar(ArrayList<Float> a, ArrayList<Float> b) throws IllegalArgumentException {
        if (a.size() != b.size()) {
            throw new IllegalArgumentException("Length of vector must equal");
        }
        double lenA, lenB;
        double tmp = 0;
        double tmp2 = 0;
        for (int i = 0; i < a.size(); i++) {
            tmp += a.get(i) * a.get(i);
            tmp2 += b.get(i) * b.get(i);
        }
        lenA = Math.sqrt(tmp);
        lenB = Math.sqrt(tmp2);
        tmp = 0;
        for (int i = 0; i < a.size(); i++) {
            tmp += a.get(i) * b.get(i) / (lenA * lenB);
        }
        if (tmp<0){
            tmp = 0;
        }
        return (float) tmp;
    }

    /**
     *
     * @param a input vector a
     * @param aWeight the weight of a
     * @param b input vector b
     * @param bWeight the weight of b
     * @return normalized Weighted Cos Similar ,Value from 0 to 1
     * @throws IllegalArgumentException
     */
    private static float weightCosSimilar(ArrayList<Float> a, ArrayList<Integer> aWeight, ArrayList<Float> b, ArrayList<Integer> bWeight) throws IllegalArgumentException {
        if (a.size() != b.size() || aWeight.size() != a.size() || bWeight.size() != b.size()) {
            throw new IllegalArgumentException("Length of vector must equal");
        }
        double lenA, lenB;
        double tmp = 0;
        double tmp2 = 0;
        float sumOfAweight = 0;
        float sumOfBweight = 0;
        for (int i = 0; i < a.size(); i++) {
            tmp += a.get(i) * a.get(i);
            tmp2 += b.get(i) * b.get(i);
            sumOfAweight += aWeight.get(i);
            sumOfBweight += bWeight.get(i);
        }
        lenA = Math.sqrt(tmp);
        lenB = Math.sqrt(tmp);
        tmp = 0;
        for (int i = 0; i < a.size(); i++) {
            tmp += a.get(i) * b.get(i) * (aWeight.get(i) + bWeight.get(i))/ ((lenA * lenB) * (sumOfAweight+sumOfBweight));
        }
        if (tmp <0){
            tmp = 0;
        }
        return (float) tmp;
    }

    public static void main(String[] args) {
        ArrayList<Float> a = new ArrayList<>();
        ArrayList<Float> b = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            a.add((float) i);
            b.add((float) i* -1);
        }
        System.out.println(TextJudger.cosSimilar(a,b));
    }

    public float getFlightTimesThreshold() {
        return flightTimesThreshold;
    }

    public float getFlightTimeThreshold() {
        return flightTimeThreshold;
    }

    public float getPressTimeThreshold() {
        return pressTimeThreshold;
    }

    public float getPressTimesThreshold() {
        return pressTimesThreshold;
    }

    public float getPressWeight() {
        return pressWeight;
    }
}

