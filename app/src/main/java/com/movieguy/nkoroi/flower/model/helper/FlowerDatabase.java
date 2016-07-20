/*
 * Copyright (c) 2016 Eric Nkoroi
 */

package com.movieguy.nkoroi.flower.model.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.movieguy.nkoroi.flower.model.Flower;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nkoroi on 7/17/2016.
 */
public class FlowerDatabase extends SQLiteOpenHelper  {

    private static final String TAG = FlowerDatabase.class.getSimpleName();

    public FlowerDatabase(Context context) {
        super(context, Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY);
            Log.d(TAG, "Table created: " + Constants.DATABASE.CREATE_TABLE_QUERY);
        }catch(SQLiteException ex){
            Log.d(TAG, "Table erroe: "+ex.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DATABASE.DROP_QUERY);


    }

    public void addFlower(Flower flower){

        Log.d(TAG, "Values Got " + flower.getName());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DATABASE.PRODUCT_ID, flower.getProductId());
        values.put(Constants.DATABASE.CATEGORY, flower.getCategory());
        values.put(Constants.DATABASE.PRICE, Double.toString(flower.getPrice()));
        values.put(Constants.DATABASE.INSTRUCTIONS, flower.getInstructions());
        values.put(Constants.DATABASE.NAME, flower.getName());
        values.put(Constants.DATABASE.PHOTO_URL, flower.getPhoto());
        values.put(Constants.DATABASE.PHOTO, Utils.getPictureByteOfArray(flower.getPicture()));

        try {
            db.insert(Constants.DATABASE.TABLE_NAME, null, values);
        }catch(Exception e){
            Log.d(TAG, "Insert failed: "+e.getMessage());
        }
        db.close();

    }

    public List<Flower> getFlower(){
        SQLiteDatabase db = getReadableDatabase();
        List<Flower> flowerList = new ArrayList<>();
        Flower flower = new Flower();
        Cursor cursor = db.rawQuery(Constants.DATABASE.GET_FLOWERS_QUERY,null);
        int i = 0;
        if(cursor.getCount() > 0){
            if(cursor.moveToFirst()){
                do {
                    FlowerCursorWrapper flowerCursorWrapper = new FlowerCursorWrapper(cursor);
                    i = i + 1;
                    Log.d(TAG, "Database getFlower: " + i +" " + cursor.getString(cursor.getColumnIndex(Constants.DATABASE.PHOTO_URL)));
                    flowerList.add(flowerCursorWrapper.getFlower());
                }while (cursor.moveToNext());
            }

        }
        return flowerList;

    }


}
