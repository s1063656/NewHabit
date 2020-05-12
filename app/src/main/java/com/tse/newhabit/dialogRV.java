package com.tse.newhabit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class dialogRV extends RecyclerView.Adapter<dialogRV.LinearViewHolder> {
    private Context mContext;
    public dialogRV(Context context){
        this.mContext = context;
    }
    @NonNull
    @Override
    public dialogRV.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.dialog_rview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final dialogRV.LinearViewHolder holder, int position) {
        holder.dView_item_num.setText(""+(position+1));
        holder.dView_item_time.setText("17:00");

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{

        private TextView dView_item_time;
        private TextView dView_item_num;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);

            dView_item_num = (TextView) itemView.findViewById(R.id.timeTitle);

            dView_item_time = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
