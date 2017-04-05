/*
 * Copyright (c) 2017 Ziqi Yuan All rights reserved
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 56363 on 2017/4/5.
 */
public class test {
    public static void main(String[] args) {
        Map<Integer[],Integer> p = new HashMap<>();
        Integer[] a = new Integer[2];
        a[0] = 1;
        a[1] = 2;
        System.out.println(a.clone().hashCode());
        p.put(a.clone(),233);
        a[0] = 8;
        a[1] = 9;
        System.out.println(a.clone().hashCode());
    }
}
