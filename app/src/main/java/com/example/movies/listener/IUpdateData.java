package com.example.movies.listener;

import com.example.movies.model.MovieObject;
import java.util.List;

public interface IUpdateData {
    void update(List<MovieObject.Movie> movies,String type);
    void updateTitle();
}
