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
        Integer[] b = new Integer[2];
        int[] c = new int[2];
        int[] d = new int[2];
        String s1 = "a-b";
        String s2 = "a-b";
        a[0] = 1;
        a[1] = 2;
        System.out.println(a.clone().hashCode());
        b[0] = 1;
        b[1] = 2;
        System.out.println(b.clone().hashCode());
        c[0] = 1;
        c[1] = 2;
        d[0] = 1;
        d[1] = 2;
        System.out.println(c.hashCode());
        System.out.println(d.hashCode());
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
    }
}
