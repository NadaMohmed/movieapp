package com.example.ecommerce.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static final String SMALL_POSTER_URL = "https://image.tmdb.org/t/p/w200";
    private List<Movie> results;
    private Context context;


    public MovieAdapter(Context context, List<Movie> results) {
        this.context = context;
        this.results = results;

    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {

        // Loading img from Internet with picasso
        Picasso.get()
                .load(SMALL_POSTER_URL + results.get(position).getImageurl())
                .into(holder.thumbImg);


        holder.thumbImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("Movie", results.get(position));
              /*  intent.putExtra ("name",results.get(position));
                intent.putExtra("hi",results.get(position));*/

                context.startActivity(intent);
            }
        });

    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setFavs(List<Movie> results) {
        this.results = results;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbImg;
        RelativeLayout parentLayout;

        MovieViewHolder(View itemView) {
            super(itemView);

            thumbImg = itemView.findViewById(R.id.siv);
            //parentLayout = itemView.findViewById(R.id.movie_parent_layout);

        }

    }

}
