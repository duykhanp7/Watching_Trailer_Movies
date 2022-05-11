package com.example.movies.utils;

import static com.example.movies.activity.DetailsMovieActivity.chipTextClicked;
import static com.example.movies.activity.DetailsMovieActivity.movieByChipGenres;
import static com.example.movies.activity.DetailsMovieActivity.moviesAdapterByGenresObservableFieldDetails;
import static com.example.movies.activity.MainActivity.movieResources;
import static com.example.movies.activity.MainActivity.parentAdapterObservableField;
import static com.example.movies.resources.MovieResources.mapGenresID;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.movies.R;
import com.example.movies.adapter.CastAdapter;
import com.example.movies.adapter.CastDetailsAdapter;
import com.example.movies.adapter.CrewAdapter;
import com.example.movies.adapter.CrewDetailsAdapter;
import com.example.movies.adapter.MovieAdapterByIDOfCastCrew;
import com.example.movies.adapter.MovieAdapterMovieID;
import com.example.movies.adapter.MoviesAdapterByGenres;
import com.example.movies.adapter.ParentAdapter;
import com.example.movies.adapter.SearchMovieAdapter;
import com.example.movies.adapter.VideosAdapter;
import com.example.movies.api.APIGetData;
import com.example.movies.model.Cast;
import com.example.movies.model.Crew;
import com.example.movies.model.GenreObject;
import com.example.movies.model.MovieObject;
import com.example.movies.resources.MovieResources;
import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class BindingUtils {

    //LOADING IMAGE CAST FROM PATH TO IMAGEVIEW USING PICASSO
    @BindingAdapter({"imageURLCast"})
    public static void setImageCast(ImageView imageView, Cast cast){
        //    0 - Not specified
        //    1 - Female
        //    2 - Male
        Log.i("AAA","CAST INFORMATION : "+cast.getName()+" -- "+cast.getProfile_path());
        if(cast != null){
            String url = cast.getProfile_path();
            if(url != null){
                Picasso.get().load(Utils.DOMAIN_API_MOVIE_GET_IMAGE.trim() +url.trim()).into(imageView);
            }
            else{
                if(cast.getGender().equals("1")){
                    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.null_person_female));
                }
                else{
                    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.null_person_male));
                }
            }
        }
        else {
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.null_person_male));
        }
    }

    //LOADING IMAGE CREW FROM PATH TO IMAGEVIEW USING PICASSO
    @BindingAdapter({"imageURLCrew"})
    public static void setImageCrew(ImageView imageView, Crew crew){
        //    0 - Not specified
        //    1 - Female
        //    2 - Male
        if(crew != null){
            String url = crew.getProfile_path();
            if(url != null){
                Picasso.get().load(Utils.DOMAIN_API_MOVIE_GET_IMAGE.trim() +url.trim()).into(imageView);
            }
            else {
                if(crew.getGender().equals("1")){
                    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.null_person_female));
                }
                else{
                    imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.null_person_male));
                }
            }
        }
        else{
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.null_person_male));
        }
    }

    //LOADING IMAGE MOVIE FROM PATH TO IMAGEVIEW USING PICASSO
    @BindingAdapter({"imageURLMovie"})
    public static void setImageMovie(ImageView imageView,  MovieObject.Movie movie){
        if(movie != null){
            String url = movie.getPathImage();
            if(url != null){
                Picasso.get().load(Utils.DOMAIN_API_MOVIE_GET_IMAGE.trim() +url.trim()).into(imageView);
            }
        }

    }

    //ADAPTER FOR RECYCLER VIEW OF EVERY GENRES
    @BindingAdapter("setSubAdapter")
    public static void setAdapter(RecyclerView recyclerView, MoviesAdapterByGenres moviesAdapterByGenres){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviesAdapterByGenres);
    }

    //ADAPTER FOR RECYCLER VIEW MAIN
    @BindingAdapter("setAdapter")
    public static void setAdapter(RecyclerView recyclerView, ParentAdapter adapter){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }


    //ADAPTER FOR RECYCLER VIEW CAST
    @BindingAdapter("adapterCast")
    public static void setAdapterCast(RecyclerView recyclerView, CastAdapter castAdapter){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(castAdapter);
    }

    //ADAPTER FOR RECYCLER VIEW CAST
    @BindingAdapter("adapterCrew")
    public static void setAdapterCrew(RecyclerView recyclerView, CrewAdapter crewAdapter){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(crewAdapter);
    }

    //ADAPTER FOR RECYCLER VIEW TRAILERS
    @BindingAdapter("adapterVideos")
    public static void setAdapterRecommendations(RecyclerView recyclerView, VideosAdapter videosAdapter){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(videosAdapter);
    }


    //ADAPTER FOR RECYCLER VIEW SIMILAR
    @BindingAdapter("adapterSimilar")
    public static void setAdapterSimilar(RecyclerView recyclerView, MovieAdapterMovieID movieAdapterMovieID){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieAdapterMovieID);
    }


    //ADAPTER FOR RECYCLER VIEW RECOMMENDATIONS
    @BindingAdapter("adapterRecommendations")
    public static void setAdapterRecommendations(RecyclerView recyclerView, MovieAdapterMovieID movieAdapterMovieID){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieAdapterMovieID);
    }

    //ADAPTER FOR RECYCLER VIEW CASTS DETAILS
    @BindingAdapter("detailsCastAdapter")
    public static void setDetailsCastAdapter(RecyclerView recyclerView, CastDetailsAdapter castDetailsAdapter){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(castDetailsAdapter);
    }

    //ADAPTER FOR RECYCLER VIEW CREW DETAILS
    @BindingAdapter("detailsCrewAdapter")
    public static void setDetailsCrewAdapter(RecyclerView recyclerView, CrewDetailsAdapter crewDetailsAdapter){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(crewDetailsAdapter);
    }


    //ADAPTER FOR RECYCLER VIEW MOVIES OF A CREW OR CAST
    @BindingAdapter("movieAdapterByIDCastCrew")
    public static void setMovieByIDCastCrewAdapter(RecyclerView recyclerView, MovieAdapterByIDOfCastCrew movieAdapterByIDOfCastCrew){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieAdapterByIDOfCastCrew);
    }


    //ADAPTER FOR RECYCLER VIEW SHOW MOVIE BY CHIP GENRES
    @BindingAdapter("typeMovie")
    public static void setAdapterMovieByChipGenre(RecyclerView recyclerView,String type){
        MoviesAdapterByGenres moviesAdapterByGenres = getMoviesAdapterByGenres(type);
        ObservableField<MoviesAdapterByGenres> moviesAdapterByGenresObservableField = new ObservableField<>(moviesAdapterByGenres);
        moviesAdapterByGenresObservableFieldDetails.set(moviesAdapterByGenresObservableField.get());
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(moviesAdapterByGenresObservableField.get());

    }





    //GET THUMBNAIL OF VIDEO FROM YOUTUBE
    //https://img.youtube.com/vi/<insert-youtube-video-id-here>/hqdefault.jpg
    @BindingAdapter("getThumbnail")
    public static void getThumbnail(ImageView imageView, String id){
        if(id != null){
            String path = "https://img.youtube.com/vi/"+id.trim()+"/hqdefault.jpg";
            Picasso.get().load(path).into(imageView);
        }
    }

    //GET DIRECTOR NAME
    @BindingAdapter("directorName")
    public static void getDirectorName(TextView textView, MovieObject.Movie movie){
        if(movie.getStaff().getCrews() != null){
            for(Crew crew : movie.getStaff().getCrews()){
                if(crew.getJob().equals("Director")){
                    textView.setText("Director: " + crew.getName());
                }
            }
        }
    }

    //GET STRING APPEND LANGUAGE, YEAR RELEASE, RUNTIME
    @BindingAdapter("languageYearRuntime")
    public static void getStringAppendLanguageYearReleaseRuntime(TextView textView, MovieObject.Movie movie){
        if(movie.getRuntime() != null){
            List<String> strings = new ArrayList<>();
            String languages = movieResources.mapLanguages.get(movie.getOriginal_language());
            String runtime = movie.getRuntime();
            String releaseDate = movie.getReleaseDate();

            if(languages != null){
                strings.add(languages);
            }
            if(releaseDate != null){
                strings.add(movie.getReleaseDate().split("-")[0]);
            }
            if(runtime != null){
                strings.add(movie.getRuntime()+" minutes");
            }
            textView.setText(String.join(", ",strings));
        }
    }

    //GET STRING APPEND RELEASE DATE AND VOTE AVERAGE
    @SuppressLint("SimpleDateFormat")
    public static String gettextReleaseDateAppendVoteAverage(MovieObject.Movie movie){
        if(movie.getReleaseDate() != null){
            List<String> strings = new ArrayList<>();

            String releaseDate = movie.getReleaseDate();
            String voteAverage = movie.getVote_average() ;

            if(releaseDate != null){
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDate);
                    assert date != null;
                    releaseDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
                    strings.add(releaseDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if(voteAverage != null){
                strings.add(voteAverage);
            }
            return String.join(", ",strings);
        }
        return "";
    }

    //ADD GENRES TO GRIDVIEW
    @BindingAdapter("addGenres")
    public static void addGenresToGridView(GridLayout gridLayout, MovieObject.Movie movie){

        //REMOVE ALL VIEWS DEFAULT ON GRIDLAYOUT
        if(movie.getGenres() != null){
            gridLayout.removeAllViews();
            for (GenreObject.Genre a : movie.getGenres()){
                //CREATE CHIP TO SHOW GENRE
                Chip chip = new Chip(gridLayout.getContext());
                chip.setTextSize(16);
                chip.setGravity(Gravity.CENTER);
                chip.setText(a.getNameGenre());
                chip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String type = chip.getText().toString().replace("\\s{2,}","").trim();
                        String id = mapGenresID.get(type);
                        for (String a : mapGenresID.keySet()){
                            Log.i("AAA","KEY SET : "+a);
                        }
                        Log.i("AAA","ID GENRES : "+id);
                        chipTextClicked.set(type);
                        movieByChipGenres.set(true);
                    }
                });
                //ADD CHIP TO GRIDLAYOUT
                gridLayout.addView(chip);
            }
        }
    }


    //ACTIVITY DETAILS CHARACTERS
    @BindingAdapter({"type","cast","crew"})
    public static void setImageForCastOrCrew(ImageView imageView, String type, Cast cast, Crew crew){
        String url = "";
        String gender = "";
        if(type.equals(Utils.TYPE_CAST)){
            url = cast.getProfile_path();
            gender = cast.getGender();
        }
        else{
            url = crew.getProfile_path();
            gender = crew.getGender();
        }
        if(url  != null){
            Picasso.get().load(Utils.DOMAIN_API_MOVIE_GET_IMAGE+url).into(imageView);
        }
        else {
            if(gender != null){
                if(gender.equals(Utils.GENDER_MALE)){
                    imageView.setBackground(ContextCompat.getDrawable(imageView.getContext(), R.drawable.null_person_male));
                }
                else{
                    imageView.setBackground(ContextCompat.getDrawable(imageView.getContext(), R.drawable.null_person_female));
                }
            }
            else {
                imageView.setBackground(ContextCompat.getDrawable(imageView.getContext(), R.drawable.null_person_male));
            }
        }
    }


    //BINDING RETURN BIRTHDAY AND SEX OF CAST
    @SuppressLint("SimpleDateFormat")
    public static String getBirthdayAppendSexCast(Cast cast){
        if(cast != null){
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                String birthday = cast.getBirthday();
                String gender = cast.getGender();
                if(birthday != null && gender != null){
                    date = format.parse(birthday);
                    String[] str = new String[]{new SimpleDateFormat("dd-MM-yyyy").format(Objects.requireNonNull(date)),cast.getGender().equals(Utils.GENDER_MALE) ? "Male":"Female"};
                    return String.join(", ",str);
                }
                else{
                    if(birthday == null && gender != null){
                        return gender.equals(Utils.GENDER_MALE) ? "Male":"Female";
                    }
                    else if(birthday != null && gender == null){
                        date = format.parse(birthday);
                        return new SimpleDateFormat("dd-MM-yyyy").format(Objects.requireNonNull(date));
                    }
                    else {
                        return "";
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }


    //BINDING RETURN BIRTHDAY AND SEX OF CREW
    @SuppressLint("SimpleDateFormat")
    public static String getBirthdayAppendSexCrew(Crew crew){
        if(crew != null){
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                String birthday = crew.getBirthday();
                String gender = crew.getGender();
                if(birthday != null && crew.getGender() != null){
                    date = format.parse(birthday);
                    String[] str = new String[]{new SimpleDateFormat("dd-MM-yyyy").format(Objects.requireNonNull(date)),crew.getGender().equals(Utils.GENDER_MALE) ? "Male":"Female"};
                    return String.join(", ",str);
                }
                else{
                    if(birthday == null && gender != null){
                        return gender.equals(Utils.GENDER_MALE) ? "Male":"Female";
                    }
                    else if(birthday != null && gender == null){
                        date = format.parse(birthday);
                        return new SimpleDateFormat("dd-MM-yyyy").format(Objects.requireNonNull(date));
                    }
                    else {
                        return "";
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }


    //ON BUTTON SOCIAL CLICKED
    public static void onButtonSocialClick(View view,String url,String typeButton){
        try {
            if(url != null){
                Intent intentSocial = new Intent(Intent.ACTION_VIEW);
                String fullUrl = "";
                switch (typeButton){
                    case Utils.FACEBOOK:
                        fullUrl = Utils.httpFacebook+url;
                        break;
                    case Utils.INSTAGRAM:
                        fullUrl = Utils.httpInstagram+url;
                        break;
                    case Utils.TWITTER:
                        fullUrl = Utils.httpTwitter+url;
                        break;
                }
                intentSocial.setData(Uri.parse(fullUrl));
                view.getContext().startActivity(intentSocial);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //ON BINDING TEXT KNOW FOR DEPARTMENT
    @SuppressLint("SetTextI18n")
    @BindingAdapter("textKnowAs")
    public static void setTextKnowAs(TextView textView,List<String> text){
        textView.setText(String.join(", ",text));
    }


    //BINDING AUTO SCROLL WHEN STATE CHANGE FOR RECYCLER VIEW
    @BindingAdapter("stateChanged")
    public static void setStateChangedForRecyclerView(RecyclerView recyclerView,boolean bool){
        recyclerView.smoothScrollToPosition(0);
    }

    //BINDING AUTO SCROLL WHEN STATE CHANGE FOR NESTED SCROLL VIEW
    @BindingAdapter("stateChanged")
    public static void setStateChangedForNestedScrollView(NestedScrollView nestedScrollView, boolean bool){
        nestedScrollView.smoothScrollTo(0,0);
        if(bool){
            nestedScrollView.setTouchscreenBlocksFocus(true);
            nestedScrollView.setSmoothScrollingEnabled(false);
            nestedScrollView.setNestedScrollingEnabled(false);
            nestedScrollView.setEnabled(false);
        }
        else{
            nestedScrollView.setTouchscreenBlocksFocus(false);
            nestedScrollView.setSmoothScrollingEnabled(true);
            nestedScrollView.setNestedScrollingEnabled(true);
            nestedScrollView.setEnabled(true);
        }
    }

    public static MoviesAdapterByGenres getMoviesAdapterByGenres(String type){
        switch (type){
            case Utils.now_playing:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.NowPlayingMoviesAdapterObservableField.get();
            case Utils.popular:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.PopularMoviesAdapterObservableField.get();
            case Utils.top_rated:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.TopRateMoviesAdapterObservableField.get();
            case Utils.upcoming:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.UpComingMoviesAdapterObservableField.get();
            case Utils.ActionMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.ActionMoviesAdapterObservableField.get();
            case Utils.AdventureMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.AdventureMoviesAdapterObservableField.get();
            case Utils.AnimationMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.AnimationMoviesAdapterObservableField.get();
            case Utils.ComedyMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.ComedyMoviesAdapterObservableField.get();
            case Utils.CrimeMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.CrimeMoviesAdapterObservableField.get();
            case Utils.DocumentaryMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.DocumentaryMoviesAdapterObservableField.get();
            case Utils.DramaMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.DramaMoviesAdapterObservableField.get();
            case Utils.FamilyMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.FamilyMoviesAdapterObservableField.get();
            case Utils.FantasyMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.FantasyMoviesAdapterObservableField.get();
            case Utils.HistoryMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.HistoryMoviesAdapterObservableField.get();
            case Utils.HorrorMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.HorrorMoviesAdapterObservableField.get();
            case Utils.MusicMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.MusicMoviesAdapterObservableField.get();
            case Utils.MysteryMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.MysteryMoviesAdapterObservableField.get();
            case Utils.RomanceMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.RomanceMoviesAdapterObservableField.get();
            case Utils.ScienceFictionMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.ScienceFictionMoviesAdapterObservableField.get();
            case Utils.TVMovie:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.TVMoviesAdapterObservableField.get();
            case Utils.ThrillerMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.ThrillerMoviesAdapterObservableField.get();
            case Utils.WarMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.WarMoviesObservableField.get();
            case Utils.WesternMovies:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.WesternMoviesAdapterObservableField.get();
            default:
                return Objects.requireNonNull(parentAdapterObservableField.get()).adapterManager.ActionMoviesAdapterObservableField.get();
        }
    }


    //ADAPTER FOR SEARCH MOVIE BY KEYWORD
    @BindingAdapter("searchMovieAdapter")
    public static void setAdapterSearchMovie(RecyclerView recyclerView, SearchMovieAdapter searchMovieAdapter){
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(searchMovieAdapter);
    }


}
