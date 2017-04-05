/*
 * Copyright (c) 2017 Ziqi Yuan All rights reserved
 */

package com.chuxiuhong.judge;

import java.util.HashMap;
import java.util.Map;

class Feature {
    private Map<Integer, Float> press;
    private Map<Integer[], Float> flight;
    private Map<Integer, Integer> pressTimes;
    private Map<Integer[], Integer> flightTimes;

    public Feature() {
        press = new HashMap<>();
        flight = new HashMap<>();
    }

    public Feature(Map<Integer, Float> press, Map<Integer[], Float> flight, Map<Integer, Integer> pressTimes, Map<Integer[], Integer> flightTimes) {
        this.press = press;
        this.flight = flight;
        this.pressTimes = pressTimes;
        this.flightTimes = flightTimes;
    }

    public Map<Integer, Float> getPress() {
        return press;
    }

    public Map<Integer[], Float> getFlight() {
        return flight;
    }

    public void setFlight(Map<Integer[], Float> flight) {
        this.flight = flight;
    }

    public void setPress(Map<Integer, Float> press) {
        this.press = press;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("press: ");
        for (Integer i : press.keySet()
                ) {
            sb.append(i.toString() + ":" + press.get(i) + " ");
        }
        sb.append("flight: ");
        for (Integer[] i : flight.keySet()
                ) {
            sb.append(i[0].toString() + "," + i[1].toString() + ":" + flight.get(i) + " ");
        }
        return sb.toString();
    }

    public Map<Integer, Integer> getPressTimes() {
        return pressTimes;
    }

    public Map<Integer[], Integer> getFlightTimes() {
        return flightTimes;
    }

    public static void main(String[] args) {
        Feature test = new Feature();
        Map<Integer, Float> p = new HashMap<>();
        Map<Integer[], Float> f = new HashMap<>();
        Integer[] k = new Integer[2];
        p.put(10, 12.3f);
        p.put(22, 99.2f);
        k[0] = 44;
        k[1] = 22;
        f.put(k, 15.7f);
        k[0] = 13;
        k[1] = 27;
        f.put(k, 89.9f);
        test.setFlight(f);
        test.setPress(p);
        System.out.println(test.toString());

    }
}
