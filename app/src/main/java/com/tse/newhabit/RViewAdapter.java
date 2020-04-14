package com.tse.newhabit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.LinearViewHolder> {
    private Context mContext;
    public RViewAdapter(Context context){
        this.mContext = context;
    }
    @NonNull
    @Override
    public RViewAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RViewAdapter.LinearViewHolder holder, int position) {
        holder.RView_item_Title.setText(position+"_Title");
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView RView_item_Title;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            RView_item_Title = (TextView) itemView.findViewById(R.id.RView_item_Title);
        }
    }
}
