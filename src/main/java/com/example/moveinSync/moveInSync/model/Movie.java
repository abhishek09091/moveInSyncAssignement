package com.example.moveinSync.moveInSync.model;


import java.util.List;


public class Movie {

    private Integer movieId;
    private String movieName;
    private MovieType movieType;
    private List<Show> listOfShowTime;

    public Movie() {
    }

    public Movie(Integer movieId, String movieName, MovieType movieType, List<Show> listOfShowTime) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieType = movieType;
        this.listOfShowTime = listOfShowTime;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }


    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public List<Show> getListOfShowTime() {
        return listOfShowTime;
    }

    public void setListOfShowTime(List<Show> listOfShowTime) {
        this.listOfShowTime = listOfShowTime;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieType=" + movieType +
                ", listOfShowTime=" + listOfShowTime +
                '}';
    }
}
