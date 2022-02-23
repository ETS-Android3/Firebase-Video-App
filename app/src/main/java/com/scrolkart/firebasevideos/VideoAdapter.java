package com.scrolkart.firebasevideos;

import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    ArrayList<MediaObject> mediaObjects;

    public VideoAdapter(ArrayList<MediaObject> mediaObjects) {
        this.mediaObjects = mediaObjects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SetVideoData(mediaObjects.get(position));
    }

    @Override
    public int getItemCount() {
        return mediaObjects.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder {

        VideoView videoView;
        TextView tv_Title, tv_Description;
        ProgressBar progressBar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            videoView = itemView.findViewById(R.id.videoView);
            tv_Title = itemView.findViewById(R.id.textVideoTitle);
            tv_Description = itemView.findViewById(R.id.textVideoDescription);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        void SetVideoData(MediaObject mediaObject){
            Uri videoURI = Uri.parse(mediaObject.getUrl());
            videoView.setVideoURI(videoURI);
            tv_Title.setText(mediaObject.getTitle());
            tv_Description.setText(mediaObject.getTimeStamp());

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    progressBar.setVisibility(View.INVISIBLE);
                    mediaPlayer.start();
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        }
    }


}
