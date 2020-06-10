package com.tse.newhabit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Habit {
    private String Title,ID;
    private ArrayList<Calendar> HabitCalendarList = new ArrayList<>();
    private ArrayList<Object> alarms = new ArrayList<>();
    private ArrayList<Boolean> checks = new ArrayList<>();
    final private Date date = new Date();
    public Object beginDate;

    private ArrayList<String> diaries = new ArrayList<>();

    public Habit(String t){
        this.Title = t;
        init();
    }

    public Habit(String t,Object date,Object alarms,Object checks,Object diaries,String ID){
        this.Title = t;
        this.beginDate = date;
        this.diaries = (ArrayList<String>) diaries;
        this.alarms = (ArrayList<Object>) alarms;
        this.checks = (ArrayList<Boolean>) checks;
        this.ID = ID;
    }
    public String getID(){
        return ID;
    }
    public ArrayList<Boolean> getCheck(){
        return checks;
    }

    public ArrayList<String> getDiary(){
        return diaries;
    }
    public String getDateTime(){
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = sdFormat.format(date);
        return strDate;
    }

    public void init(){
        for(int i=0;i<21;i++){
            checks.add(false);
            diaries.add("");
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
    }
    public ArrayList<Object> getHabitAlarmList(){
        return alarms;
    }
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
            alarms.add(calendarData);
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
        diaries.set(getGapCount(d),newDiary);
    }
    public String getDiary(int position){
        return diaries.get(position);
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

