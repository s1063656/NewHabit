package com.tse.newhabit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.LinearViewHolder> {
    private Context mContext;
    private int mNum;
    public historyAdapter(Context context,int num){
        this.mContext = context;this.mNum = num;
    }
    @NonNull
    @Override
    public historyAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.history_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final historyAdapter.LinearViewHolder holder, final int position) {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = sdFormat.parse(MainActivity.HabitList.get(mNum).getDateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,position);
        holder.historyDate.setText(sdFormat.format(calendar.getTime()));
        holder.historyDiary.setText(MainActivity.HabitList.get(mNum).getDiary(position));
    }

    @Override
    public int getItemCount() {
        return 21;
    }
    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView historyDate,historyDiary;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            historyDate = (TextView) itemView.findViewById(R.id.dateOfdiary);
            historyDiary = (TextView) itemView.findViewById(R.id.diaryOfdate);
        }
    }
}

