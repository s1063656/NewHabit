package com.tse.newhabit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class dialogRV extends RecyclerView.Adapter<dialogRV.LinearViewHolder> {
    private Context mContext;

    private ArrayList<String> dRVAlarmList = new ArrayList();
    public dialogRV(Context context){
        this.mContext = context;
    }
    @NonNull
    @Override
    public dialogRV.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.dialog_rview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final dialogRV.LinearViewHolder holder, final int position) {
        holder.dView_item_num.setText(""+(position+1));
        holder.dView_item_time.setText(dRVAlarmList.get(position));
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.HabitList.get(MainActivity.HabitList.size()-1).removeRemindTime(position);
                dRVAlarmList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(0,dRVAlarmList.size());
            }
        });
    }
    public void addData(int position,String data){
        dRVAlarmList.add(data);
        notifyItemInserted(position);
        notifyItemRangeChanged(0,dRVAlarmList.size());

    }
    @Override
    public int getItemCount() {
        try{return dRVAlarmList.size();}catch (NullPointerException e){return 0;}
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView dView_item_time;
        private TextView dView_item_num;
        private Button del;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            del = (Button) itemView.findViewById(R.id.del);
            dView_item_num = (TextView) itemView.findViewById(R.id.timeTitle);
            dView_item_time = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
