package com.example.movies.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.databinding.ItemRecyclerViewBinding;
import com.example.movies.model.MovieObject;
import com.example.movies.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> {

    public AdapterManager adapterManager;
    List<ItemRecyclerViewBinding> bindings = new ArrayList<>();
    public ParentAdapter(AdapterManager adapterManager){
        this.adapterManager = adapterManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerViewBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_recycler_view,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        bindings.add(holder.binding);
        MoviesAdapterByGenres item = adapterManager.adaptersObservableField.get(position).get();
        if(position <= Utils.titleGenres.size()-1){
            Objects.requireNonNull(item).setTitle(Utils.titleGenres.get(position));
        }
        holder.binding.setItem(item);
    }

    @Override
    public int getItemCount() {
        return adapterManager.adaptersObservableField.size();
    }



    public void setValuesUpdateMovies(String type, MovieObject.Movie item){
        switch (type){
            case Utils.now_playing:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.NowPlayingMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().trim().equals(item.getId().trim())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.popular:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.PopularMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.top_rated:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.TopRateMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.upcoming:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.UpComingMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.ActionMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.ActionMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.AdventureMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.AdventureMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.AnimationMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.AnimationMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.ComedyMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.ComedyMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.CrimeMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.CrimeMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.DocumentaryMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.DocumentaryMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.DramaMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.DramaMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.FamilyMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.FamilyMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.FantasyMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.FantasyMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.HistoryMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.HistoryMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.HorrorMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.HorrorMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.MusicMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.MusicMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.MysteryMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.MysteryMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.RomanceMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.RomanceMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.ScienceFictionMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.ScienceFictionMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.TVMovie:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.TVMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.ThrillerMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.ThrillerMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.WarMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.WarMoviesObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            case Utils.WesternMovies:
                for (MovieObject.Movie a : Objects.requireNonNull(adapterManager.WesternMoviesAdapterObservableField.get()).getMovieList()){
                    if(a.getId().equals(item.getId())){
                        a.setMovie(item);break;
                    }
                }
                break;
            default:
                break;
        }
    }



    public void updateTitleGenre(){
        for (int i = 0; i < bindings.size(); i++) {
            int finalI = i;
            String title = Utils.titleGenres.get(finalI);
            bindings.get(i).titleGenre.post(() -> bindings.get(finalI).titleGenre.setText(title));
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        ItemRecyclerViewBinding binding;
        public ViewHolder(@NonNull ItemRecyclerViewBinding item) {
            super(item.getRoot());
            this.binding = item;
        }
    }

}
