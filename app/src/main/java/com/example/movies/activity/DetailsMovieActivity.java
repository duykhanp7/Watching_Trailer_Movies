package com.example.movies.activity;

import static com.example.movies.activity.MainActivity.parentAdapterObservableField;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;

import com.example.movies.adapter.CastAdapter;
import com.example.movies.adapter.CastDetailsAdapter;
import com.example.movies.adapter.CrewAdapter;
import com.example.movies.adapter.CrewDetailsAdapter;
import com.example.movies.adapter.MovieAdapterMovieID;
import com.example.movies.adapter.MoviesAdapterByGenres;
import com.example.movies.adapter.VideosAdapter;
import com.example.movies.api.APIGetData;
import com.example.movies.R;
import com.example.movies.databinding.ActivityMovieDetailsBinding;
import com.example.movies.listener.ICastItemClickListener;
import com.example.movies.listener.ICrewItemClickListener;
import com.example.movies.listener.IRecommendationsClickListener;
import com.example.movies.listener.ITrailerItemClickListener;
import com.example.movies.model.Cast;
import com.example.movies.model.Crew;
import com.example.movies.model.MovieObject;
import com.example.movies.model.TrailerObject;
import com.example.movies.utils.Utils;
import com.google.android.youtube.player.YouTubeBaseActivity;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsMovieActivity extends YouTubeBaseActivity implements ITrailerItemClickListener, IRecommendationsClickListener, ICastItemClickListener, ICrewItemClickListener{

    public MovieObject.Movie item;
    boolean stateEmptyWhenFirstClicked = true;
    boolean buttonFavoritesClicked = false;
    public Boolean expandableDetailsCastAndCrew;
    public ObservableField<Boolean> expandableDetailsCastAndCrewObservable;
    public static boolean hadSearch = false;
    ActivityMovieDetailsBinding binding;
    //String type;


    CastAdapter castAdapter;
    CrewAdapter crewAdapter;
    VideosAdapter videosAdapter;
    MovieAdapterMovieID recommendationAdapter;
    MovieAdapterMovieID similarAdapter;
    CastDetailsAdapter castDetailsAdapter;
    CrewDetailsAdapter crewDetailsAdapter;

    public ObservableField<CastAdapter> castAdapterObservableField;
    public ObservableField<CrewAdapter> crewAdapterObservableField;
    public ObservableField<VideosAdapter> videosAdapterObservableField;
    public ObservableField<MovieAdapterMovieID> recommendationsAdapterObservableField;
    public ObservableField<MovieAdapterMovieID> similarAdapterObservableField;
    public ObservableField<MovieObject.Movie> movieObservableField;
    public ObservableField<MovieObject.Movie> oldMovieObservableField;
    public ObservableField<CastDetailsAdapter> castDetailsAdapterObservableField;
    public ObservableField<CrewDetailsAdapter> crewDetailsAdapterObservableField;
    public static ObservableField<MoviesAdapterByGenres> moviesAdapterByGenresObservableFieldDetails;

    public static ObservableField<Boolean> movieByChipGenres;
    public static ObservableField<String> chipTextClicked;
    public static ObservableField<Boolean> buttonSearchClicked;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        item = (MovieObject.Movie) bundle.getSerializable("item");
        //type = bundle.getString("type");
        expandableDetailsCastAndCrew = false;
        expandableDetailsCastAndCrewObservable = new ObservableField<>(expandableDetailsCastAndCrew);

        castAdapter = new CastAdapter(this);
        crewAdapter = new CrewAdapter(this);
        videosAdapter = new VideosAdapter(this);
        recommendationAdapter = new MovieAdapterMovieID(item,this, Utils.recommendations);
        similarAdapter = new MovieAdapterMovieID(item,this,Utils.similar);
        castDetailsAdapter = new CastDetailsAdapter(this);
        crewDetailsAdapter = new CrewDetailsAdapter(this);

        castAdapterObservableField = new ObservableField<>(castAdapter);
        crewAdapterObservableField = new ObservableField<>(crewAdapter);
        videosAdapterObservableField = new ObservableField<>(videosAdapter);
        recommendationsAdapterObservableField = new ObservableField<>(recommendationAdapter);
        similarAdapterObservableField = new ObservableField<>(similarAdapter);
        movieObservableField = new ObservableField<>(item);
        oldMovieObservableField = new ObservableField<>();
        castDetailsAdapterObservableField = new ObservableField<>(castDetailsAdapter);
        crewDetailsAdapterObservableField = new ObservableField<>(crewDetailsAdapter);
        moviesAdapterByGenresObservableFieldDetails = new ObservableField<>();

        //
        movieByChipGenres = new ObservableField<>(false);
        chipTextClicked = new ObservableField<>("Action");
        buttonSearchClicked = new ObservableField<>(false);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_movie_details);
        binding.setMain(this);
        renderUIDetails();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.favoritesButton.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.favorites_ic_normal));
            }
        });

        binding.nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(!v.canScrollVertically(-1)){
                    binding.buttonScrollToTop.setVisibility(View.GONE);
                    Animation animationGone = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_floating_hide);
                    binding.buttonScrollToTop.startAnimation(animationGone);
                }
                else{
                    Animation animationShow = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_floating_show);
                    binding.buttonScrollToTop.startAnimation(animationShow);
                    binding.buttonScrollToTop.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.recyclerViewByChipGenre.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if(!view.canScrollVertically(-1)){
                    binding.buttonScrollToTop.setVisibility(View.GONE);
                    Animation animationGone = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_floating_hide);
                    binding.buttonScrollToTop.startAnimation(animationGone);
                }
                else{
                    Animation animationShow = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_floating_show);
                    binding.buttonScrollToTop.startAnimation(animationShow);
                    binding.buttonScrollToTop.setVisibility(View.VISIBLE);
                }
            }
        });


        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Objects.requireNonNull(moviesAdapterByGenresObservableFieldDetails.get()).getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }


    //FETCH TO GET DETAILS INFORMATION OF MOVIE AND BIND TO VIEW
    @SuppressLint("NotifyDataSetChanged")
    public void renderUIDetails(){
        //CHECK IF SIZE OF TRAILER OF ITEM IS GREATER THAN 0
        // IF GREATER THAN : NOT DO FUNCTION IN IF CONDITION BELOW AND DO ELSE
        //DO FUNCTION IF BELOW IF TRAILERS IS EMPTY
        buttonFavoritesClicked = false;
        expandableDetailsCastAndCrew = false;
        expandableDetailsCastAndCrewObservable.set(expandableDetailsCastAndCrew);
        //Log.i("AAA","TYPE GETTTTTTTTTTTTTT : "+type);
        if(item.getTrailers().getTrailersList().size() == 0){
            Log.i("AAA","NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL FIRST CLICKED");
            stateEmptyWhenFirstClicked = true;
            APIGetData.apiGetData.getDetailsMovieInformation(item.getId()).enqueue(new Callback<MovieObject.Movie>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onResponse(@NonNull Call<MovieObject.Movie> call, @NonNull Response<MovieObject.Movie> response) {
                    item = response.body();
                    movieObservableField.set(item);


                    castAdapter.setCasts(Objects.requireNonNull(item).getStaff().getCasts());
                    crewAdapter.setCrews(item.getStaff().getCrews());
                    videosAdapter.setTrailers(item.getTrailers().getTrailersList());
                    recommendationAdapter.setMoviesList(item.getRecommendations().getMoviesList());
                    similarAdapter.setMoviesList(item.getSimilar().getMoviesList());
                    castDetailsAdapter.setCasts(Objects.requireNonNull(item).getStaff().getCasts());
                    crewDetailsAdapter.setCrews(item.getStaff().getCrews());

                    Objects.requireNonNull(castAdapterObservableField.get()).notifyDataSetChanged();
                    Objects.requireNonNull(crewAdapterObservableField.get()).notifyDataSetChanged();
                    Objects.requireNonNull(videosAdapterObservableField.get()).notifyDataSetChanged();
                    Objects.requireNonNull(recommendationsAdapterObservableField.get()).notifyDataSetChanged();
                    Objects.requireNonNull(similarAdapterObservableField.get()).notifyDataSetChanged();
                    Objects.requireNonNull(castAdapterObservableField.get()).notifyDataSetChanged();
                    Objects.requireNonNull(crewAdapterObservableField.get()).notifyDataSetChanged();
                    Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.updateMovieInAllGenres(item);
                }

                @Override
                public void onFailure(@NonNull Call<MovieObject.Movie> call, @NonNull Throwable t) {
                    Log.i("AAA","FAILEDDDDDDDDDDDDDDDDDDDDD GET API");
                }
            });

        }
        //ELSE DO THIS IF TRAILERS IS NOT NULL
        else{
            Log.i("AAA","NOT NULLLLLLLLLLLLLLLLL FIRST CLICKED");
            stateEmptyWhenFirstClicked = false;// IF NOT NULL TICK IT TO FALSE
            castAdapter.setCasts(Objects.requireNonNull(item).getStaff().getCasts());
            crewAdapter.setCrews(Objects.requireNonNull(item).getStaff().getCrews());
            videosAdapter.setTrailers(item.getTrailers().getTrailersList());
            recommendationAdapter.setMoviesList(item.getRecommendations().getMoviesList());
            similarAdapter.setMoviesList(item.getSimilar().getMoviesList());
            castDetailsAdapter.setCasts(Objects.requireNonNull(item).getStaff().getCasts());
            crewDetailsAdapter.setCrews(item.getStaff().getCrews());

            castAdapterObservableField.set(castAdapter);
            crewAdapterObservableField.set(crewAdapter);
            videosAdapterObservableField.set(videosAdapter);
            recommendationsAdapterObservableField.set(recommendationAdapter);
            similarAdapterObservableField.set(similarAdapter);
            castDetailsAdapterObservableField.set(castDetailsAdapter);
            crewDetailsAdapterObservableField.set(crewDetailsAdapter);

        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        //startWatching();
    }

    //SET DATA RETURN
    public void setResultReturn(){
//        Intent intent = new Intent(DetailsMovieActivity.this, MainActivity.class);
//        intent.putExtra("item",item);
//        intent.putExtra("type",type);
//        setResult(RESULT_OK, intent);
    }


    //ON BACK PRESSED EVENT
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onBackPressed() {
        if(movieByChipGenres.get()){
            Log.i("AAA","AAAAAAAAAAAAAAAAA");
            if(hadSearch){
                Objects.requireNonNull(moviesAdapterByGenresObservableFieldDetails.get()).setMovieListWithOldMovieList();
            }
            else{
                binding.searchView.setQuery("",false);
                movieByChipGenres.set(false);
            }
            hadSearch = false;
            buttonSearchClicked.set(false);
        }
        else{
            hadSearch = false;
            buttonSearchClicked.set(false);
            Log.i("AAA","BBBBBBBBBBBBBBBBBBBB");
            super.onBackPressed();
        }
    }

    //REFRESH LAYOUT WHEN CLICK ON ANOTHER MOVIE ITEM
    public void refreshUIDetails(MovieObject.Movie movie){
        item = movie;
        renderUIDetails();
        movieObservableField.set(item);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.nestedScrollView.fullScroll(ScrollView.FOCUS_UP);
                binding.nestedScrollView.smoothScrollTo(0,0);
                binding.recyclerViewCast.smoothScrollToPosition(0);
                binding.recyclerViewRecommendations.smoothScrollToPosition(0);
                binding.recyclerViewSimilar.smoothScrollToPosition(0);
            }
        });
    }


    //BUTTON ADD TO FAVORITES
    public void onButtonAddToFavorites(View view, MovieObject.Movie movie){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(!buttonFavoritesClicked){
                    binding.favoritesButton.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.favorites_ic_clicked));
                }
                else{
                    binding.favoritesButton.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.favorites_ic_normal));
                }
                buttonFavoritesClicked = !buttonFavoritesClicked;
            }
        });
    }


    //BUTTON SHOW DETAILS CAST AND CREW
    public void onButtonShowDetailsCastAndCrew(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                expandableDetailsCastAndCrew = !expandableDetailsCastAndCrew;
                expandableDetailsCastAndCrewObservable.set(expandableDetailsCastAndCrew);
            }
        }).start();
    }

    //ON BUTTON BACK PRESSED
    public void onIconBackPressed(View view){
//        if(movieByChipGenres.get()){
//            movieByChipGenres.set(false);
//        }
//        else{
//            finish();
//        }
        onBackPressed();
    }

    //ON ITEM TRAILER CLICK
    //START WATCH TRAILER IF CLICKED
    @Override
    public void onTrailerItemClick(TrailerObject.Trailer trailer) {
        Intent intentWatchTrailer = new Intent(DetailsMovieActivity.this,WatchTrailerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("trailer",trailer);;
        intentWatchTrailer.putExtra("bundle",bundle);
        startActivity(intentWatchTrailer);
    }

    //REFRESH LAYOUT MOVIE DETAILS ON OTHERS MOVIE CLICK
    @Override
    public void onRecommendationItemClick(MovieObject.Movie movie) {
        refreshUIDetails(movie);
    }


    //ON ITEM CAST CLICK
    @Override
    public void onItemCastClick(Cast cast) {
        onCastItemClickFunction(cast);
    }

    public void onCastItemClickFunction(Cast cast){
        new Thread(new Runnable() {
            @Override
            public void run() {
                APIGetData.apiGetData.getCastDetails(cast.getId()).enqueue(new Callback<Cast>() {
                    @Override
                    public void onResponse(@NonNull Call<Cast> call, @NonNull Response<Cast> response) {
                        Cast castDetails = response.body();
                        String type = Utils.TYPE_CAST;
                        Intent intentCastClick = new Intent(DetailsMovieActivity.this,DetailsCharacterActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("type",type);
                        bundle.putSerializable("cast",castDetails);
                        intentCastClick.putExtra("bundle",bundle);
                        startActivity(intentCastClick);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Cast> call, @NonNull Throwable t) {
                    }
                });
            }
        }).start();
    }


    //ON ITEM CREW CLICKED
    @Override
    public void onItemCrewClick(Crew crew) {
        onCrewItemClickFunction(crew);
    }

    public void onCrewItemClickFunction(Crew crew){
        new Thread(new Runnable() {
            @Override
            public void run() {
                APIGetData.apiGetData.getCrewDetails(crew.getId()).enqueue(new Callback<Crew>() {
                    @Override
                    public void onResponse(@NonNull Call<Crew> call, @NonNull Response<Crew> response) {
                        Crew crewDetails = response.body();
                        String type = Utils.TYPE_CREW;
                        Intent intentCastClick = new Intent(DetailsMovieActivity.this,DetailsCharacterActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("type",type);
                        bundle.putSerializable("crew",crewDetails);
                        intentCastClick.putExtra("bundle",bundle);
                        startActivity(intentCastClick);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Crew> call, @NonNull Throwable t) {

                    }
                });
            }
        }).start();
    }


    //ON BUTTON SCROLL TO TOP CLICKED
    public void onButtonScrollToTopClicked(){
        binding.nestedScrollView.smoothScrollTo(0,0);
        if(binding.recyclerViewByChipGenre.getVisibility() == View.VISIBLE){
            binding.recyclerViewByChipGenre.smoothScrollToPosition(0);
        }
    }

    //ON BUTTON SEARCH CLICKED
    public void onButtonSearchClicked(){
        Log.i("AAA","ON BUTTON SEARCH CLICKED");
        buttonSearchClicked.set(true);
    }


    //PROBLEMS
    //ĐANG Ở GIAO DIỆN CHI TIẾT PHIM NHƯNG MÀ KHI NHẤN VÀO CÁC ITEM CỦA RECYCLER THÌ CHUYỂN SANG PAGE KHÁC
    //CHỈNH LẠI NẾU ĐANG Ở PAGE CHI TIẾT PHIM THÌ NHẤN VÀO ITEM THÌ VẪN GIỮ ACTIVITY
    //VÀ REFRESH LẠI LAYOUT
}
