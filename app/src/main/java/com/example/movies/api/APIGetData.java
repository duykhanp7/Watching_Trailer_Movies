package com.example.movies.api;

import android.view.ViewDebug;

import com.example.movies.model.Cast;
import com.example.movies.model.Crew;
import com.example.movies.model.Language;
import com.example.movies.model.MovieObject;
import com.example.movies.model.GenreObject;
import com.example.movies.model.TrailerObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIGetData {

    //LINK API : https://api.themoviedb.org/3/movie/popular?api_key=904b3059ddd54e71c45dc72502d59375;
    String baseUrl = "https://api.themoviedb.org/";
    APIGetData apiGetData = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(APIGetData.class);


    //GET LIST MOVIES LATEST, NOW PLAYING, POPULAR, TOP RATE AND UP COMING BY GENRE
    //https://api.themoviedb.org/3/movie/popular?api_key=904b3059ddd54e71c45dc72502d59375&page=1;
    @GET("3/movie/{type}")
    Call<MovieObject> getMovies(@Path("type") String type, @Query("api_key") String api_key, @Query("page") String page);

    //GET VIDEOS TRAILER OF MOVIE WITH ID.
    //https://api.themoviedb.org/3/movie/{id}/videos?api_key=904b3059ddd54e71c45dc72502d59375
    @GET("3/movie/{id}/videos")
    Call<TrailerObject> getTrailers(@Path("id") String id, @Query("api_key") String api_key);

    //GET LIST GENRE MOVIES.
    //https://api.themoviedb.org/3/genre/movie/list?api_key=904b3059ddd54e71c45dc72502d59375
    @GET("/3/genre/movie/list")
    Call<GenreObject> getMovieGenres(@Query("api_key") String api_key);

    //GET LIST MOVIES BY GENRE ID.
    // PAGE RANGE 1 - 500
    //https://api.themoviedb.org/3/discover/movie?api_key=904b3059ddd54e71c45dc72502d59375&with_genres=28&page=1
    @GET("/3/discover/movie")
    Call<MovieObject> getMoviesByGenreID(@Query("api_key") String api_key
                                        ,@Query("with_genres") String with_genresId
                                        ,@Query("page") String pageNumber);

    //GET DETAILS INFORMATION AND TRAILERS
    //https://api.themoviedb.org/3/movie/414906?api_key=904b3059ddd54e71c45dc72502d59375&append_to_response=videos,credits,recommendations,similar,external_ids
    @GET("3/movie/{id}?api_key=904b3059ddd54e71c45dc72502d59375&append_to_response=videos,credits,recommendations,similar,external_ids")
    Call<MovieObject.Movie> getDetailsMovieInformation(@Path("id") String id);

    //GET MOVIES RECOMMENDATION BY MOVIE ID
    //https://api.themoviedb.org/3/movie/{movie_id}/{type}?api_key=904b3059ddd54e71c45dc72502d59375&page=1
    //TYPE : recommendations or similar
    @GET("3/movie/{id}/{type}")
    Call<MovieObject> getMoviesByIdAndTitle(@Path("id") String id
                                                  ,@Path("type") String type
                                                  , @Query("api_key") String api_key
                                                  , @Query("page") String page);

    //GET THE LIST OF LANGUAGE (ISO 639-1 tags)
    //https://api.themoviedb.org/3/configuration/languages?api_key=904b3059ddd54e71c45dc72502d59375
    @GET("3/configuration/languages?api_key=904b3059ddd54e71c45dc72502d59375")
    Call<List<Language>> getLanguages();

    //GET DETAILS INFORMATION OF CAST OR CREW
    //https://api.themoviedb.org/3/person/11288?api_key=904b3059ddd54e71c45dc72502d59375&append_to_response=movie_credits,tv_credits,external_ids,images
    @GET("3/person/{id}?api_key=904b3059ddd54e71c45dc72502d59375&append_to_response=movie_credits,tv_credits,external_ids,images")
    Call<Cast> getCastDetails(@Path("id") String id);
    //GET CREW DETAILS
    @GET("3/person/{id}?api_key=904b3059ddd54e71c45dc72502d59375&append_to_response=movie_credits,tv_credits,external_ids,images")
    Call<Crew> getCrewDetails(@Path("id") String id);
}
