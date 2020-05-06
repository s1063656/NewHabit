package com.tse.newhabit;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.LinearViewHolder> {
    private Context mContext;


    private boolean[] check = new boolean[30];
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
        holder.RView_item_Title.setText(position + "_Title");
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(check[position]) {
                        holder.img.setImageResource(R.drawable.light_off);
                    }else {
                        holder.img.setImageResource(R.drawable.light_on);
                    }
                    check[position] =!check[position];

            }
        });
    }
    @Override
    public int getItemCount() {
        return 20;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView RView_item_Title;
        private ImageView img;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            RView_item_Title = (TextView) itemView.findViewById(R.id.RView_item_Title);
            img = (ImageView) itemView.findViewById(R.id.imageView);
            for(int i =0;i<30;i++){
                check[i]=false;
            }
        }



    }
}
