package com.lekai.root.popularmovies.NetworkTools;

import com.lekai.root.popularmovies.movie.Movie;
import com.lekai.root.popularmovies.movie.MoviesInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by root on 4/12/17.
 */

public interface MovieDBEndpointInterface {
// http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=28ef9801379c99fb16dc16dc34bf9751
    @GET("3/movie/popular?api_key=28ef9801379c99fb16dc16dc34bf9751")
    Call<MoviesInfo> getMoviesUsingPopularity();
    // http://api.themoviedb.org/3/discover/movie/popular&api_key=28ef9801379c99fb16dc16dc34bf9751
    @GET("3/movie/top_rated?api_key=28ef9801379c99fb16dc16dc34bf9751")
    Call <MoviesInfo> getMoviesUsingRatings();
}
// http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=28ef9801379c99fb16dc16dc34bf9751
// http://api.themoviedb.org/3/movie/popular?api_key=28ef9801379c99fb16dc16dc34bf9751
