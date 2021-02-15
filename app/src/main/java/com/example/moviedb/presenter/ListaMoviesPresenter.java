package com.example.moviedb.presenter;

import androidx.annotation.NonNull;

import com.example.moviedb.Interface.ListaMoviesView;
import com.example.moviedb.base.BasePresenter;
import com.example.moviedb.model.ListaMoviesInteractor;
import com.example.moviedb.model.Results;

import java.util.ArrayList;

public class ListaMoviesPresenter extends BasePresenter implements ListaMoviesInteractor.onDetailsFetched {

    private ListaMoviesView listaMoviesView;
    private ListaMoviesInteractor moviesInteractor;

    public ListaMoviesPresenter(@NonNull ListaMoviesView listaMoviesView,@NonNull ListaMoviesInteractor moviesInteractor) {
        this.listaMoviesView = listaMoviesView;
        this.moviesInteractor = moviesInteractor;
    }

    public void cargarDatos(){
        moviesInteractor.getMovies(this);
    }

    @Override
    public void onSuccess(ArrayList<Results> results) {
        listaMoviesView.cargarRecylcer(results);
        listaMoviesView.hideReload();
    }

    @Override
    public void onFailure() {
        listaMoviesView.mostrarError();
        listaMoviesView.showReload();
    }

    @Override
    public void noResults() {
        listaMoviesView.noHayResultados();
    }
}
