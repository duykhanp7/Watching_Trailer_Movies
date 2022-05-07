package com.example.movies.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    //EMAIL AND PASSWORD FOR AUTHENTICATION EMAIL
    public static final String EMAIL = "duykhangp7@gmail.com";
    public static final String PASSWORD = "0397439979adAD";

    //API KEY OF T M D B MOVIE
    public static String API_MOVIE_KEY = "904b3059ddd54e71c45dc72502d59375";
    //API KEY OF YOUTUBE
    public static String KEY_API_YOUTUBE = "AIzaSyAJ-V3QPQqEIny48doJbtxkXqjHWBLQak8";
    //DOMAIN
    public static String DOMAIN_API_MOVIE_GET_IMAGE = "https://image.tmdb.org/t/p/w500";
    //GENRE MOVIES
    public static final String latest = "latest";
    public static final String now_playing = "now_playing";
    public static final String popular = "popular";
    public static final String top_rated = "top_rated";
    public static final String upcoming = "upcoming";
    public static final String recommendations = "recommendations";
    public static final String similar = "similar";

    public static final String ActionMovies = "Action";
    public static final String AdventureMovies = "Adventure";
    public static final String AnimationMovies = "Animation";
    public static final String ComedyMovies = "Comedy";
    public static final String CrimeMovies = "Crime";
    public static final String DocumentaryMovies = "Documentary";
    public static final String DramaMovies = "Drama";
    public static final String FamilyMovies = "Family";
    public static final String FantasyMovies = "Fantasy";
    public static final String HistoryMovies = "History";
    public static final String HorrorMovies = "Horror";
    public static final String MusicMovies = "Music";
    public static final String MysteryMovies = "Mystery";
    public static final String RomanceMovies = "Romance";
    public static final String ScienceFictionMovies = "Science Fiction";
    public static final String TVMovie = "TV Movie";
    public static final String ThrillerMovies = "Thriller";
    public static final String WarMovies = "War";
    public static final String WesternMovies = "Western";


    //LIST TITLE GENRE
    public static List<String> titleGenres = new ArrayList<String>();
    public static List<String> typeArray = new ArrayList<String>
                        (Arrays.asList(ActionMovies,AdventureMovies,AnimationMovies,ComedyMovies,
                                CrimeMovies,DocumentaryMovies,DramaMovies,FamilyMovies,
                                FantasyMovies,HistoryMovies,HorrorMovies,MusicMovies,
                                MysteryMovies,RomanceMovies,ScienceFictionMovies,TVMovie,
                                ThrillerMovies,WarMovies,WesternMovies));

    //STATE LOAD INIT OR LOAD TO ADD NEW MOVIES
    public static final int INIT_MOVIES = 0;
    public static final int ADD_MOVIES = 1;
    //HTTPS FACEBOOK, INSTAGRAM, TWITTER
    public static final String httpFacebook = "https://www.facebook.com/";
    public static final String httpInstagram = "https://www.instagram.com/";
    public static final String httpTwitter = "https://twitter.com/";
    //TYPE : CAST OR CREW
    public static final String TYPE_CAST = "0";
    public static final String TYPE_CREW = "1";
    //GENDER
    //    0 - Not specified
    //    1 - Female
    //    2 - Male
    public static final String GENDER_MALE = "2";
    public static final String GENDER_FEMALE = "1";
    public static final String GENDER_OTHERS = "0";
    //SOCIAL
    public static final String FACEBOOK = "FACEBOOK";
    public static final String INSTAGRAM = "INSTAGRAM";
    public static final String TWITTER = "TWITTER";
}
