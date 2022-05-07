package com.example.movies.adapter;

import static com.example.movies.activity.MainActivity.movieResources;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.databinding.LayoutItemFilmBinding;
import com.example.movies.model.MovieObject;
import com.example.movies.listener.IMovieItemClickListener;
import com.example.movies.R;
import com.example.movies.utils.Utils;

import java.util.List;

public class MoviesAdapterByGenres extends RecyclerView.Adapter<MoviesAdapterByGenres.ViewHolder>{

    int page = 1;
    public String type;
    public String title="";
    public List<MovieObject.Movie> movieList;
    private final IMovieItemClickListener singleItemClicked;


    //CONSTRUCTOR
    public MoviesAdapterByGenres(String type, List<MovieObject.Movie> items, IMovieItemClickListener itemClicked){
        this.type = type;
        this.movieList = items;
        this.singleItemClicked = itemClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemFilmBinding layoutItemFilmBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_item_film,parent,false);
        return new ViewHolder(layoutItemFilmBinding, movieList,singleItemClicked,type);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieObject.Movie item = movieList.get(position);
        holder.layoutItemFilmBinding.setItemFilm(item);
        if(position == movieList.size()-3){
            if(type.equals(Utils.latest) || type.equals(Utils.now_playing) || type.equals(Utils.popular) || type.equals(Utils.top_rated) || type.equals(Utils.upcoming)){
                page++;
                movieResources.getMoviesAPIAtPageIndex(type,page);
            }
            else{
                page++;
                movieResources.getAllMoviesByGenre(type, String.valueOf(page));
            }
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    //SET LIST MOVIE
    @SuppressLint("NotifyDataSetChanged")
    public void setMovieItemList(List<MovieObject.Movie> items){
        this.movieList = items;
        notifyDataSetChanged();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MovieObject.Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieObject.Movie> movieList) {
        this.movieList = movieList;
    }

    public void addMovieList(List<MovieObject.Movie> movieList){
        this.movieList.addAll(movieList);
    }

    //SET MOVIE
    public void setMovieItem(MovieObject.Movie item){
        for (int i = 0; i < movieList.size(); i++) {
            if(movieList.get(i).getId().equals(item.getId())){
                movieList.set(i,item);
                break;
            }
        }
    }


    //VIEW HOLDER
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LayoutItemFilmBinding layoutItemFilmBinding;
        List<MovieObject.Movie> movieList;
        IMovieItemClickListener singleItemClicked;
        String type;
        public ViewHolder(@NonNull LayoutItemFilmBinding layoutItemFilmBindingA, List<MovieObject.Movie> movieList, IMovieItemClickListener singleItemClicked, String type) {
            super(layoutItemFilmBindingA.getRoot());
            this.type = type;
            layoutItemFilmBinding = layoutItemFilmBindingA;
            this.movieList = movieList;
            this.singleItemClicked = singleItemClicked;
            layoutItemFilmBindingA.getRoot().setOnClickListener(this);
        }

        //ITEM CLICKED
        @Override
        public void onClick(View view) {
            singleItemClicked.itemClicked(movieList.get(getAbsoluteAdapterPosition()),type);
        }
    }
}
