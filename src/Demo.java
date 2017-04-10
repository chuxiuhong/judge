/*
 * Copyright (c) 2017 Ziqi Yuan All rights reserved
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

import com.chuxiuhong.judge.PeopleText;
import com.chuxiuhong.judge.TextJudger;
import com.csvreader.*;
import com.chuxiuhong.*;
public class Demo {
    public static void main(String[] args) {
        try {
            CsvReader r = new CsvReader("c://data//judge//src//test3552.csv",',',Charset.forName("GBK"));
            try {
                r.readHeaders();
                ArrayList<Integer> charList = new ArrayList<>();
                ArrayList<Integer> stateList = new ArrayList<>();
                ArrayList<Integer> timeList = new ArrayList<>();
                ArrayList<PeopleText> peopleTexts = new ArrayList<>();
                while (r.readRecord()){
                    if (Character.isDigit(r.get(0).charAt(0))){
                        charList.add(Integer.parseInt(r.get(0)));
                        stateList.add(Integer.parseInt((r.get(2))));
                        timeList.add(Integer.parseInt((r.get(1))));
                    }
                    else {
                        peopleTexts.add(new PeopleText(charList,stateList,timeList));
                        charList.clear();
                        stateList.clear();
                        timeList.clear();
                    }
                }
                peopleTexts.add(new PeopleText(charList,stateList,timeList));
                TextJudger tj = new TextJudger();
                PeopleText[] a = new PeopleText[2];
                a[0] = peopleTexts.get(1);
                a[1] = peopleTexts.get(2);
                System.out.println(tj.compare(a,peopleTexts.get(0)));
                r.close();

            }
            catch (IOException e){
                System.out.println("IO error");
            }

        }
        catch (FileNotFoundException e){
            System.out.println("no file");
        }

    }
}
