/*
 * Copyright (c) 2016 Eric Nkoroi
 */

package com.movieguy.nkoroi.flower.model.callback;

import com.movieguy.nkoroi.flower.model.Flower;

import java.util.List;

/**
 * Created by Nkoroi on 7/18/2016.
 */
public interface FlowerFetchListener {

    void onDeliverAllFlowers(List<Flower> flowers);

    void onDeliverFlower(Flower flower);

    void onHideDialog();
}