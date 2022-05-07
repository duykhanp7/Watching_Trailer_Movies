package com.example.movies.resources;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.movies.BR;
import com.example.movies.api.APIGetData;
import com.example.movies.listener.IUpdateData;
import com.example.movies.model.GenreObject;
import com.example.movies.model.Language;
import com.example.movies.model.MovieObject;
import com.example.movies.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieResources extends BaseObservable {


    public final IUpdateData updateData;
    //GENRE LIST
    public GenreObject GenresMovie;
    //TOP MOVIES
    public List<MovieObject> LatestMovies;
    public List<MovieObject> NowPlayingMovies;
    public List<MovieObject> PopularMovies;
    public List<MovieObject> TopRatedMovies;
    public List<MovieObject> UpComingMovies;

    //GENRE MOVIES
    public List<MovieObject> ActionMovies;
    public List<MovieObject> AdventureMovies;
    public List<MovieObject> AnimationMovies;
    public List<MovieObject> ComedyMovies;
    public List<MovieObject> CrimeMovies;
    public List<MovieObject> DocumentaryMovies;
    public List<MovieObject> DramaMovies;
    public List<MovieObject> FamilyMovies;
    public List<MovieObject> FantasyMovies;
    public List<MovieObject> HistoryMovies;
    public List<MovieObject> HorrorMovies;
    public List<MovieObject> MusicMovies;
    public List<MovieObject> MysteryMovies;
    public List<MovieObject> RomanceMovies;
    public List<MovieObject> ScienceFictionMovies;
    public List<MovieObject> TVMovieMovies;
    public List<MovieObject> ThrillerMovies;
    public List<MovieObject> WarMovies;
    public List<MovieObject> WesternMovies;

    //LANGUAGES (ISO 639-1 tags) )
    public List<Language> languages;
    public Map<String,String> mapLanguages;

    //LIST OF LIST MOVIES OBJECT
    public Map<String,List<MovieObject>> mapAdapter;

    public MovieResources(IUpdateData updateData){
        this.updateData = updateData;
        mapAdapter = new HashMap<>();

        //GENRE
        GenresMovie = new GenreObject();
        //TOP MOVIE
        LatestMovies = new ArrayList<>();
        PopularMovies = new ArrayList<>();
        NowPlayingMovies = new ArrayList<>();
        TopRatedMovies = new ArrayList<>();
        UpComingMovies = new ArrayList<>();
        //GENRE MOVIES
        ActionMovies = new ArrayList<>();
        AdventureMovies = new ArrayList<>();
        AnimationMovies = new ArrayList<>();
        ComedyMovies = new ArrayList<>();
        CrimeMovies = new ArrayList<>();
        DocumentaryMovies = new ArrayList<>();
        DramaMovies = new ArrayList<>();
        FamilyMovies = new ArrayList<>();
        FantasyMovies = new ArrayList<>();
        HistoryMovies = new ArrayList<>();
        HorrorMovies = new ArrayList<>();
        MusicMovies = new ArrayList<>();
        MysteryMovies = new ArrayList<>();
        RomanceMovies = new ArrayList<>();
        ScienceFictionMovies = new ArrayList<>();
        TVMovieMovies = new ArrayList<>();
        ThrillerMovies = new ArrayList<>();
        WarMovies = new ArrayList<>();
        WesternMovies = new ArrayList<>();

        //LANGUAGES
        languages = new ArrayList<>();
        mapLanguages = new HashMap<>();

        //ADD ITEM TO PARENT LIST MOVIE OBJECT
        mapAdapter.put(Utils.now_playing,NowPlayingMovies);
        mapAdapter.put(Utils.popular,PopularMovies);
        mapAdapter.put(Utils.top_rated,TopRatedMovies);
        mapAdapter.put(Utils.upcoming,UpComingMovies);
        mapAdapter.put(Utils.ActionMovies,ActionMovies);
        mapAdapter.put(Utils.AdventureMovies,AdventureMovies);
        mapAdapter.put(Utils.AnimationMovies,AnimationMovies);
        mapAdapter.put(Utils.ComedyMovies,ComedyMovies);
        mapAdapter.put(Utils.CrimeMovies,CrimeMovies);
        mapAdapter.put(Utils.DocumentaryMovies,DocumentaryMovies);
        mapAdapter.put(Utils.DramaMovies,DramaMovies);
        mapAdapter.put(Utils.FamilyMovies,FamilyMovies);
        mapAdapter.put(Utils.FantasyMovies,FantasyMovies);
        mapAdapter.put(Utils.HistoryMovies,HistoryMovies);
        mapAdapter.put(Utils.HorrorMovies,HorrorMovies);
        mapAdapter.put(Utils.MusicMovies,MusicMovies);
        mapAdapter.put(Utils.MysteryMovies,MysteryMovies);
        mapAdapter.put(Utils.RomanceMovies,RomanceMovies);
        mapAdapter.put(Utils.ScienceFictionMovies,ScienceFictionMovies);
        mapAdapter.put(Utils.TVMovie,TVMovieMovies);
        mapAdapter.put(Utils.ThrillerMovies,ThrillerMovies);
        mapAdapter.put(Utils.WarMovies,WarMovies);
        mapAdapter.put(Utils.WesternMovies,WesternMovies);


        //RUN SOME FUNCTIONS
        //GET GENRES AND LANGUAGES
        getMovieGenresAPI();
        getLanguages();
    }




    //GET MOVIES POPULAR AT PAGE INDEX -> PAGE > 0
    public synchronized void getMoviesAPIAtPageIndex(String type,int page){
        APIGetData.apiGetData.getMovies(type, Utils.API_MOVIE_KEY, String.valueOf(page)).enqueue(new Callback<MovieObject>() {
            @Override
            public void onResponse(@NonNull Call<MovieObject> call, @NonNull Response<MovieObject> response) {
                assert response.body() != null;
                if(response.body().getMoviesList().size() > 0){
                    Log.i("AAA","GET DATA RETURN SUCCESSFULLY "+response.body().getPageNumber()+" -- "+type);
                    Objects.requireNonNull(mapAdapter.get(type)).add(response.body());
                    updateData.update(response.body().getMoviesList(),type);
                }
            }
            @Override
            public void onFailure(@NonNull Call<MovieObject> call, @NonNull Throwable t) {
                Log.i("AAA","GET DATA RETURN FAILURE");
            }
        });
    }

    //GET GENRES MOVIE
    public void getMovieGenresAPI(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                APIGetData.apiGetData.getMovieGenres(Utils.API_MOVIE_KEY).enqueue(new Callback<GenreObject>() {
                    @Override
                    public void onResponse(@NonNull Call<GenreObject> call, @NonNull Response<GenreObject> response) {
                        assert response.body() != null;
                        GenresMovie = response.body();
                        Utils.titleGenres.add("Now Playing");
                        Utils.titleGenres.add("Popular");
                        Utils.titleGenres.add("Top Rated");
                        Utils.titleGenres.add("Up Coming");
                        for (GenreObject.Genre item : response.body().getGenres()){
                            Utils.titleGenres.add(item.getNameGenre());
                        }
                        updateData.updateTitle();
                        getAllMoviesFromDiscoverByGenre();
                    }

                    @Override
                    public void onFailure(@NonNull Call<GenreObject> call, @NonNull Throwable t) {

                    }
                });
            }
        }).start();
    }


    //GET MOVIES BY GENRE ID
    public synchronized void getAllMoviesByGenre(String type, String page){
        String id_genre = getIdGenreByNameId(type);
        Log.i("AAA","TYPE NAME : "+type+" -- "+id_genre);
        APIGetData.apiGetData.getMoviesByGenreID(Utils.API_MOVIE_KEY,id_genre,page).enqueue(new Callback<MovieObject>() {
            @Override
            public void onResponse(@NonNull Call<MovieObject> call, @NonNull Response<MovieObject> response) {
                assert response.body() != null;
                if(response.body().getMoviesList().size() > 0){
                    Objects.requireNonNull(mapAdapter.get(type)).add(response.body());
                    updateData.update(response.body().getMoviesList(),type);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieObject> call, @NonNull Throwable t) {
                Log.i("AAA","ON FAILED GET GENRE : ");
            }
        });
    }

    //LOOP FOR GET ALL MOVIES BY GENRE
    public void getAllMoviesFromDiscoverByGenre(){
        for (int i = 0; i < GenresMovie.getGenres().size(); i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    GenreObject.Genre item = GenresMovie.getGenres().get(finalI);
                    Log.i("AAA","TYPE GENRE : "+item.getIdGenre()+" -- "+item.getNameGenre());
                    getAllMoviesByGenre(Utils.typeArray.get(finalI), String.valueOf(1));
                }
            }).start();
        }
    }


    //GET LANGUAGES
    public synchronized void getLanguages(){
        APIGetData.apiGetData.getLanguages().enqueue(new Callback<List<Language>>() {
            @Override
            public void onResponse(@NonNull Call<List<Language>> call, @NonNull Response<List<Language>> response) {
                assert response.body() != null;
                languages.addAll(response.body());

                for (Language e : languages){
                    mapLanguages.put(e.getIso_639_1(),e.getEnglish_name());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Language>> call, @NonNull Throwable t) {

            }
        });
    }

    public String getIdGenreByNameId(String str){
        for (GenreObject.Genre item : GenresMovie.getGenres()){
            if(item.getNameGenre().trim().equals(str)){
                return item.getIdGenre();
            }
        }
        return "";
    }



    //GENRES
    @Bindable
    public GenreObject getGenresMovie() {
        return GenresMovie;
    }

    public void setGenresMovie(GenreObject genresMovie) {
        GenresMovie = genresMovie;
        this.notifyPropertyChanged(BR.genresMovie);
    }
    //GETTER AND SETTER
    @Bindable
    public List<MovieObject> getLatestMovies() {
        return LatestMovies;
    }

    public void setLatestMovies(List<MovieObject> latestMovies) {
        LatestMovies = latestMovies;
        this.notifyPropertyChanged(BR.latestMovies);
    }

    @Bindable
    public List<MovieObject> getNowPlayingMovies() {
        return NowPlayingMovies;
    }

    public void setNowPlayingMovies(List<MovieObject> nowPlayingMovies) {
        NowPlayingMovies = nowPlayingMovies;
        this.notifyPropertyChanged(BR.nowPlayingMovies);
    }

    @Bindable
    public List<MovieObject> getPopularMovies() {
        return PopularMovies;
    }

    public void setPopularMovies(List<MovieObject> popularMovies) {
        PopularMovies = popularMovies;
        this.notifyPropertyChanged(BR.popularMovies);
    }

    @Bindable
    public List<MovieObject> getTopRatedMovies() {
        return TopRatedMovies;
    }

    public void setTopRatedMovies(List<MovieObject> topRatedMovies) {
        TopRatedMovies = topRatedMovies;
        this.notifyPropertyChanged(BR.topRatedMovies);
    }

    @Bindable
    public List<MovieObject> getUpComingMovies() {
        return UpComingMovies;
    }

    public void setUpComingMovies(List<MovieObject> upComingMovies) {
        UpComingMovies = upComingMovies;
        this.notifyPropertyChanged(BR.upComingMovies);
    }

    @Bindable
    public List<MovieObject> getActionMovies() {
        return ActionMovies;
    }

    public void setActionMovies(List<MovieObject> actionMovies) {
        ActionMovies = actionMovies;
        this.notifyPropertyChanged(BR.actionMovies);
    }

    @Bindable
    public List<MovieObject> getAdventureMovies() {
        return AdventureMovies;
    }

    public void setAdventureMovies(List<MovieObject> adventureMovies) {
        AdventureMovies = adventureMovies;
        this.notifyPropertyChanged(BR.adventureMovies);
    }

    @Bindable
    public List<MovieObject> getAnimationMovies() {
        return AnimationMovies;
    }

    public void setAnimationMovies(List<MovieObject> animationMovies) {
        AnimationMovies = animationMovies;
        this.notifyPropertyChanged(BR.animationMovies);
    }

    @Bindable
    public List<MovieObject> getComedyMovies() {
        return ComedyMovies;
    }

    public void setComedyMovies(List<MovieObject> comedyMovies) {
        ComedyMovies = comedyMovies;
        this.notifyPropertyChanged(BR.comedyMovies);
    }

    @Bindable
    public List<MovieObject> getCrimeMovies() {
        return CrimeMovies;
    }

    public void setCrimeMovies(List<MovieObject> crimeMovies) {
        CrimeMovies = crimeMovies;
        this.notifyPropertyChanged(BR.crimeMovies);
    }

    @Bindable
    public List<MovieObject> getDocumentaryMovies() {
        return DocumentaryMovies;
    }

    public void setDocumentaryMovies(List<MovieObject> documentaryMovies) {
        DocumentaryMovies = documentaryMovies;
        this.notifyPropertyChanged(BR.documentaryMovies);
    }

    @Bindable
    public List<MovieObject> getDramaMovies() {
        return DramaMovies;
    }

    public void setDramaMovies(List<MovieObject> dramaMovies) {
        DramaMovies = dramaMovies;
        this.notifyPropertyChanged(BR.dramaMovies);
    }

    @Bindable
    public List<MovieObject> getFamilyMovies() {
        return FamilyMovies;
    }

    public void setFamilyMovies(List<MovieObject> familyMovies) {
        FamilyMovies = familyMovies;
        this.notifyPropertyChanged(BR.familyMovies);
    }

    @Bindable
    public List<MovieObject> getFantasyMovies() {
        return FantasyMovies;
    }

    public void setFantasyMovies(List<MovieObject> fantasyMovies) {
        FantasyMovies = fantasyMovies;
        this.notifyPropertyChanged(BR.fantasyMovies);
    }

    @Bindable
    public List<MovieObject> getHistoryMovies() {
        return HistoryMovies;
    }

    public void setHistoryMovies(List<MovieObject> historyMovies) {
        HistoryMovies = historyMovies;
        this.notifyPropertyChanged(BR.historyMovies);
    }

    @Bindable
    public List<MovieObject> getHorrorMovies() {
        return HorrorMovies;
    }

    public void setHorrorMovies(List<MovieObject> horrorMovies) {
        HorrorMovies = horrorMovies;
        this.notifyPropertyChanged(BR.horrorMovies);
    }

    @Bindable
    public List<MovieObject> getMusicMovies() {
        return MusicMovies;
    }

    public void setMusicMovies(List<MovieObject> musicMovies) {
        MusicMovies = musicMovies;
        this.notifyPropertyChanged(BR.musicMovies);
    }

    @Bindable
    public List<MovieObject> getMysteryMovies() {
        return MysteryMovies;
    }

    public void setMysteryMovies(List<MovieObject> mysteryMovies) {
        MysteryMovies = mysteryMovies;
        this.notifyPropertyChanged(BR.mysteryMovies);
    }

    @Bindable
    public List<MovieObject> getRomanceMovies() {
        return RomanceMovies;
    }

    public void setRomanceMovies(List<MovieObject> romanceMovies) {
        RomanceMovies = romanceMovies;
        this.notifyPropertyChanged(BR.romanceMovies);
    }

    @Bindable
    public List<MovieObject> getScienceFictionMovies() {
        return ScienceFictionMovies;
    }

    public void setScienceFictionMovies(List<MovieObject> scienceFictionMovies) {
        ScienceFictionMovies = scienceFictionMovies;
        this.notifyPropertyChanged(BR.scienceFictionMovies);
    }

    @Bindable
    public List<MovieObject> getTVMovieMovies() {
        return TVMovieMovies;
    }

    public void setTVMovieMovies(List<MovieObject> TVMovieMovies) {
        this.TVMovieMovies = TVMovieMovies;
        this.notifyPropertyChanged(BR.tVMovieMovies);
    }

    @Bindable
    public List<MovieObject> getThrillerMovies() {
        return ThrillerMovies;
    }

    public void setThrillerMovies(List<MovieObject> thrillerMovies) {
        ThrillerMovies = thrillerMovies;
        this.notifyPropertyChanged(BR.thrillerMovies);
    }

    @Bindable
    public List<MovieObject> getWarMovies() {
        return WarMovies;
    }

    public void setWarMovies(List<MovieObject> warMovies) {
        WarMovies = warMovies;
        this.notifyPropertyChanged(BR.warMovies);
    }

    @Bindable
    public List<MovieObject> getWesternMovies() {
        return WesternMovies;
    }

    public void setWesternMovies(List<MovieObject> westernMovies) {
        WesternMovies = westernMovies;
        this.notifyPropertyChanged(BR.westernMovies);
    }
}
