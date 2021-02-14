package com.example.moviedb.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.moviedb.Interface.ListaMoviesView;
import com.example.moviedb.R;
import com.example.moviedb.base.BaseActivity;
import com.example.moviedb.model.ListaMoviesInteractor;
import com.example.moviedb.model.Results;
import com.example.moviedb.model.adapter.MoviesAdapter;
import com.example.moviedb.presenter.ListaMoviesPresenter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity<ListaMoviesPresenter> implements ListaMoviesView {

    private MoviesAdapter moviesAdapter;
    private RecyclerView rvMovies;


    @NonNull
    @Override
    protected ListaMoviesPresenter createPresenter(@NonNull Context context) {
        return new ListaMoviesPresenter(this,new ListaMoviesInteractor());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMovies = findViewById(R.id.rvMovies);
        getMovies();
    }

    @Override
    public void getMovies() {
        mPresenter.cargarDatos();
    }

    @Override
    public void cargarRecylcer(ArrayList<Results> results) {
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        rvMovies.setLayoutManager(layoutManager);
        moviesAdapter = new MoviesAdapter(results,this);
        rvMovies.setAdapter(moviesAdapter);
    }

    @Override
    public void mostrarError() {
        Toast.makeText(this, "Error intentando cargar los datos ⛔", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noHayResultados() {
        Toast.makeText(this, "No hay datos ⛔", Toast.LENGTH_SHORT).show();
    }
}