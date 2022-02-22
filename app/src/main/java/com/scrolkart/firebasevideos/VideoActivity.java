package com.scrolkart.firebasevideos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VideoActivity extends AppCompatActivity {


    FloatingActionButton btn_add_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // Action bar Title
        setTitle("FireBase Videos");

        // Upload video button
        btn_add_video = findViewById(R.id.btn_add_video);

        btn_add_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start new video add activity
                startActivity(new Intent(VideoActivity.this,AddVideoActivity.class));
            }
        });
    }
}