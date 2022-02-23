package com.scrolkart.firebasevideos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {


    FloatingActionButton btn_add_video;
    ViewPager2 viewPager2;
    ArrayList<MediaObject> mediaObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); // hide status bar

        // Action bar Title
        setTitle("FireBase Videos");

        viewPager2 = (ViewPager2)findViewById(R.id.viewPager);
        mediaObjects = new ArrayList<>();

        /*DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Videos");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mediaObjects.clear();
                for(DataSnapshot data : snapshot.getChildren()){
                    MediaObject object = data.getValue(MediaObject.class);
                    mediaObjects.add(object);
                    System.out.println(object.getTitle());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };*/

        MediaObject object1 = new MediaObject("1","Big Buck Bunny","Sample Description 1","001","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        MediaObject object2 = new MediaObject("2","Elephant Dream","Sample Description 2","002","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4");
        MediaObject object3 = new MediaObject("3","For Bigger Blazes","Sample Description 3","003","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4");
        MediaObject object4 = new MediaObject("4","For Bigger Escape","Sample Description 4","004","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4");
        MediaObject object5 = new MediaObject("5","For Bigger Fun","Sample Description 5","005","https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4");
        mediaObjects.add(object1);
        mediaObjects.add(object2);
        mediaObjects.add(object3);
        mediaObjects.add(object4);
        mediaObjects.add(object5);

        viewPager2.setAdapter(new VideoAdapter(mediaObjects));


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