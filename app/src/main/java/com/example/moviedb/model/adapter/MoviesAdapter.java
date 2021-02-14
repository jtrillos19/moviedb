package com.example.moviedb.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.moviedb.R;
import com.example.moviedb.model.Results;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private ArrayList<Results> movieList;
    private Context context;

    public MoviesAdapter(ArrayList<Results> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Results movieModel = movieList.get(position);
        holder.tvNombreMovie.setText(movieModel.getTitle());
        holder.urlImagen = movieModel.getPosterPath();

        String urlImagenProcesada = "https://image.tmdb.org/t/p/w500" + holder.urlImagen;
        Glide.with(context)
                .load(urlImagenProcesada)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivMovie);

    }

    @Override
    public int getItemCount() {
        if (movieList.size() > 0) {
            return movieList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombreMovie;
        private ImageView ivMovie;
        private String urlImagen;
        public View view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            tvNombreMovie = itemView.findViewById(R.id.tvNombreMovie);
            ivMovie = itemView.findViewById(R.id.ivMovie);
        }
    }
}
