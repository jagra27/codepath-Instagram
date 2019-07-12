package com.example.instagram;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.instagram.model.Post;

public class PostDetailActivity extends AppCompatActivity {

    Post post;
    String imageURL;
    String des;
    String timestamp;
    TextView tvTime;
    TextView description;
    ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        tvTime = (TextView) findViewById(R.id.tvTime);
        description = (TextView) findViewById(R.id.tvDescription);
        photo = (ImageView) findViewById(R.id.ivPhoto);


        des = getIntent().getExtras().getString("description");
        timestamp = getIntent().getExtras().getString("time");
        imageURL = getIntent().getExtras().getString("photo");

        tvTime.setText(timestamp);
        description.setText(des);
        Glide.with(this).load(imageURL).into(photo);
    }
}
