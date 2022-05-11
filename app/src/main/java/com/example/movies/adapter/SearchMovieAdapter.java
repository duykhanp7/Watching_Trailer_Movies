package com.example.movies.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.api.APIGetData;
import com.example.movies.databinding.LayoutItemFilmBinding;
import com.example.movies.listener.IMovieItemClickListener;
import com.example.movies.model.MovieObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.ViewHolder> {

    public List<MovieObject.Movie> moviesSearch;
    public int page = 1;
    public String keyword;
    IMovieItemClickListener iMovieItemClickListener;

    public SearchMovieAdapter(IMovieItemClickListener iMovieItemClickListener){
        this.moviesSearch = new ArrayList<>();
        this.iMovieItemClickListener = iMovieItemClickListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMoviesSearch(List<MovieObject.Movie> movies){
        this.moviesSearch = movies;
        notifyDataSetChanged();
    }

    public void setKeyword(String keyword){
        this.keyword = keyword;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemFilmBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_item_film,parent,false);
        return new ViewHolder(binding,iMovieItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieObject.Movie movie = moviesSearch.get(position);
        holder.binding.setItemFilm(movie);
        holder.setMovie(movie);
        if(position == moviesSearch.size() - 3){
            APIGetData.apiGetData.getMovieByKeyword(keyword, String.valueOf(++page)).enqueue(new Callback<MovieObject>() {
                @Override
                public void onResponse(@NonNull Call<MovieObject> call, @NonNull Response<MovieObject> response) {
                    moviesSearch.addAll(Objects.requireNonNull(response.body()).getMoviesList());
                }

                @Override
                public void onFailure(@NonNull Call<MovieObject> call, @NonNull Throwable t) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return moviesSearch.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        LayoutItemFilmBinding binding;
        MovieObject.Movie movie;
        IMovieItemClickListener iMovieItemClickListener;

        public ViewHolder(@NonNull LayoutItemFilmBinding itemView,IMovieItemClickListener iMovieItemClickListener) {
            super(itemView.getRoot());
            this.binding = itemView;
            this.iMovieItemClickListener = iMovieItemClickListener;
            itemView.getRoot().setOnClickListener(this);
        }

        public void setMovie(MovieObject.Movie movie){
            this.movie = movie;
        }

        @Override
        public void onClick(View view) {
            iMovieItemClickListener.itemClicked(movie);
        }
    }
}
