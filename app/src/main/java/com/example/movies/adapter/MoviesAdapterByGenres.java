package com.example.movies.adapter;

import static com.example.movies.activity.MainActivity.movieResources;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import static com.example.movies.activity.DetailsMovieActivity.hadSearch;
import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.api.APIGetData;
import com.example.movies.databinding.LayoutItemFilmBinding;
import com.example.movies.model.MovieObject;
import com.example.movies.listener.IMovieItemClickListener;
import com.example.movies.R;
import com.example.movies.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesAdapterByGenres extends RecyclerView.Adapter<MoviesAdapterByGenres.ViewHolder> implements Filterable {

    public int page = 1;
    public String type;
    public  String title="";
    public List<MovieObject.Movie> movieList;
    public List<MovieObject.Movie> oldMovieList;
    private IMovieItemClickListener singleItemClicked;


    //CONSTRUCTOR
    public MoviesAdapterByGenres(String type, List<MovieObject.Movie> items, IMovieItemClickListener itemClicked){
        this.type = type;
        this.movieList = items;
        this.oldMovieList = items;
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
            Log.i("AAA","NOT FIND MOVIESSSSSSSSSSSSSSSSs");
            page++;
            if(type.equals(Utils.latest) || type.equals(Utils.now_playing) || type.equals(Utils.popular) || type.equals(Utils.top_rated) || type.equals(Utils.upcoming)){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        movieResources.getMoviesAPIAtPageIndex(type,page);
                    }
                }).start();
            }
            else{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        movieResources.getAllMoviesByGenre(type, String.valueOf(page));
                    }
                }).start();
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
        this.oldMovieList.addAll(movieList);
    }

    public void setMovieListWithOldMovieList(){
        this.movieList = oldMovieList;
        notifyDataSetChanged();
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                hadSearch = true;
                String query = charSequence.toString().trim();
                if(query.isEmpty()){
                    Log.i("AAA","QUERYYYYYYYYYY EMPTYYYYYYYYYYYYYYYYYYYY");
                    movieList = oldMovieList;
                }
                else{
                    List<MovieObject.Movie> moviesFilter;
                    Map<String, MovieObject.Movie> moviesMap = new HashMap<>();
                    //REPLACE ALL SPECIFIC CHARACTERS WITH SPACE, THEN REPLACE TWO OR MORE SPACE WITH ONE SPACE
                    query = query.replaceAll(Utils.SPECIFIC_CHARACTERS," ").replaceAll(Utils.REGEX_DOUBLE_SPACE," ");
                    String[] str = query.split(" ");
                    for (MovieObject.Movie a : oldMovieList){
                        for (String item : str){
                            Log.i("AAA","MOVIE NAME : "+a.getTitle().toLowerCase().trim() +" -- "+query.toLowerCase().trim()+" -- "+a.getTitle().toLowerCase().trim().equals(query.toLowerCase().trim()));
                            if(a.getTitle().toLowerCase().trim().contains(item.toLowerCase().trim()) || a.getOriginal_title().toLowerCase().trim().equals(item.toLowerCase().trim())){
                                moviesMap.put(a.getId(),a);
                            }
                        }
                    }
                    moviesFilter = new ArrayList<>(moviesMap.values());
                    Log.i("AAA","SIZE MATCH ITEMS : "+moviesFilter.size());

                    movieList = moviesFilter;
                }

                FilterResults results = new FilterResults();
                results.values = movieList;
                return results;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieList = (List<MovieObject.Movie>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
            singleItemClicked.itemClicked(movieList.get(getAbsoluteAdapterPosition()));
        }
    }

    public List<MovieObject.Movie> filterMovieByGenresID(String genresID, List<MovieObject.Movie> moviesNeedFilterByGenresID){
        List<MovieObject.Movie> afterFilterByGenresID = new ArrayList<>();
        Log.i("AAA","GENRES ID : "+genresID);
        for (MovieObject.Movie a : moviesNeedFilterByGenresID){
            if(a.getGenres().contains(genresID)){
                Log.i("AAA","MOVIE FILTER NAME : "+a.getTitle());
                afterFilterByGenresID.add(a);
            }
        }
        return afterFilterByGenresID;
    }
}
