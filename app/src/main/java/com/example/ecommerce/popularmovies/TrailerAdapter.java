package com.example.ecommerce.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter  extends RecyclerView.Adapter<TrailerAdapter.TrailerHolder> {

    private List<String> mTrailer;
    private Context mContext;


    class TrailerHolder extends RecyclerView.ViewHolder {

        //TextView trailerName;
        ImageView trailerImage;
        TextView text ;

        TrailerHolder(View itemView) {
            super(itemView);

            trailerImage = itemView.findViewById(R.id.trailer_sample_image);
            //trailerName = itemView.findViewById(R.id.trailer_sample_image);
            text = itemView.findViewById(R.id.text);

        }
    }


    public TrailerAdapter(List<String> trailers, Context context) {

        this.mTrailer = trailers;
        this.mContext = context;

    }

    @Override
    public TrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent, false);
        //GridItemViewHolder viewHolder = new GridItemViewHolder(itemView) ;

        return new TrailerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerHolder holder,final int position) {

        /*Picasso.get()
                .load("https://www.iconfinder.com/icons/2710884/forward_increase_speed_media_player_resume_icon")
            .into(holder.trailerImage);*/
       // holder.trailerImage.setImageDrawable(R.drawable.);
        holder.trailerImage.setImageResource(R.drawable.resumicon);
        String trailer  = "Trailer :" + String.valueOf(position+1) ;
        holder.text.setText(trailer);

        holder.trailerImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mTrailer.get(position)));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(intent);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return mTrailer.size();
    }
}
