package com.example.movies.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.movies.BR;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieObject extends BaseObservable implements Serializable{

    @SerializedName("page")
    String pageNumber;

    @SerializedName("results")
    List<Movie> moviesList;

    @SerializedName("total_pages")
    String total_pages;

    public MovieObject(){
        moviesList = new ArrayList<>();
    }


    @Bindable
    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
        this.notifyPropertyChanged(BR.pageNumber);
    }

    @Bindable
    public List<Movie> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Movie> movies) {
        this.moviesList = movies;
        this.notifyPropertyChanged(BR.moviesList);
    }

    @Bindable
    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
        this.notifyPropertyChanged(BR.total_pages);
    }

    public static class Movie extends BaseObservable implements Serializable {

        @SerializedName("id") private String id;
        @SerializedName("title") private String title;
        @SerializedName("poster_path") private String pathImage;
        @SerializedName("release_date") private String releaseDate;
        @SerializedName("genres") private List<GenreObject.Genre> genres;
        @SerializedName("budget") private String budget;
        @SerializedName("original_title") private String original_title;
        @SerializedName("original_language") private String original_language;
        @SerializedName("overview") private String overview;
        @SerializedName("popularity") private String popularity;
        @SerializedName("revenue") private String revenue;
        @SerializedName("runtime") private String runtime;
        @SerializedName("status") private String status;
        @SerializedName("tagline") private String tagline;
        @SerializedName("videos") private TrailerObject trailers;
        @SerializedName("vote_average") private String vote_average;
        @SerializedName("credits") private StaffObject staff;
        @SerializedName("recommendations") private MovieObject recommendations;
        @SerializedName("similar") private MovieObject similar;
        @SerializedName("external_ids") private Social social;

        //USING FOR PERSONAL HAS MOVIE AND TV CREDITS
        @SerializedName("character") private String character;
        @SerializedName("department") private String department;


        boolean endLoading = false;

        public void setMovie(Movie movie){
            this.setId(movie.getId());
            this.setTitle(movie.getTitle());
            this.setPathImage(movie.getPathImage());
            this.setReleaseDate(movie.getReleaseDate());
            this.setGenres(movie.getGenres());
            this.setBudget(movie.getBudget());
            this.setOriginal_title(movie.getOriginal_title());
            this.setOriginal_language(movie.getOriginal_language());
            this.setOverview(movie.getOverview());
            this.setPopularity(movie.getPopularity());
            this.setRevenue(movie.getRevenue());
            this.setRuntime(movie.getRuntime());
            this.setStatus(movie.getStatus());
            this.setTagline(movie.getTagline());
            this.setTrailers(movie.getTrailers());
            this.setVote_average(movie.getVote_average());
            this.setStaff(movie.getStaff());
            this.setRecommendations(movie.getRecommendations());
            this.setSimilar(movie.getSimilar());
        }

        public Movie() {
            genres = new ArrayList<>();
            trailers = new TrailerObject();
            staff = new StaffObject();
            pathImage = "";
        }

        @Bindable
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
            this.notifyPropertyChanged(BR.id);
        }

        @Bindable
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
            this.notifyPropertyChanged(BR.title);
        }

        @Bindable
        public String getPathImage() {
            return pathImage;
        }

        public void setPathImage(String pathImage) {
            this.pathImage = pathImage;
            this.notifyPropertyChanged(BR.pathImage);
        }

        @Bindable
        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            this.notifyPropertyChanged(BR.releaseDate);
        }


        @Bindable
        public String getBudget() {
            return budget;
        }

        public void setBudget(String budget) {
            this.budget = budget;
            this.notifyPropertyChanged(BR.budget);
        }

        @Bindable
        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
            this.notifyPropertyChanged(BR.original_title);
        }

        @Bindable
        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
            this.notifyPropertyChanged(BR.original_language);
        }

        @Bindable
        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
            this.notifyPropertyChanged(BR.overview);
        }

        @Bindable
        public String getPopularity() {
            return popularity;
        }

        public void setPopularity(String popularity) {
            this.popularity = popularity;
            this.notifyPropertyChanged(BR.popularity);
        }

        @Bindable
        public String getRevenue() {
            return revenue;
        }

        public void setRevenue(String revenue) {
            this.revenue = revenue;
            this.notifyPropertyChanged(BR.revenue);
        }

        @Bindable
        public String getRuntime() {
            return runtime;
        }

        public void setRuntime(String runtime) {
            this.runtime = runtime;
            this.notifyPropertyChanged(BR.runtime);
        }

        @Bindable
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
            this.notifyPropertyChanged(BR.status);
        }

        @Bindable
        public String getTagline() {
            return tagline;
        }

        public void setTagline(String tagline) {
            this.tagline = tagline;
            this.notifyPropertyChanged(BR.tagline);
        }

        @Bindable
        public boolean isEndLoading() {
            return endLoading;
        }


        public void setEndLoading(boolean endLoading) {
            this.endLoading = endLoading;
            this.notifyPropertyChanged(BR.endLoading);
        }


        @Bindable
        public List<GenreObject.Genre> getGenres() {
            return genres;
        }

        public void setGenres(List<GenreObject.Genre> genres) {
            this.genres = genres;
            this.notifyPropertyChanged(BR.genres);
        }

        @Bindable
        public TrailerObject getTrailers() {
            return trailers;
        }

        public void setTrailers(TrailerObject trailers) {
            this.trailers = trailers;
            this.notifyPropertyChanged(BR.trailers);
        }


        @Bindable
        public String getVote_average() {
            return vote_average;
        }

        public void setVote_average(String vote_average) {
            this.vote_average = vote_average;
            this.notifyPropertyChanged(BR.vote_average);
        }

        @Bindable
        public StaffObject getStaff() {
            return staff;
        }

        public void setStaff(StaffObject staff) {
            this.staff = staff;
            this.notifyPropertyChanged(BR.staff);
        }

        @Bindable
        public MovieObject getRecommendations() {
            return recommendations;
        }

        public void setRecommendations(MovieObject recommendations) {
            this.recommendations = recommendations;
            this.notifyPropertyChanged(BR.recommendations);
        }

        @Bindable
        public MovieObject getSimilar() {
            return similar;
        }

        public void setSimilar(MovieObject similar) {
            this.similar = similar;
            this.notifyPropertyChanged(BR.similar);
        }


        @Bindable
        public Social getSocial() {
            return social;
        }

        public void setSocial(Social social) {
            this.social = social;
            this.notifyPropertyChanged(BR.social);
        }

        @Bindable
        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
            this.notifyPropertyChanged(BR.character);
        }

        @Bindable
        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
            this.notifyPropertyChanged(BR.department);
        }
    }
}
