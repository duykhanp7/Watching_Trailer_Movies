package com.example.movies.activity;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.movies.adapter.AdapterManager;
import com.example.movies.adapter.MoviesAdapterByGenres;
import com.example.movies.adapter.ParentAdapter;
import com.example.movies.adapter.SearchMovieAdapter;
import com.example.movies.api.APIGetData;
import com.example.movies.databinding.ActivityMainBinding;
import com.example.movies.listener.IUpdateData;
import com.example.movies.model.MovieObject;
import com.example.movies.listener.IMovieItemClickListener;
import com.example.movies.R;
import com.example.movies.resources.MovieResources;
import com.example.movies.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements IMovieItemClickListener, IUpdateData {

    ActivityMainBinding activityMainBinding;
    public static MovieResources movieResources;
    public AdapterManager adapterManager;
    public ParentAdapter parentAdapter;
    public static ObservableField<ParentAdapter> parentAdapterObservableField = new ObservableField<>();
    public ObservableField<Boolean> isSearchMovie = new ObservableField<>(false);
    public ObservableField<Boolean> hasSearched = new ObservableField<>(false);
    public ObservableField<SearchMovieAdapter> searchMovieAdapterObservableField = new ObservableField<>(new SearchMovieAdapter(this));
    //ACTIVITY RESULT : GET DATA LIST TRAILER RETURN
    public final ActivityResultLauncher<Intent> mResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                //SAU KHI NHẬN DỮ LIỆU THÌ GỬI SANG ALARMADAPTER ĐỂ CẬP NHẬT DỮ LIỆU
                @Override
                public void onActivityResult(ActivityResult result) {
//                    if(result.getResultCode() == RESULT_OK){
//                        Intent intent = result.getData();
//                        assert intent != null;
//                        MovieObject.Movie item = (MovieObject.Movie) intent.getSerializableExtra("item");
//                        String type = intent.getStringExtra("type");
//                        Objects.requireNonNull(parentAdapterObservableField.get()).setValuesUpdateMovies(type,item);
//                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("AAA","ON RECREWTE");
        //ADAPTER MANAGER
        adapterManager = new AdapterManager();
        //LATEST ADAPTER
//        adapterManager.LatestMoviesAdapter = new MoviesAdapter(new ArrayList<>(10),this);
//        adapterManager.LatestMoviesAdapterObservableField.set(adapterManager.LatestMoviesAdapter);
        //NOW PLAYING ADAPTER
        adapterManager.nowPlayingMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.now_playing,new ArrayList<>(10),this);
        adapterManager.NowPlayingMoviesAdapterObservableField.set(adapterManager.nowPlayingMoviesAdapterByGenres);
        //POPULAR ADAPTER
        adapterManager.popularMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.popular,new ArrayList<>(10),this);
        adapterManager.PopularMoviesAdapterObservableField.set(adapterManager.popularMoviesAdapterByGenres);
        //TOP RATE
        adapterManager.topRateMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.top_rated,new ArrayList<>(10),this);
        adapterManager.TopRateMoviesAdapterObservableField.set(adapterManager.topRateMoviesAdapterByGenres);
        //UP COMING
        adapterManager.upComingMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.upcoming,new ArrayList<>(10),this);
        adapterManager.UpComingMoviesAdapterObservableField.set(adapterManager.upComingMoviesAdapterByGenres);

        //ActionMovies
        adapterManager.actionMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.ActionMovies,new ArrayList<>(10),this);
        adapterManager.ActionMoviesAdapterObservableField.set(adapterManager.actionMoviesAdapterByGenres);

        //AdventureMovies
        adapterManager.adventureMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.AdventureMovies,new ArrayList<>(10),this);
        adapterManager.AdventureMoviesAdapterObservableField.set(adapterManager.adventureMoviesAdapterByGenres);

        //AnimationMovies
        adapterManager.animationMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.AnimationMovies,new ArrayList<>(10),this);
        adapterManager.AnimationMoviesAdapterObservableField.set(adapterManager.animationMoviesAdapterByGenres);

        //ComedyMovies
        adapterManager.comedyMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.ComedyMovies,new ArrayList<>(10),this);
        adapterManager.ComedyMoviesAdapterObservableField.set(adapterManager.comedyMoviesAdapterByGenres);

        //CrimeMovies
        adapterManager.crimeMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.CrimeMovies,new ArrayList<>(10),this);
        adapterManager.CrimeMoviesAdapterObservableField.set(adapterManager.crimeMoviesAdapterByGenres);

        //DocumentaryMovies
        adapterManager.documentaryMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.DocumentaryMovies,new ArrayList<>(10),this);
        adapterManager.DocumentaryMoviesAdapterObservableField.set(adapterManager.documentaryMoviesAdapterByGenres);

        //DramaMovies
        adapterManager.dramaMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.DramaMovies,new ArrayList<>(10),this);
        adapterManager.DramaMoviesAdapterObservableField.set(adapterManager.dramaMoviesAdapterByGenres);

        //FamilyMovies
        adapterManager.familyMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.FamilyMovies,new ArrayList<>(10),this);
        adapterManager.FamilyMoviesAdapterObservableField.set(adapterManager.familyMoviesAdapterByGenres);

        //FantasyMovies
        adapterManager.fantasyMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.FantasyMovies,new ArrayList<>(10),this);
        adapterManager.FantasyMoviesAdapterObservableField.set(adapterManager.fantasyMoviesAdapterByGenres);

        //HistoryMovies
        adapterManager.historyMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.HistoryMovies,new ArrayList<>(10),this);
        adapterManager.HistoryMoviesAdapterObservableField.set(adapterManager.historyMoviesAdapterByGenres);

        //HorrorMovies
        adapterManager.horrorMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.HorrorMovies,new ArrayList<>(10),this);
        adapterManager.HorrorMoviesAdapterObservableField.set(adapterManager.horrorMoviesAdapterByGenres);

        //MusicMovies
        adapterManager.musicMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.MusicMovies,new ArrayList<>(10),this);
        adapterManager.MusicMoviesAdapterObservableField.set(adapterManager.musicMoviesAdapterByGenres);

        //MysteryMovies
        adapterManager.mysteryMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.MysteryMovies,new ArrayList<>(10),this);
        adapterManager.MysteryMoviesAdapterObservableField.set(adapterManager.mysteryMoviesAdapterByGenres);

        //RomanceMovies
        adapterManager.romanceMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.RomanceMovies,new ArrayList<>(10),this);
        adapterManager.RomanceMoviesAdapterObservableField.set(adapterManager.romanceMoviesAdapterByGenres);

        //ScienceFictionMovies
        adapterManager.scienceFictionMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.ScienceFictionMovies,new ArrayList<>(10),this);
        adapterManager.ScienceFictionMoviesAdapterObservableField.set(adapterManager.scienceFictionMoviesAdapterByGenres);

        //TVMovie
        adapterManager.TVMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.TVMovie,new ArrayList<>(10),this);
        adapterManager.TVMoviesAdapterObservableField.set(adapterManager.TVMoviesAdapterByGenres);

        //ThrillerMovies
        adapterManager.thrillerMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.ThrillerMovies,new ArrayList<>(10),this);
        adapterManager.ThrillerMoviesAdapterObservableField.set(adapterManager.thrillerMoviesAdapterByGenres);

        //WarMovies
        adapterManager.warMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.WarMovies,new ArrayList<>(10),this);
        adapterManager.WarMoviesObservableField.set(adapterManager.warMoviesAdapterByGenres);

        //WesternMovies
        adapterManager.westernMoviesAdapterByGenres = new MoviesAdapterByGenres(Utils.WesternMovies,new ArrayList<>(10),this);
        adapterManager.WesternMoviesAdapterObservableField.set(adapterManager.westernMoviesAdapterByGenres);

        //PARENT ADAPTER
        parentAdapter = new ParentAdapter(adapterManager);
        parentAdapterObservableField.set(parentAdapter);

        //MOVIE RESOURCES
        movieResources = new MovieResources(this);
        Thread a,b,c,d,e;


        b = new Thread(new Runnable() {
            @Override
            public void run() {
                movieResources.getMoviesAPIAtPageIndex(Utils.now_playing,1);
            }
        });
        c = new Thread(new Runnable() {
            @Override
            public void run() {
                movieResources.getMoviesAPIAtPageIndex(Utils.popular,1);
            }
        });
        d = new Thread(new Runnable() {
            @Override
            public void run() {
                movieResources.getMoviesAPIAtPageIndex(Utils.top_rated,1);
            }
        });
        e = new Thread(new Runnable() {
            @Override
            public void run() {
                movieResources.getMoviesAPIAtPageIndex(Utils.upcoming,1);
            }
        });

        b.start();c.start();d.start();e.start();
        //BINDING
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setMain(this);
        //ADD SNAP HELPER TO RECYCLER VIEW
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(activityMainBinding.recyclerViewListMovies);


        activityMainBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String keyword = query.trim();
                hasSearched.set(true);
                Objects.requireNonNull(searchMovieAdapterObservableField.get()).setKeyword(keyword);
                APIGetData.apiGetData.getMovieByKeyword(keyword,"1").enqueue(new Callback<MovieObject>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieObject> call, @NonNull Response<MovieObject> response) {
                        Objects.requireNonNull(searchMovieAdapterObservableField.get()).setMoviesSearch(Objects.requireNonNull(response.body()).getMoviesList());
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieObject> call, @NonNull Throwable t) {

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    //OVERRIDE FUNCTION FOR ITEM CLICKED
    @Override
    public void itemClicked(MovieObject.Movie item) {
        Intent intent = new Intent(MainActivity.this, DetailsMovieActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("item",item);
        intent.putExtra("bundle",bundle);
        mResultLauncher.launch(intent);
    }


    //UPDATE ADAPTER WHEN COMPLETED GET DATA FROM API
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void update(List<MovieObject.Movie> movies,String type) {
        int size = 0;

        size = adapterManager.nowPlayingMoviesAdapterByGenres.getMovieList().size();
        switch (type){
            case Utils.now_playing:
                adapterManager.nowPlayingMoviesAdapterByGenres.addMovieList(movies);
                //adapterManager.NowPlayingMoviesAdapter = new MoviesAdapter(Utils.now_playing,movies,this);
                adapterManager.NowPlayingMoviesAdapterObservableField.set(adapterManager.nowPlayingMoviesAdapterByGenres);
                //Objects.requireNonNull(parentAdapterObservableField.get()).setValuesUpdate(Utils.now_playing,adapterManager.NowPlayingMoviesAdapter);
                //parentAdapter.setValuesUpdate(Utils.now_playing,adapterManager.NowPlayingMoviesAdapter,state);
                //parentAdapter.notifyItemChanged(0);
                Objects.requireNonNull(parentAdapter.adapterManager.NowPlayingMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.popular:
                size = adapterManager.popularMoviesAdapterByGenres.getMovieList().size();
                adapterManager.popularMoviesAdapterByGenres.addMovieList(movies);
                //adapterManager.PopularMoviesAdapter = new MoviesAdapter(Utils.popular,movies,this);
                adapterManager.PopularMoviesAdapterObservableField.set(adapterManager.popularMoviesAdapterByGenres);
                //Objects.requireNonNull(parentAdapterObservableField.get()).setValuesUpdate(Utils.popular,adapterManager.PopularMoviesAdapter);
                //parentAdapter.setValuesUpdate(Utils.popular,adapterManager.PopularMoviesAdapter,state);
                //parentAdapter.notifyItemChanged(1);
                Objects.requireNonNull(parentAdapter.adapterManager.PopularMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.top_rated:
                size = adapterManager.topRateMoviesAdapterByGenres.getMovieList().size();
                adapterManager.topRateMoviesAdapterByGenres.addMovieList(movies);
                //adapterManager.TopRateMoviesAdapter = new MoviesAdapter(Utils.top_rated,movies,this);
                adapterManager.TopRateMoviesAdapterObservableField.set(adapterManager.topRateMoviesAdapterByGenres);
                //Objects.requireNonNull(parentAdapterObservableField.get()).setValuesUpdate(Utils.top_rated,adapterManager.TopRateMoviesAdapter);
                //parentAdapter.setValuesUpdate(Utils.top_rated,adapterManager.TopRateMoviesAdapter,state);
                //parentAdapter.notifyItemChanged(2);
                Objects.requireNonNull(parentAdapter.adapterManager.TopRateMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());

                break;
            case Utils.upcoming:
                //adapterManager.UpComingMoviesAdapter = new MoviesAdapter(Utils.upcoming,movies,this);
                size = adapterManager.upComingMoviesAdapterByGenres.getMovieList().size();
                adapterManager.upComingMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.UpComingMoviesAdapterObservableField.set(adapterManager.upComingMoviesAdapterByGenres);
                //Objects.requireNonNull(parentAdapterObservableField.get()).setValuesUpdate(Utils.upcoming,adapterManager.UpComingMoviesAdapter);
                //parentAdapter.setValuesUpdate(Utils.upcoming,adapterManager.UpComingMoviesAdapter,state);
                //parentAdapter.notifyItemChanged(3);
                Objects.requireNonNull(parentAdapter.adapterManager.UpComingMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.ActionMovies:
                size = adapterManager.actionMoviesAdapterByGenres.getMovieList().size();
                adapterManager.actionMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.ActionMoviesAdapterObservableField.set(adapterManager.actionMoviesAdapterByGenres);
                //parentAdapter.notifyItemChanged(4);
                Objects.requireNonNull(parentAdapter.adapterManager.ActionMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.AdventureMovies:
                size = adapterManager.adventureMoviesAdapterByGenres.getMovieList().size();
                adapterManager.adventureMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.AdventureMoviesAdapterObservableField.set(adapterManager.adventureMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(5);
                Objects.requireNonNull(parentAdapter.adapterManager.AdventureMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.AnimationMovies:
                size = adapterManager.animationMoviesAdapterByGenres.getMovieList().size();
                adapterManager.animationMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.AnimationMoviesAdapterObservableField.set(adapterManager.animationMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(6);
                Objects.requireNonNull(parentAdapter.adapterManager.AnimationMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.ComedyMovies:
                size = adapterManager.comedyMoviesAdapterByGenres.getMovieList().size();
                adapterManager.comedyMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.ComedyMoviesAdapterObservableField.set(adapterManager.comedyMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(7);
                Objects.requireNonNull(parentAdapter.adapterManager.ComedyMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.CrimeMovies:
                size = adapterManager.crimeMoviesAdapterByGenres.getMovieList().size();
                adapterManager.crimeMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.CrimeMoviesAdapterObservableField.set(adapterManager.crimeMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(8);
                Objects.requireNonNull(parentAdapter.adapterManager.CrimeMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.DocumentaryMovies:
                size = adapterManager.documentaryMoviesAdapterByGenres.getMovieList().size();
                adapterManager.documentaryMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.DocumentaryMoviesAdapterObservableField.set(adapterManager.documentaryMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(9);
                Objects.requireNonNull(parentAdapter.adapterManager.DocumentaryMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.DramaMovies:
                size = adapterManager.dramaMoviesAdapterByGenres.getMovieList().size();
                adapterManager.dramaMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.DramaMoviesAdapterObservableField.set(adapterManager.dramaMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(10);
                Objects.requireNonNull(parentAdapter.adapterManager.DramaMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.FamilyMovies:
                size = adapterManager.familyMoviesAdapterByGenres.getMovieList().size();
                adapterManager.familyMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.FamilyMoviesAdapterObservableField.set(adapterManager.familyMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(11);
                Objects.requireNonNull(parentAdapter.adapterManager.FamilyMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.FantasyMovies:
                size = adapterManager.fantasyMoviesAdapterByGenres.getMovieList().size();
                adapterManager.fantasyMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.FantasyMoviesAdapterObservableField.set(adapterManager.fantasyMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(12);
                Objects.requireNonNull(parentAdapter.adapterManager.FantasyMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.HistoryMovies:
                size = adapterManager.historyMoviesAdapterByGenres.getMovieList().size();
                adapterManager.historyMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.HistoryMoviesAdapterObservableField.set(adapterManager.historyMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(13);
                Objects.requireNonNull(parentAdapter.adapterManager.HistoryMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.HorrorMovies:
                size = adapterManager.horrorMoviesAdapterByGenres.getMovieList().size();
                adapterManager.horrorMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.HorrorMoviesAdapterObservableField.set(adapterManager.horrorMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(14);
                Objects.requireNonNull(parentAdapter.adapterManager.HorrorMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.MusicMovies:
                size = adapterManager.musicMoviesAdapterByGenres.getMovieList().size();
                adapterManager.musicMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.MusicMoviesAdapterObservableField.set(adapterManager.musicMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(15);
                Objects.requireNonNull(parentAdapter.adapterManager.MusicMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.MysteryMovies:
                size = adapterManager.mysteryMoviesAdapterByGenres.getMovieList().size();
                adapterManager.mysteryMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.MysteryMoviesAdapterObservableField.set(adapterManager.mysteryMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(16);
                Objects.requireNonNull(parentAdapter.adapterManager.MysteryMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.RomanceMovies:
                size = adapterManager.romanceMoviesAdapterByGenres.getMovieList().size();
                adapterManager.romanceMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.RomanceMoviesAdapterObservableField.set(adapterManager.romanceMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(17);
                Objects.requireNonNull(parentAdapter.adapterManager.RomanceMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.ScienceFictionMovies:
                size = adapterManager.scienceFictionMoviesAdapterByGenres.getMovieList().size();
                adapterManager.scienceFictionMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.ScienceFictionMoviesAdapterObservableField.set(adapterManager.scienceFictionMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(18);
                Objects.requireNonNull(parentAdapter.adapterManager.ScienceFictionMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.TVMovie:
                size = adapterManager.TVMoviesAdapterByGenres.getMovieList().size();
                adapterManager.TVMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.TVMoviesAdapterObservableField.set(adapterManager.TVMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(19);
                Objects.requireNonNull(parentAdapter.adapterManager.TVMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.ThrillerMovies:
                size = adapterManager.thrillerMoviesAdapterByGenres.getMovieList().size();
                adapterManager.thrillerMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.ThrillerMoviesAdapterObservableField.set(adapterManager.thrillerMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(20);
                Objects.requireNonNull(parentAdapter.adapterManager.ThrillerMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.WarMovies:
                size = adapterManager.warMoviesAdapterByGenres.getMovieList().size();
                adapterManager.warMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.WarMoviesObservableField.set(adapterManager.warMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(21);
                Objects.requireNonNull(parentAdapter.adapterManager.WarMoviesObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            case Utils.WesternMovies:
                size = adapterManager.westernMoviesAdapterByGenres.getMovieList().size();
                adapterManager.westernMoviesAdapterByGenres.addMovieList(movies);
                adapterManager.WesternMoviesAdapterObservableField.set(adapterManager.westernMoviesAdapterByGenres);
//                parentAdapter.notifyItemChanged(22);
                Objects.requireNonNull(parentAdapter.adapterManager.WesternMoviesAdapterObservableField.get()).notifyItemRangeInserted(size,movies.size());
                break;
            default:
                break;
        }
    }

    @Override
    public void updateTitle() {
        parentAdapter.updateTitleGenre();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onButtonSearchClickedMain(){
        Toast.makeText(getApplicationContext(),"TAO DANG NHAN NE",Toast.LENGTH_LONG).show();
        isSearchMovie.set(true);
    }

    @Override
    public void onBackPressed() {
        hasSearched.set(false);
        if(isSearchMovie.get()){
            isSearchMovie.set(false);
        }
        else{
            super.onBackPressed();
        }
    }
}

