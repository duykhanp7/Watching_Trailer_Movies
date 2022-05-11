package com.example.movies.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.example.movies.R;
import com.example.movies.adapter.MovieAdapterByIDOfCastCrew;
import com.example.movies.api.APIGetData;
import com.example.movies.databinding.ActivityDetailsCharacterBinding;
import com.example.movies.listener.IMovieItemByCastCrewIDClickListener;
import com.example.movies.model.Cast;
import com.example.movies.model.Crew;
import com.example.movies.model.MovieObject;
import com.example.movies.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsCharacterActivity extends AppCompatActivity implements IMovieItemByCastCrewIDClickListener {

    //
    Bundle bundle;
    //
    public String type;
    public Cast cast = new Cast();
    public Crew crew = new Crew();
    public ObservableField<Cast> castObservableField = new ObservableField<>();
    public ObservableField<Crew> crewObservableField = new ObservableField<>();
    public ObservableField<String> bioStringObservableField = new ObservableField<>();
    public ActivityDetailsCharacterBinding binding;
    public boolean buttonFavoritesClicked;
    //ADAPTER
    public MovieAdapterByIDOfCastCrew movieAdapterByIDOfCastCrew;
    public ObservableField<MovieAdapterByIDOfCastCrew> movieAdapterByIDOfCastCrewObservableField;
    //ANIMATION FOR BUTTON SCROLL TO TOP
    Animation animationGone;
    Animation animationShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //INITIALIZE
        //ANIMATIONS
        animationGone = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_floating_hide);
        animationShow = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_floating_show);

        bundle = getIntent().getBundleExtra("bundle");
        type = bundle.getString("type");

        if(type.equals(Utils.TYPE_CAST)){
            cast = (Cast) bundle.getSerializable("cast");
            bioStringObservableField.set(cast.getBiography());
            castObservableField.set(cast);
            movieAdapterByIDOfCastCrew = new MovieAdapterByIDOfCastCrew(cast.getMovie_credits().getCasts(), this);
        }
        else{
            crew = (Crew) bundle.getSerializable("crew");
            bioStringObservableField.set(crew.getBiography());
            crewObservableField.set(crew);
            movieAdapterByIDOfCastCrew = new MovieAdapterByIDOfCastCrew(crew.getMovie_credits().getCrews(),this);
        }
        movieAdapterByIDOfCastCrewObservableField = new ObservableField<>(movieAdapterByIDOfCastCrew);


        binding = DataBindingUtil.setContentView(this,R.layout.activity_details_character);
        binding.setMain(this);


        binding.nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(!v.canScrollVertically(-1)){
                binding.buttonScrollToTop.setVisibility(View.GONE);
                binding.buttonScrollToTop.startAnimation(animationGone);
            }
            else{
                binding.buttonScrollToTop.startAnimation(animationShow);
                binding.buttonScrollToTop.setVisibility(View.VISIBLE);
            }
        });
    }

    //BUTTON ADD TO FAVORITES
    public void onButtonAddToFavorites(View view){
        runOnUiThread(() -> {
            if(!buttonFavoritesClicked){
                binding.favoritesButton.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.favorites_ic_clicked));
            }
            else{
                binding.favoritesButton.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.favorites_ic_normal));
            }
            buttonFavoritesClicked = !buttonFavoritesClicked;
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        buttonFavoritesClicked = false;
    }

    //ON CLICK ON BUTTON BACK PRESSED
    public void onIconBackPressed(View view){
        finish();
    }

    @Override
    public void onItemClicked(MovieObject.Movie movie) {
        APIGetData.apiGetData.getDetailsMovieInformation(movie.getId()).enqueue(new Callback<MovieObject.Movie>() {
            @Override
            public void onResponse(@NonNull Call<MovieObject.Movie> call, @NonNull Response<MovieObject.Movie> response) {
                MovieObject.Movie item = response.body();
                Intent intentDetailsMovie = new Intent(DetailsCharacterActivity.this, DetailsMovieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item",item);
                intentDetailsMovie.putExtra("bundle",bundle);
                startActivity(intentDetailsMovie);
            }

            @Override
            public void onFailure(@NonNull Call<MovieObject.Movie> call, @NonNull Throwable t) {

            }
        });
    }

    //ON BUTTON SCROLL TO TOP CLICKED
    public void onButtonScrollToTopClicked(){
        binding.nestedScrollView.smoothScrollTo(0,0);
    }

}