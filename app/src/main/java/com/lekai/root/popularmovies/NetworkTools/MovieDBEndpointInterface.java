package com.lekai.root.popularmovies.NetworkTools;

import com.lekai.root.popularmovies.MovieReviews.ReviewInfo;
import com.lekai.root.popularmovies.movie.MoviesInfo;
import com.lekai.root.popularmovies.videos.VideoInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by root on 4/12/17.
 */

public interface MovieDBEndpointInterface {
    /*TODO Please,Change the API_Key here*/
    String API_KEY = "";
    @GET("3/movie/popular?api_key="+API_KEY)
    Call<MoviesInfo> getMoviesUsingPopularity();
    @GET("3/movie/top_rated?api_key="+API_KEY)
    Call <MoviesInfo> getMoviesUsingRatings();
    @GET("3/movie/{id}/videos?api_key=" +API_KEY)
    Call <VideoInfo> getAllVideos(@Path("id") String id);
    @GET("3/movie/{id}/reviews?api_key=" +API_KEY)
    Call <ReviewInfo> getAllReviews(@Path("id") String id);
}
