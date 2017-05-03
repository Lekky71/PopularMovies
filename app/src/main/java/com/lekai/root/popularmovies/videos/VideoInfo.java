package com.lekai.root.popularmovies.videos;

/**
 * Created by root on 5/2/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VideoInfo implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<Video> results = null;
    public final static Parcelable.Creator<VideoInfo> CREATOR = new Creator<VideoInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VideoInfo createFromParcel(Parcel in) {
            VideoInfo instance = new VideoInfo();
            instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.results, (Video.class.getClassLoader()));
            return instance;
        }

        public VideoInfo[] newArray(int size) {
            return (new VideoInfo[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7229544058017418554L;

    /**
     * No args constructor for use in serialization
     *
     */
    public VideoInfo() {
    }

    /**
     *
     * @param id
     * @param results
     */
    public VideoInfo(Integer id, List<Video> results) {
        super();
        this.id = id;
        this.results = results;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}
