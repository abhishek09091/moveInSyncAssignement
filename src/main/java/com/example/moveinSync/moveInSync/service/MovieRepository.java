package com.example.moveinSync.moveInSync.service;

import com.example.moveinSync.moveInSync.model.Movie;

import java.util.List;

public interface MovieRepository {

    Movie create(Movie movie) throws Exception;
    Movie find(Integer id) throws  Exception;
    List<Movie> list();
    Movie update(Long id, Movie movie) throws Exception;
    boolean delete(Long id) throws Exception;

}
