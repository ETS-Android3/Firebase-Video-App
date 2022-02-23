package com.scrolkart.firebasevideos;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView tv_videoTitle;
    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer simpleExoPlayer;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    void prepareExoplayer(Application application,String videoTitle,String videoURL){

        tv_videoTitle = itemView.findViewById(R.id.videoTitle);
        simpleExoPlayerView = itemView.findViewById((R.id.videoView_card));

        tv_videoTitle.setText(videoTitle);

        try{

            BandwidthMeter bandwidthMeter= new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            simpleExoPlayer = (SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(application,trackSelector);

            Uri videoURI = Uri.parse(videoURL);

            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoURI,dataSourceFactory,extractorsFactory,null,null);

            simpleExoPlayerView.setPlayer(simpleExoPlayer);
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);

        }
        catch (Exception e){
            Log.d(TAG, "prepareExoplayer: Exoplayer crashed");
        }
    }
}
