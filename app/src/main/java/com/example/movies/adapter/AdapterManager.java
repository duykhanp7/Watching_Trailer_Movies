package com.example.movies.adapter;

import androidx.databinding.ObservableField;

import com.example.movies.model.MovieObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterManager{

    //NOW PLAYING
    public ObservableField<MoviesAdapterByGenres> NowPlayingMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres nowPlayingMoviesAdapterByGenres;

    //POPULAR
    public ObservableField<MoviesAdapterByGenres> PopularMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres popularMoviesAdapterByGenres;

    //TOP RATE
    public ObservableField<MoviesAdapterByGenres> TopRateMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres topRateMoviesAdapterByGenres;

    //UP COMING
    public ObservableField<MoviesAdapterByGenres> UpComingMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres upComingMoviesAdapterByGenres;

    //ActionMovies
    public ObservableField<MoviesAdapterByGenres> ActionMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres actionMoviesAdapterByGenres;

    //AdventureMovies
    public ObservableField<MoviesAdapterByGenres> AdventureMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres adventureMoviesAdapterByGenres;

    //AnimationMovies
    public ObservableField<MoviesAdapterByGenres> AnimationMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres animationMoviesAdapterByGenres;

    //Comedy
    public ObservableField<MoviesAdapterByGenres> ComedyMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres comedyMoviesAdapterByGenres;

    //Crime
    public ObservableField<MoviesAdapterByGenres> CrimeMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres crimeMoviesAdapterByGenres;

    //Documentary
    public ObservableField<MoviesAdapterByGenres> DocumentaryMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres documentaryMoviesAdapterByGenres;

    //Drama
    public ObservableField<MoviesAdapterByGenres> DramaMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres dramaMoviesAdapterByGenres;

    //Family
    public ObservableField<MoviesAdapterByGenres> FamilyMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres familyMoviesAdapterByGenres;

    //Fantasy
    public ObservableField<MoviesAdapterByGenres> FantasyMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres fantasyMoviesAdapterByGenres;

    //History
    public ObservableField<MoviesAdapterByGenres> HistoryMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres historyMoviesAdapterByGenres;

    //Horror
    public ObservableField<MoviesAdapterByGenres> HorrorMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres horrorMoviesAdapterByGenres;

    //Music
    public ObservableField<MoviesAdapterByGenres> MusicMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres musicMoviesAdapterByGenres;

    //Mystery
    public ObservableField<MoviesAdapterByGenres> MysteryMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres mysteryMoviesAdapterByGenres;

    //Romance
    public ObservableField<MoviesAdapterByGenres> RomanceMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres romanceMoviesAdapterByGenres;

    //ScienceFiction
    public ObservableField<MoviesAdapterByGenres> ScienceFictionMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres scienceFictionMoviesAdapterByGenres;

    //TV
    public ObservableField<MoviesAdapterByGenres> TVMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres TVMoviesAdapterByGenres;

    //Thriller
    public ObservableField<MoviesAdapterByGenres> ThrillerMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres thrillerMoviesAdapterByGenres;

    //War
    public ObservableField<MoviesAdapterByGenres> WarMoviesObservableField = new ObservableField<>();
    public MoviesAdapterByGenres warMoviesAdapterByGenres;

    //Western
    public ObservableField<MoviesAdapterByGenres> WesternMoviesAdapterObservableField = new ObservableField<>();
    public MoviesAdapterByGenres westernMoviesAdapterByGenres;

    //PROPERTIES
    public List<ObservableField<MoviesAdapterByGenres>> adaptersObservableField;
    public List<MoviesAdapterByGenres> adapters;

    public AdapterManager(){
        adaptersObservableField = new ArrayList<>();
        adapters = new ArrayList<>();

        //adaptersObservableField.add(LatestMoviesAdapterObservableField);
        adaptersObservableField.add(NowPlayingMoviesAdapterObservableField);
        adaptersObservableField.add(PopularMoviesAdapterObservableField);
        adaptersObservableField.add(TopRateMoviesAdapterObservableField);
        adaptersObservableField.add(UpComingMoviesAdapterObservableField);
        adaptersObservableField.add(ActionMoviesAdapterObservableField);
        adaptersObservableField.add(AdventureMoviesAdapterObservableField);
        adaptersObservableField.add(AnimationMoviesAdapterObservableField);
        adaptersObservableField.add(ComedyMoviesAdapterObservableField);
        adaptersObservableField.add(CrimeMoviesAdapterObservableField);
        adaptersObservableField.add(DocumentaryMoviesAdapterObservableField);
        adaptersObservableField.add(DramaMoviesAdapterObservableField);
        adaptersObservableField.add(FamilyMoviesAdapterObservableField);
        adaptersObservableField.add(FantasyMoviesAdapterObservableField);
        adaptersObservableField.add(HistoryMoviesAdapterObservableField);
        adaptersObservableField.add(HorrorMoviesAdapterObservableField);
        adaptersObservableField.add(MusicMoviesAdapterObservableField);
        adaptersObservableField.add(MysteryMoviesAdapterObservableField);
        adaptersObservableField.add(RomanceMoviesAdapterObservableField);
        adaptersObservableField.add(ScienceFictionMoviesAdapterObservableField);
        adaptersObservableField.add(TVMoviesAdapterObservableField);
        adaptersObservableField.add(ThrillerMoviesAdapterObservableField);
        adaptersObservableField.add(WarMoviesObservableField);
        adaptersObservableField.add(WesternMoviesAdapterObservableField);
        //adapters.add(LatestMoviesAdapter);
        adapters.add(nowPlayingMoviesAdapterByGenres);
        adapters.add(popularMoviesAdapterByGenres);
        adapters.add(topRateMoviesAdapterByGenres);
        adapters.add(upComingMoviesAdapterByGenres);
        adapters.add(actionMoviesAdapterByGenres);
        adapters.add(adventureMoviesAdapterByGenres);
        adapters.add(animationMoviesAdapterByGenres);
        adapters.add(comedyMoviesAdapterByGenres);
        adapters.add(crimeMoviesAdapterByGenres);
        adapters.add(documentaryMoviesAdapterByGenres);
        adapters.add(dramaMoviesAdapterByGenres);
        adapters.add(familyMoviesAdapterByGenres);
        adapters.add(fantasyMoviesAdapterByGenres);
        adapters.add(historyMoviesAdapterByGenres);
        adapters.add(horrorMoviesAdapterByGenres);
        adapters.add(musicMoviesAdapterByGenres);
        adapters.add(mysteryMoviesAdapterByGenres);
        adapters.add(romanceMoviesAdapterByGenres);
        adapters.add(scienceFictionMoviesAdapterByGenres);
        adapters.add(TVMoviesAdapterByGenres);
        adapters.add(thrillerMoviesAdapterByGenres);
        adapters.add(warMoviesAdapterByGenres);
        adapters.add(westernMoviesAdapterByGenres);
    }


    public void updateMovieInAllGenres(MovieObject.Movie item){
        new Thread(() -> {
            for (MovieObject.Movie a : Objects.requireNonNull(NowPlayingMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().trim().equals(item.getId().trim())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(PopularMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(TopRateMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(UpComingMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(ActionMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(AdventureMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(AnimationMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(ComedyMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(CrimeMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(DocumentaryMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(DramaMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(FamilyMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(FantasyMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(HistoryMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(HorrorMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(MusicMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(MysteryMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(RomanceMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(ScienceFictionMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(TVMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(ThrillerMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(WarMoviesObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
            for (MovieObject.Movie a : Objects.requireNonNull(WesternMoviesAdapterObservableField.get()).getMovieList()){
                if(a.getId().equals(item.getId())){
                    a.setMovie(item);break;
                }
            }
        }).start();
    }

}
