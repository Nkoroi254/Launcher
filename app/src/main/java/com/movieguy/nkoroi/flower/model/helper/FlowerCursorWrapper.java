package com.movieguy.nkoroi.flower.model.helper;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.movieguy.nkoroi.flower.model.Flower;

import static com.movieguy.nkoroi.flower.model.helper.Utils.*;

/**
 * Created by nerd on 7/19/2016.
 */
public class FlowerCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public FlowerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Flower getFlower(){
        String productId = getString(getColumnIndex(Constants.DATABASE.PRODUCT_ID));
        String name = getString(getColumnIndex(Constants.DATABASE.NAME));
        String instruction = getString(getColumnIndex(Constants.DATABASE.INSTRUCTIONS));
        String price = getString(getColumnIndex(Constants.DATABASE.PRICE));
        String category = getString(getColumnIndex(Constants.DATABASE.CATEGORY));
        byte[] photo = getBlob(getColumnIndex(Constants.DATABASE.PHOTO));
        String photo_url = getString(getColumnIndex(Constants.DATABASE.PHOTO_URL));

        Flower flower = new Flower();
        flower.setProductId(Integer.parseInt(productId));
        flower.setName(name);
        flower.setInstructions(instruction);
        flower.setPrice(Double.parseDouble(price));
        flower.setCategory(category);
        flower.setPhoto(photo_url);
        flower.setPicture(Utils.getBitmapFromByte(photo));


        return flower;
    }
}
