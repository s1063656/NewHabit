package com.tse.newhabit;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Date;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.LinearViewHolder> {
    private Context mContext;
    private ArrayList<Habit> RVHabitList =  MainActivity.HabitList;
    private boolean[] Checkbox = new boolean[30];
    private RecyclerView historyRV;
    public RViewAdapter(Context context){
        this.mContext = context;
    }
    @NonNull
    @Override
    public RViewAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RViewAdapter.LinearViewHolder holder, final int position) {
        holder.RView_Title.setText((position+1)+"  /  "+MainActivity.HabitList.get(position).getTitle());
        holder.checkLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(Checkbox[position]) {
                        holder.checkLight.setImageResource(R.drawable.light_off);
                    }else {
                        holder.checkLight.setImageResource(R.drawable.light_on);
                    }
                    Checkbox[position] =!Checkbox[position];
            }
        });
        holder.RView_Diary.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                View dView = LayoutInflater.from(mContext).inflate(R.layout.diary_dialog,null);
                final EditText editDiary = (EditText) dView.findViewById(R.id.diary_txv);
                builder.setView(dView);
                if(holder.RView_Diary.getText()!=""){
                    editDiary.setText(holder.RView_Diary.getText());
                }
                Button diaryDialogDone = (Button) dView.findViewById(R.id.diaryDone);
                final AlertDialog close = builder.show();

                diaryDialogDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.HabitList.get(position).editDiary(new Date(),editDiary.getText().toString());
                        holder.RView_Diary.setText(editDiary.getText().toString());

                        System.out.println("diary done");
                        close.dismiss();
                    }
                });

            }
        });
        holder.date.setText("項目開始於 : "+MainActivity.HabitList.get(MainActivity.HabitList.size()-1).getDateTime());
        holder.history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder historyBuilder = new AlertDialog.Builder(mContext);
                View historyView = LayoutInflater.from(mContext).inflate(R.layout.fragment_history,null);
                historyAdapter hisAdapter = new historyAdapter(mContext,position);
                historyRV = historyView.findViewById(R.id.historyList);
                historyRV.setLayoutManager(new LinearLayoutManager(mContext));
                historyRV.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
                historyRV.setAdapter(hisAdapter);
                historyRV.setItemViewCacheSize(21);

                TextView historyTitle = (TextView) historyView.findViewById(R.id.subjectTitle);
                historyTitle.setText(MainActivity.HabitList.get(position).getTitle());

                historyBuilder.setView(historyView);
                historyBuilder.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return RVHabitList.size();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView RView_Title,RView_Diary;
        private ImageView checkLight;
        private TextView date;
        private Button history_btn;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            history_btn = (Button) itemView.findViewById(R.id.history_button);
            RView_Title = (TextView) itemView.findViewById(R.id.RView_item_Title);
            checkLight = (ImageView) itemView.findViewById(R.id.imageView);
            for(int i =0;i<30;i++){
                Checkbox[i]=false;
            }
            RView_Diary = (TextView) itemView.findViewById(R.id.diary_edit);
            date = (TextView) itemView.findViewById(R.id.date);

        }
    }
}
