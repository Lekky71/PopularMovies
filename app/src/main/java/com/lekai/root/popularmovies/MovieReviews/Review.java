package com.lekai.root.popularmovies.MovieReviews;

/**
 * Created by root on 5/2/17.
 */


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("url")
    @Expose
    private String url;
    public final static Parcelable.Creator<Review> CREATOR = new Creator<Review>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Review createFromParcel(Parcel in) {
            Review instance = new Review();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.author = ((String) in.readValue((String.class.getClassLoader())));
            instance.content = ((String) in.readValue((String.class.getClassLoader())));
            instance.url = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Review[] newArray(int size) {
            return (new Review[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7998987731281859677L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Review() {
    }

    /**
     *
     * @param content
     * @param id
     * @param author
     * @param url
     */
    public Review(String id, String author, String content, String url) {
        super();
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(author);
        dest.writeValue(content);
        dest.writeValue(url);
    }

    public int describeContents() {
        return 0;
    }

}