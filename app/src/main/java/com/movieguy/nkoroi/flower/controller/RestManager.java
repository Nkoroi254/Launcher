/*
 * Copyright (c) 2016 Eric Nkoroi
 */

package com.movieguy.nkoroi.flower.controller;

import com.movieguy.nkoroi.flower.model.Flower;
import com.movieguy.nkoroi.flower.model.callback.FlowerService;
import com.movieguy.nkoroi.flower.model.helper.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nkoroi on 7/16/2016.
 */
public class RestManager {

    private FlowerService mFlowerService;

    public FlowerService getFlowerService(){
        if(mFlowerService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HTTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mFlowerService = retrofit.create(FlowerService.class);
        }
        return mFlowerService;
    }
}
