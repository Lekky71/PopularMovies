package com.lekai.root.popularmovies.NetworkTools;

import com.lekai.root.popularmovies.movie.MoviesInfo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by root on 4/12/17.
 */

public interface MovieDBEndpointInterface {
    /*TODO Please,Change the API_Key here*/
    final String API_KEY = "";
    @GET("3/movie/popular?api_key="+API_KEY)
    Call<MoviesInfo> getMoviesUsingPopularity();
    @GET("3/movie/top_rated?api_key="+API_KEY)
    Call <MoviesInfo> getMoviesUsingRatings();
}
