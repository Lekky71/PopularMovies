package com.lekai.root.popularmovies.videos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lekai.root.popularmovies.R;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.OnClickListener;
import static android.support.v7.widget.RecyclerView.ViewHolder;

/**
 * Created by root on 5/2/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{
    ArrayList <Video> videos;
    Context context;

    public VideoAdapter(ArrayList <Video> myVideos, Context myContext){
        videos = myVideos;
        context = myContext;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.videoplay_view,parent,false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VideoViewHolder holder, int position) {
        String KEY = "SUXWAEX2jlg" ;
        KEY = videos.get(position).getKey().toString();
        String youtube_link = "https://www.youtube.com/watch?v="+KEY;
        final Uri trailer_uri = Uri.parse(youtube_link);
        int number = position + 1;
        holder.trailer_number.setText("Trailer "+number);
        holder.playButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,trailer_uri);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class VideoViewHolder extends ViewHolder {
        ImageView playButton ;
        TextView trailer_number;
        public VideoViewHolder(View itemView) {
            super(itemView);
            playButton = (ImageView) itemView.findViewById(R.id.play_trailer_button);
            trailer_number = (TextView) itemView.findViewById(R.id.trailer_number);
            playButton.setBackground(null);
        }
    }
}
