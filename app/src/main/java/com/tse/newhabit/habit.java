package com.tse.newhabit;

import android.content.Context;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Habit {
    private String Title;
    private ArrayList<Calendar> HabitCalendarList = new ArrayList<>();
    private Boolean [] check = new Boolean [21];
    final private Date date = new Date();
    private String[] diary = new String[21];
    public String getDateTime(){
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = sdFormat.format(date);
        return strDate;
    }
    public Habit(String t){
         this.Title = t;
         init();
    }
    public void init(){
        for(int i=0;i<21;i++){
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
    public String getSingleAlarm(int num){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(HabitCalendarList.get(num).getTime());
    }
    public void showAlarm(){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        for(int i = 0;i<HabitCalendarList.size();i++) {
            System.out.println(df.format(HabitCalendarList.get(i).getTime()));
        }

    }
    public void editDiary(Date d,String newDiary){
        diary[getGapCount(d)]=newDiary;
    }
    public String getDiary(int position){
        return diary[position];
    }
    public  int getGapCount(Date endDate) {
             Calendar fromCalendar = Calendar.getInstance();
             fromCalendar.setTime(date);
             fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
             fromCalendar.set(Calendar.MINUTE, 0);
             fromCalendar.set(Calendar.SECOND, 0);
             fromCalendar.set(Calendar.MILLISECOND, 0);

            Calendar toCalendar = Calendar.getInstance();
            toCalendar.setTime(endDate);
            toCalendar.set(Calendar.HOUR_OF_DAY, 0);
            toCalendar.set(Calendar.MINUTE, 0);
            toCalendar.set(Calendar.SECOND, 0);
            toCalendar.set(Calendar.MILLISECOND, 0);
            System.out.println((int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24)));
            return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }
    }

