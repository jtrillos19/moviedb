package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.moviedb.R;

public class MovieDetalleActivity extends AppCompatActivity {

    private ImageView ivMovieDetalle;
    private TextView tvOverview,tvPopularity;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detalle);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ivMovieDetalle = findViewById(R.id.ivMovieDetalle);
        tvOverview = findViewById(R.id.tvOverview);
        tvPopularity = findViewById(R.id.tvPopularity);

        Bundle intent = getIntent().getExtras();
        String titulo = intent.getString("Titulo");
        String sinopsis = intent.getString("Overview");
        String popularity = intent.getString("popularity");
        String imagen = intent.getString("Imagen");
        String urlImagenProcesada = "https://image.tmdb.org/t/p/w300" + imagen;
        tvOverview.setText(sinopsis);
        tvPopularity.setText(popularity);
        getSupportActionBar().setTitle(titulo);
        Glide.with(this)
                .load(urlImagenProcesada)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivMovieDetalle);
    }
}