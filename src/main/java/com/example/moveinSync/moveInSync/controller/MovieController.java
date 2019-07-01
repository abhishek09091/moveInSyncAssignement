package com.example.moveinSync.moveInSync.controller;

import com.example.moveinSync.moveInSync.model.Movie;
import com.example.moveinSync.moveInSync.service.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie-entries")
public class MovieController {

    private MovieRepository movieRepository;

    public MovieController( MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody Movie movie) throws Exception {
        System.out.println(movie);
        Movie createMovie = movieRepository.create(movie);
        if(createMovie == null){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(createMovie, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> read(@PathVariable Integer id) throws  Exception{
        Movie movie = movieRepository.find(id);
        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
