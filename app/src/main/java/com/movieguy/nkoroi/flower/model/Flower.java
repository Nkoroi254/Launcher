package com.movieguy.nkoroi.flower.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.google.gson.annotations.Expose;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Nkoroi on 7/16/2016.
 */
public class Flower implements Serializable {
    @Expose
    private String category;
    @Expose
    private double price;
    @Expose
    private String instructions;
    @Expose
    private String photo;
    @Expose
    private String name;
    @Expose
    private int productId;

    private Bitmap picture;

    private boolean isFromDatabase;

    public boolean isFromDatabase() {
        return isFromDatabase;
    }

    public void setFromDatabase(boolean fromDatabase) {
        isFromDatabase = fromDatabase;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Bitmap getPicture() {
        return picture;
    }

    private void writeObject(ObjectOutputStream out) throws  IOException{
        out.writeObject(category);
        out.writeObject(price);
        out.writeObject(instructions);
        out.writeObject(photo);
        out.writeObject(name);
        out.writeObject(productId);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        picture.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        byte bitmapBytes[] = byteArrayOutputStream.toByteArray();
        out.write(bitmapBytes, 0, bitmapBytes.length);
    }

    private void readObject(ObjectInputStream in) throws  IOException, ClassNotFoundException{
        category = (String) in.readObject();
        price = (Double)in.readObject();
        instructions = (String)in.readObject();
        photo = (String)in.readObject();
        name = (String)in.readObject();
        productId = (Integer) in.readObject();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int b;
        while((b = in.read()) != -1)
            byteArrayOutputStream.write(b);
        byte bitmapBytes[] = byteArrayOutputStream.toByteArray();
        picture = BitmapFactory.decodeByteArray(bitmapBytes,0,bitmapBytes.length);

    }
}