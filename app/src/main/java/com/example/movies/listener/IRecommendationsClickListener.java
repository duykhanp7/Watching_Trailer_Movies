package com.example.movies.listener;

import com.example.movies.model.MovieObject;

public interface IRecommendationsClickListener {
    void onRecommendationItemClick(MovieObject.Movie movie);
}
