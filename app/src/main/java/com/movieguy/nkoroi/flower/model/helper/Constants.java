/*
 * Copyright (c) 2016 Eric Nkoroi
 */

package com.movieguy.nkoroi.flower.model.helper;

/**
 * Created by Nkoroi on 7/16/2016.
 */
public class Constants {

    public static final class HTTP {
        public static final String BASE_URL = "http://services.hanselandpetal.com";
    }

    public static final class DATABASE {

        public static final String DB_NAME = "flowers";
        public static final int DB_VERSION = 1;
        public static final String TABLE_NAME = "flower_table";

        public static final String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String GET_FLOWERS_QUERY = "SELECT * FROM " + TABLE_NAME;

        public static final String PRODUCT_ID = "productId";
        public static final String CATEGORY = "category";
        public static final String PRICE = "price";
        public static final String INSTRUCTIONS = "instructions";
        public static final String NAME = "names";
        public static final String PHOTO_URL = "photo_url";
        public static final String PHOTO = "photo";
        public static final String ID = "id";


        public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME +
                "("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PRODUCT_ID + " INTEGER not null UNIQUE," +
                CATEGORY + " TEXT not null," +
                PRICE + " TEXT not null," +
                INSTRUCTIONS + " TEXT not null," +
                NAME + " TEXT not null," +
                PHOTO_URL + " TEXT not null," +
                PHOTO + " BLOB not null)";
    }




    public static final class REFERENCE {
        public static final String FLOWER = Config.PACKAGE_NAME + "flower";
    }

    public static final class Config {
        public static final String PACKAGE_NAME = "com.movieguy.nkoroi";
    }
}
