package com.example.movies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.api.APIGetData;
import com.example.movies.databinding.LayoutItemFilmBinding;
import com.example.movies.listener.IRecommendationsClickListener;
import com.example.movies.model.MovieObject;
import com.example.movies.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieAdapterMovieID extends RecyclerView.Adapter<MovieAdapterMovieID.ViewHolder> {

    List<MovieObject.Movie> moviesList;
    MovieObject.Movie movieOriginal;
    IRecommendationsClickListener iRecommendationsClickListener;
    int page = 1;
    String type;

    public MovieAdapterMovieID(MovieObject.Movie movie, IRecommendationsClickListener iRecommendationsClickListener, String type){
        this.moviesList = new ArrayList<>();
        this.movieOriginal = movie;
        this.iRecommendationsClickListener = iRecommendationsClickListener;
        this.type = type;
    }

    public void setMoviesList(List<MovieObject.Movie> moviesRecommendations){
        this.moviesList = moviesRecommendations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemFilmBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_item_film,parent,false);
        return new ViewHolder(binding,iRecommendationsClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieObject.Movie movie = moviesList.get(position);
        holder.binding.setItemFilm(movie);
        holder.setMovie(movie);
        if(position == moviesList.size() - 3){
            new Thread(() -> fetchMovies(movieOriginal.getId())).start();
        }
    }

    public synchronized void fetchMovies(String id){
        APIGetData.apiGetData.getMoviesByIdAndTitle(id,type, Utils.API_MOVIE_KEY, String.valueOf(++page)).enqueue(new Callback<MovieObject>() {
            @Override
            public void onResponse(@NonNull Call<MovieObject> call, @NonNull Response<MovieObject> response) {
                assert response.body() != null;

                assert response.body() != null;
                int size = moviesList.size();
                List<MovieObject.Movie> movies = response.body().getMoviesList();
                moviesList.addAll(movies);
                notifyItemRangeInserted(size, moviesList.size());
            }

            @Override
            public void onFailure(@NonNull Call<MovieObject> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        LayoutItemFilmBinding binding;
        IRecommendationsClickListener iRecommendationsClickListener;
        MovieObject.Movie movie;

        public ViewHolder(@NonNull LayoutItemFilmBinding binding, IRecommendationsClickListener iRecommendationsClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.iRecommendationsClickListener = iRecommendationsClickListener;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iRecommendationsClickListener.onRecommendationItemClick(movie);
        }

        public void setMovie(MovieObject.Movie movie){
            this.movie = movie;
        }
    }
}
