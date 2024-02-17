// Write your code here
package com.example.movie.repository;

import com.example.movie.model.Movie;
import com.example.movie.service.MovieH2Service;
import java.util.*;

public interface MovieRepository {
    ArrayList<Movie> getMovies();

    Movie addMovies(Movie movie);

    Movie getMovieById(int movieId);

    Movie updateMovie(int movieId, Movie movie);

    void deleteMovie(int movieId);
}