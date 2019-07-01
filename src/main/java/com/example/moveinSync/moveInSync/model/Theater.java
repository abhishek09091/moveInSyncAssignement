package com.example.moveinSync.moveInSync.model;

import java.util.List;

public class Theater {

    private Long theaterId;
    private String theaterName;
    private Address address;
    private Float rating;
    private List<Movie> movieList;

    public Theater() {
    }

    public Theater(Long theaterId, String theaterName, Address address, Float rating) {
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        this.address = address;
        this.rating = rating;
    }

    public Theater(Long theaterId, String theaterName, Address address, Float rating, List<Movie> movieList) {
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        this.address = address;
        this.rating = rating;
        this.movieList = movieList;
    }

    public Long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Long theaterId) {
        this.theaterId = theaterId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "theaterId=" + theaterId +
                ", theaterName='" + theaterName + '\'' +
                ", address=" + address +
                ", rating=" + rating +
                ", movieList=" + movieList +
                '}';
    }
}
