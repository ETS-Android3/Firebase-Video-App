package com.scrolkart.firebasevideos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class VideoActivity extends AppCompatActivity {


    FloatingActionButton btn_add_video;
    RecyclerView recyclerView;

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

        // recycler view
        recyclerView = findViewById(R.id.recyclerView_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // data fetching from firebase
        FirebaseRecyclerOptions<MediaObject> options = new FirebaseRecyclerOptions.Builder<MediaObject>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Videos"), MediaObject.class)
                .build();


        FirebaseRecyclerAdapter<MediaObject,ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<MediaObject, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull MediaObject model) {
                holder.prepareExoplayer(getApplication(),model.getTitle(), model.getUrl());
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_card_view,parent,false);
                return new ViewHolder(view);
            }
        };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}