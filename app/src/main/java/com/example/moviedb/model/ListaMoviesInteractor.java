package com.example.moviedb.model;

import android.util.Log;

import com.example.moviedb.Interface.MovieDBService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaMoviesInteractor {

    private static final String TAG = "ListaMoviesInteractor";
    private static final String urlBase = "https://api.themoviedb.org/3/";
    private static final String urlMoviePopular = "784785859db664fa7ffaba0f36d48c7d";

    public interface onDetailsFetched{
        void onSuccess(ArrayList<Results> results);
        void onFailure();
        void noResults();
    }

    public void getMovies(final  onDetailsFetched listener){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieDBService movieDBService = retrofit.create(MovieDBService.class);
        Call<Movie> movieCall = movieDBService.getPopularMovies(urlMoviePopular);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(!response.isSuccessful()){
                    listener.onFailure();
                    return;
                }
                Movie contenido = response.body();
                if(contenido != null){
                    List<Results> listProducts = contenido.getResults();
                    if(listProducts.size() > 0){
                        ArrayList<Results> lista = new ArrayList<Results>(listProducts);
                        listener.onSuccess(lista);
                    }else{
                        listener.noResults();
                    }

                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                listener.onFailure();
                Log.e(TAG,"onFailure" + t.getMessage());
            }
        });
    }
}
