/*
 * Copyright (c) 2016 Eric Nkoroi
 */

package com.movieguy.nkoroi.flower.model.callback;


import com.movieguy.nkoroi.flower.model.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nkoroi on 7/16/2016.
 */
public interface FlowerService {
    @GET("feeds/flowers.json")
    Call<List<Flower>> getAllFlowers();
}
