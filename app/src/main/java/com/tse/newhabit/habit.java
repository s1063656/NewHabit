package com.tse.newhabit;

import android.content.Context;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Habit {
    private String Title;
    private ArrayList<Calendar> HabitCalendarList = new ArrayList<>();
    private Boolean [] check = new Boolean [30];
    public Habit(String t){
         this.Title = t;
         init();
    }
    public void init(){
        for(int i=0;i<30;i++){
            check[i]=false;
        }
    }
    public ArrayList<Calendar> getCalendar(){
        return HabitCalendarList;
    }
    public int getNumOfAlarm(){
        return HabitCalendarList.size();
    }
    public String rData(){
        return new SimpleDateFormat("HH:mm").format(HabitCalendarList.get(HabitCalendarList.size()-1).getTime());
    };
    public String getTitle(){
        return Title;
    }
    public Boolean addRemindTime(Context context ,Calendar calendar){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String calendarData = df.format(calendar.getTime());
        Boolean ifSame = false;
        for (int i = 0;i<HabitCalendarList.size();i++) {
            if (calendarData.equals(df.format(HabitCalendarList.get(i).getTime()))) {
                ifSame = true;
                break;
            }
        }
        if(!ifSame) {
            HabitCalendarList.add(calendar);
            Toast.makeText(context, df.format(HabitCalendarList.get(HabitCalendarList.size() - 1).getTime()), Toast.LENGTH_SHORT).show();
            System.out.println(df.format(HabitCalendarList.get(HabitCalendarList.size() - 1).getTime()));
            return true;
        }else{

            return false;
        }

    }
    public void removeRemindTime(int position){
        HabitCalendarList.remove(position);
    }
    public void showAlarm(Context context){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        for(int i = 0;i<HabitCalendarList.size();i++) {
            System.out.println(df.format(HabitCalendarList.get(i).getTime()));
        }

    }
}
