package com.example.ecommerce.popularmovies;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


@Entity
public class Movie implements Parcelable {



    @PrimaryKey
    @NonNull
    private  String id ;

    private  String title ;

    private   String relesedate ;

    private  String vote_average ;

    private  String imageurl ;

    private  String overview ;


public Movie(String title , String relesedate, String vote_average , String imageurl,String overview,String id)
{
    this.title = title ;
    this.relesedate = relesedate ;
    this.vote_average = vote_average ;
    this.imageurl = imageurl ;
    this.overview = overview ;
    this.id = id;
}
@Ignore
    public Movie(String title , String relesedate, String vote_average , String imageurl,String overview)
    {
        this.title = title ;
        this.relesedate = relesedate ;
        this.vote_average = vote_average ;
        this.imageurl = imageurl ;
        this.overview = overview ;

    }

    protected Movie(Parcel in) {
        id = in.readString();
        title = in.readString();
        relesedate = in.readString();
        vote_average = in.readString();
        imageurl = in.readString();
        overview = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelesedate(String relesedate) {
        this.relesedate = relesedate;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public String getRelesedate() {
        return relesedate;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(relesedate);
        parcel.writeString(vote_average);
        parcel.writeString(imageurl);
        parcel.writeString(overview);
    }
}
