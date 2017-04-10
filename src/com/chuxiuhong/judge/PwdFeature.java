/*
 * Copyright (c) 2017 Ziqi Yuan All rights reserved
 */

package com.chuxiuhong.judge;

class PwdFeature {
    private int[] pressTime;
    private int[] flightTime;

    public PwdFeature(int[] pressTime, int[] flightTime) {
        this.pressTime = pressTime;
        this.flightTime = flightTime;
    }

    public int[] getFlightTime() {
        return flightTime;
    }

    public int[] getPressTime() {
        return pressTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("pressTime: ");
        for (int i:pressTime
             ) {
            sb.append(i);
        }
        sb.append("\nflightTime: ");
        for (int i:flightTime
             ) {
            sb.append(i);
        }
        return sb.toString();
    }
}
