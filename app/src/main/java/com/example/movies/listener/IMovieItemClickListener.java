package com.example.movies.listener;

import com.example.movies.model.MovieObject;

public interface IMovieItemClickListener {
    void itemClicked(MovieObject.Movie item,String type);
}
