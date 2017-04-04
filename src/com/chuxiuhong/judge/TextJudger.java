/*
 * Copyright (c) 2017 Ziqi Yuan All rights reserved
 */

package com.chuxiuhong.judge;


public class TextJudger {
    private final float flightTimesThereshold;
    private final float flightTimeThereshold;
    private final float pressTimeThereshold;
    private final float pressTimesThereshold;
    private final float pressWeight;

    public TextJudger(float flightTimesThereshold, float flightTimeThereshold, float pressTimeThereshold, float pressTimesThereshold, float pressWeight) {
        this.flightTimesThereshold = flightTimesThereshold;
        this.flightTimeThereshold = flightTimeThereshold;
        this.pressTimeThereshold = pressTimeThereshold;
        this.pressTimesThereshold = pressTimesThereshold;
        this.pressWeight = pressWeight;
    }

}

