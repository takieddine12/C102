package com.bottom.bottomapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BottomAdapter extends RecyclerView.Adapter<BottomAdapter.MyViewHolder> {
    private ArrayList<Model> arrayList;
    public BottomAdapter(ArrayList<Model> arrayList) {
        this.arrayList  = arrayList;
    }

    public OnSizeListener onSizeListener;
    public interface OnSizeListener {
        void onClicked(int height , int width);
    }
    public void onSized(OnSizeListener onSizeListener){
        this.onSizeListener = onSizeListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_sheet_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Model item = arrayList.get(position);
        holder.imageView.setImageResource(item.getImage());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onSizeListener != null){
                    int pos = holder.getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        onSizeListener.onClicked(v.getHeight(),v.getWidth());
                    }
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}