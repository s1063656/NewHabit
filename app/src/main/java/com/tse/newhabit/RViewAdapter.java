package com.tse.newhabit;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.LinearViewHolder> {
    private Context mContext;
    private ArrayList<Habit> RVHabitList =  MainActivity.HabitList;
    private boolean[] Checkbox = new boolean[30];
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
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(Checkbox[position]) {
                        holder.img.setImageResource(R.drawable.light_off);
                    }else {
                        holder.img.setImageResource(R.drawable.light_on);
                    }
                    Checkbox[position] =!Checkbox[position];

            }
        });
        holder.RView_Diary.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                View dView = LayoutInflater.from(mContext).inflate(R.layout.diary_dialog,null);
                builder.setView(dView);
                builder.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return RVHabitList.size();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView RView_Title,RView_Diary;
        private ImageView img;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            RView_Title = (TextView) itemView.findViewById(R.id.RView_item_Title);
            img = (ImageView) itemView.findViewById(R.id.imageView);
            for(int i =0;i<30;i++){
                Checkbox[i]=false;
            }
            RView_Diary = (TextView) itemView.findViewById(R.id.diary_edit);

        }
    }
}
