package com.example.moviedb.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
    private Button btnRecagar;
    private ProgressBar progress_circular;


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
        btnRecagar = findViewById(R.id.btnRecagar);
        progress_circular = findViewById(R.id.progress_circular);
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

    @Override
    public void hideReload() {
        progress_circular.setVisibility(View.GONE);
        btnRecagar.setVisibility(View.GONE);
    }

    @Override
    public void showReload() {
        btnRecagar.setVisibility(View.VISIBLE);
        btnRecagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovies();
            }
        });
    }
}