/*
 * Copyright (c) 2016 Eric Nkoroi
 */

package com.movieguy.nkoroi.flower.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Interpolator;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.animation.OvershootInterpolator;

import com.movieguy.nkoroi.flower.R;
import com.movieguy.nkoroi.flower.controller.RestManager;
import com.movieguy.nkoroi.flower.model.Flower;
import com.movieguy.nkoroi.flower.model.adapter.FlowerAdapter;
import com.movieguy.nkoroi.flower.model.helper.Constants;
import com.movieguy.nkoroi.flower.model.helper.FlowerDatabase;
import com.movieguy.nkoroi.flower.model.helper.Utils;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity  implements FlowerAdapter.FlowerClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RestManager restManager;
    private FlowerAdapter adapter;
    private DefaultItemAnimator itemAnimator;
    private FlowerDatabase flowerDatabase;
    private List<Flower> flowerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configViews();

        restManager = new RestManager();

        //network
        if(Utils.isNetworkAvailable(getApplicationContext())){
            getFeed();
        }else{
            getFeedFromDatabase();
        }


    }

    private void getFeedFromDatabase() {
        List<Flower> flowerListFromDb = new ArrayList<>();
        flowerListFromDb = flowerDatabase.getFlower();
        for (int i = 0; i < flowerListFromDb.size(); i++){
            Flower flower = flowerListFromDb.get(i);
            Log.d(TAG, "Name from DB: " + flower.getPhoto());
            adapter.addFlower(flower, i);
        }
    }

    private void getFeed() {
        Call<List<Flower>> call =  restManager.getFlowerService().getAllFlowers();
        call.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {


                if(response.isSuccessful()){
                    flowerList = response.body();
                    for (int i = 0; i < flowerList.size(); i++){
                        Flower flower = flowerList.get(i);

                        SaveIntoDb saveIntoDb = new SaveIntoDb();
                        saveIntoDb.execute(flower);
                        Log.d(TAG, "flower name getFeed: "+flower.getName());
                        adapter.addFlower(flower, i);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {

            }
        });
    }

    private void configViews(){
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FlowerAdapter(this);
        flowerDatabase = new FlowerDatabase(this);

        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(int position) {
        Flower selectedFlower = adapter.getSelectedFlower(position);

        Log.d(TAG, "Before Serialize: " + selectedFlower.getName());
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(Constants.REFERENCE.FLOWER, selectedFlower);
        startActivity(intent);

    }

    public class SaveIntoDb extends AsyncTask<Flower, Flower , Boolean>{
        private final String TAG = SaveIntoDb.class.getSimpleName();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Flower... params) {

            Flower flower = params[0];

            try{
                InputStream stream = new URL(Constants.HTTP.BASE_URL + "/photos/" + flower.getPhoto()).openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                flower.setPicture(bitmap);
                publishProgress(flower);

            }catch (Exception e){
                Log.d(TAG, e.getMessage());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Flower... values) {
            super.onProgressUpdate(values);
            flowerDatabase.addFlower(values[0]);


        }


    }
}
