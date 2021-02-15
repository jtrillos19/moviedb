package com.example.moviedb.Interface;

import com.example.moviedb.model.Results;

import java.util.ArrayList;

public interface ListaMoviesView {
    void getMovies();

    void cargarRecylcer(ArrayList<Results> results);

    void mostrarError();

    void noHayResultados();

    void hideReload();

    void showReload();
}
