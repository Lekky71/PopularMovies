package com.lekai.root.popularmovies.MovieReviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lekai.root.popularmovies.R;

import java.util.ArrayList;

/**
 * Created by root on 5/2/17.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    Context context;
    ArrayList <Review> reviews;
    public ReviewAdapter(ArrayList <Review> myReviews,Context myContext){
        reviews = myReviews;
        context = myContext;
    }
    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.each_review_item,parent,false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        String authorName = reviews.get(position).getAuthor().toString();
        String content =    reviews.get(position).getContent();
        holder.reviewAuthor.setText(authorName);
        holder.reviewContent.setText(content);

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView reviewAuthor;
        TextView reviewContent;
        public ReviewViewHolder(View itemView) {
            super(itemView);
            reviewAuthor = (TextView)  itemView.findViewById(R.id.review_author);
            reviewContent = (TextView) itemView.findViewById(R.id.review_content);
        }
    }
}
