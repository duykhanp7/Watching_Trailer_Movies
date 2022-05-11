package com.example.movies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.databinding.LayoutItemFilmDetailsBinding;
import com.example.movies.listener.IMovieItemByCastCrewIDClickListener;
import com.example.movies.model.MovieObject;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapterByIDOfCastCrew extends RecyclerView.Adapter<MovieAdapterByIDOfCastCrew.ViewHolder> {

    List<MovieObject.Movie> moviesByCastOrCrewID;
    IMovieItemByCastCrewIDClickListener iMovieItemByCastCrewIDClickListener;

    public MovieAdapterByIDOfCastCrew(List<MovieObject.Movie> movies, IMovieItemByCastCrewIDClickListener iMovieItemByCastCrewIDClickListener){
        moviesByCastOrCrewID = new ArrayList<>();
        this.moviesByCastOrCrewID = movies;
        this.iMovieItemByCastCrewIDClickListener = iMovieItemByCastCrewIDClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemFilmDetailsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_item_film_details,parent,false);
        return new ViewHolder(binding, iMovieItemByCastCrewIDClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieObject.Movie movie = moviesByCastOrCrewID.get(position);
        holder.binding.setItem(movie);
        holder.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return moviesByCastOrCrewID.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        LayoutItemFilmDetailsBinding binding;
        MovieObject.Movie movie;
        IMovieItemByCastCrewIDClickListener iMovieItemByCastCrewIDClickListener;

        public ViewHolder(@NonNull LayoutItemFilmDetailsBinding binding, IMovieItemByCastCrewIDClickListener iMovieItemByCastCrewIDClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.iMovieItemByCastCrewIDClickListener = iMovieItemByCastCrewIDClickListener;
            binding.getRoot().setOnClickListener(this);
        }

        public void setMovie(MovieObject.Movie movie){
            this.movie = movie;
        }

        @Override
        public void onClick(View view) {
            iMovieItemByCastCrewIDClickListener.onItemClicked(movie);
        }
    }
}
