package com.example.moviedb.Interface;

import com.example.moviedb.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDBService {
    String API_ROUTE = "movie/popular";

    @GET(API_ROUTE)
    Call<Movie> getPopularMovies(@Query("api_key") String apiKey);

}
