/*
 * Copyright (c) 2017 Ziqi Yuan All rights reserved
 */

package com.chuxiuhong.judge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by 56363 on 2017/7/11.
 */

public class ManyText {
    private ArrayList<People> textList;

    public ManyText() {
        this.textList = new ArrayList<People>();
    }

    public void addText(People t) {
        textList.add(t);

    }

    private ArrayList getFeature(int n, People x) {
        ArrayList<HashMap<String,Float>> res = new ArrayList<>();
        Stack<Integer> pressKey = new Stack<>();
        Stack<Integer> pressTime = new Stack<>();
        HashMap<Integer,Integer> pressStack = new HashMap<>();

        int length = x.getCharList().length;
        for (int i = 0; i <= n; i++) {
            res.add(new HashMap<>());
        }

        return res;
    }

}
