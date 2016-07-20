/*
 * Copyright (c) 2016 Eric Nkoroi
 */

package com.movieguy.nkoroi.flower.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.movieguy.nkoroi.flower.R;
import com.movieguy.nkoroi.flower.model.Flower;
import com.movieguy.nkoroi.flower.model.helper.Constants;
import com.squareup.picasso.Picasso;

import static com.movieguy.nkoroi.flower.model.helper.Constants.REFERENCE.FLOWER;

public class DetailActivity extends AppCompatActivity {

    private TextView mName;
    private TextView mId;
    private TextView mCategory;
    private TextView mPrice;
    private TextView mInstruction;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mName = (TextView)findViewById(R.id.valueName);
        mCategory = (TextView)findViewById(R.id.valueCategory);
        mId = (TextView)findViewById(R.id.valueId);
        mInstruction = (TextView)findViewById(R.id.valueInstruction);
        mPrice = (TextView)findViewById(R.id.valuePrice);
        imageView = (ImageView)findViewById(R.id.image);

        Intent intent = getIntent();
        Flower flower = (Flower) intent.getSerializableExtra(Constants.REFERENCE.FLOWER);
        mName.setText(": "+ flower.getName());
        mCategory.setText(": "+ flower.getCategory());
        mPrice.setText(": "+ Double.toString(flower.getPrice()));
        mInstruction.setText(": "+ flower.getInstructions());
        mId.setText(": " + Integer.toString(flower.getProductId()));
        if(flower.isFromDatabase()) {
           imageView.setImageBitmap(flower.getPicture());
        }else{
            Picasso.with(getApplicationContext()).load(Constants.HTTP.BASE_URL + "/photos/" + flower.getPhoto()).into(imageView);
        }
    }
}
