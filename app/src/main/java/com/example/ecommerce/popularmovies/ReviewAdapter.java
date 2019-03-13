package com.example.ecommerce.popularmovies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder>
{

    private List<String> mReview;


    public ReviewAdapter(List<String> reviews) {

        this.mReview = reviews;

    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewHolder holder, final int position) {

        String review  = "Review :" + String.valueOf(position+1) ;
        holder.name.setText(review);

        holder.content.setText(mReview.get(position));
          }

    @Override
    public int getItemCount() {
        return mReview.size();
    }

    static class ReviewHolder extends RecyclerView.ViewHolder {

        TextView name, content;


        ReviewHolder(@NonNull View itemView) {
            super(itemView);

            content = itemView.findViewById(R.id.review_content);
            name = itemView.findViewById(R.id.review) ;
        }
    }



}
