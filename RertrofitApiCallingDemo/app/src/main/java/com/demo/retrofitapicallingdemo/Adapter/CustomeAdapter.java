package com.demo.retrofitapicallingdemo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.demo.retrofitapicallingdemo.Model.ModelClass;
import com.demo.retrofitapicallingdemo.R;

import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.CustomeViewHolder>{

    private ArrayList<ModelClass> dataModelClassArrayList = new ArrayList<>();
    ModelClass dataModelClass = new ModelClass();
    private Context context;

    public CustomeAdapter(ArrayList<ModelClass> dataModelClassArrayList, ModelClass dataModelClass, Context context){
        this.dataModelClassArrayList = dataModelClassArrayList;
        this.dataModelClass = dataModelClass;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomeAdapter.CustomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler, parent, false);
        return new CustomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomeAdapter.CustomeViewHolder holder, int position) {
        dataModelClass = dataModelClassArrayList.get(position);

        String name = dataModelClass.getName();
        Log.d("CustomeAdapter","Getting name "+name);
        Glide.with(context)
                .load(dataModelClass.getImageurl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataModelClassArrayList.size();
    }

    public class CustomeViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name, txt_real_name;
        public ImageView imageView;

        public CustomeViewHolder(View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_name);
            txt_real_name = itemView.findViewById(R.id.txt_real_name);
            imageView = itemView.findViewById(R.id.imgView);
        }
    }
}
