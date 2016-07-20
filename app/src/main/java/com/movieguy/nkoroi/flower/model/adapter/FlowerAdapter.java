/*
 * Copyright (c) 2016 Eric Nkoroi
 */

package com.movieguy.nkoroi.flower.model.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.movieguy.nkoroi.flower.R;
import com.movieguy.nkoroi.flower.model.Flower;
import com.movieguy.nkoroi.flower.model.helper.Constants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nkoroi on 7/16/2016.
 */
public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.FlowerHolder>  {
    private final FlowerClickListener mListener;
    private List<Flower> flowerlist;
    private Context mContext;

    private static final String TAG = FlowerAdapter.class.getSimpleName();

    public FlowerAdapter(FlowerClickListener listener){
        flowerlist = new ArrayList<>();
        mListener = listener;
    }
    @Override
    public FlowerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        mContext = parent.getContext();
        return new FlowerHolder(view);
    }

    @Override
    public void onBindViewHolder(FlowerHolder holder, int position) {

        holder.textView.setText(flowerlist.get(position).getName());
        holder.textView1.setText("$"+Double.toString(flowerlist.get(position).getPrice()));
        Log.d(TAG, Constants.HTTP.BASE_URL + "/photos/" + flowerlist.get(position).getPhoto());
        //context - holder.itemView.getContext
        Picasso.with(mContext).load(Constants.HTTP.BASE_URL + "/photos/" + flowerlist.get(position).getPhoto()).into(holder.imageView);
        holder.position = position;

    }

    /*
    *@param position
    * @return
     */
    @Override
    public int getItemCount() {
        return flowerlist.size();
    }

    public void addFlower(Flower flower, int i) {
        flowerlist.add(flower);
        notifyDataSetChanged();
    }

    public Flower getSelectedFlower(int position) {

        return flowerlist.get(position);
    }




    public class FlowerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView textView;
        private TextView textView1;

        private int position;
        public FlowerHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            textView = (TextView)itemView.findViewById(R.id.flowerName);
            textView1 = (TextView)itemView.findViewById(R.id.flowerPrice);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Position: " + position, Toast.LENGTH_LONG).show();
            mListener.onClick(getLayoutPosition());
        }
    }
    public interface FlowerClickListener{

        void onClick(int position);
    }
}
