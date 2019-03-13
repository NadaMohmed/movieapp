package com.example.ecommerce.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageGridAdapter extends RecyclerView.Adapter<ImageGridAdapter.GridItemViewHolder> {

    private List<String> imageList;
    private Context c;

    public List<String> getlist ()

    {
        return  imageList ;
    }

    public class GridItemViewHolder extends RecyclerView.ViewHolder {
        ImageView siv;

        public GridItemViewHolder(View view) {
            super(view);
            siv =(ImageView)  view.findViewById(R.id.siv);
        }
    }

    public ImageGridAdapter(Context c, List<String> imageList) {
        this.c = c;
       this.imageList =imageList ;
    }

   /* public ImageGridAdapter(Context c, List imageList) {
        this.c = c;

        this.imageList = imageList;
    }*/
   /*public void updateadapter (List<String> list)
   {
       this.imageList.clear();
       this.imageList.addAll(list);
       this.notifyDataSetChanged();
   }
*/

    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        //GridItemViewHolder viewHolder = new GridItemViewHolder(itemView) ;

        return new GridItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GridItemViewHolder holder, final int position) {
        final String path = imageList.get(position);
    /*   final Movie movie = movielist.get(position) ;
        final String id  = movie.getId() ;*/
        Log.i("info",path) ;

        Picasso.get()
                .load(path)
                .into(holder.siv);

        holder.siv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle click event on image
                Intent i = new Intent(c,DetailsActivity.class) ;
                Log.i("pos",String.valueOf( position));
                i.putExtra("position",position) ;
                i.putExtra("pos",imageList.get(position)) ;
                c.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        Log.i("inf",String.valueOf(imageList.size())) ;
        return imageList.size();

    }
    public void setfav(List<String> list)
    {
 this.imageList = list ;
    }

}