package com.tse.newhabit;

import android.util.ArrayMap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class habit {
    private String Title;
    private ArrayList<Calendar> c = new ArrayList<>();
    private Boolean [] check = new Boolean [30];

    public habit(String t){
         this.Title = t;
         init();
    }
    public void init(){
        for(int i=0;i<30;i++){
            check[i]=false;
        }
    }

    public void addRemindTime(int h,int m){
        c.add(Calendar.getInstance());
        c.get(c.size()-1).set(Calendar.HOUR_OF_DAY,h);
        c.get(c.size()-1).set(Calendar.MINUTE,m);
    }

    public void showAlarm(){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        System.out.println(df.format(c.get(c.size()-1).getTime()));
    }
}
