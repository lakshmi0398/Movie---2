/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
/*
 * You can use the following import statements
 * 
 * import org.springframework.web.server.ResponseStatusException;
 * import org.springframework.http.HttpStatus;
 * 
 */

package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.movie.model.Movie;
import com.example.movie.model.MovieRowMapper;
import com.example.movie.repository.MovieRepository;
import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;

import java.util.*;

// Do not modify the below code
@Service
public class MovieH2Service implements MovieRepository {

  @Autowired
  public JdbcTemplate db;

  // Do not modify the above code

  // Write your code here

  @Override
  public ArrayList<Movie> getMovies() {
    Collection<Movie> movieCollection = db.query("select * from movieList", new MovieRowMapper());
    ArrayList<Movie> movies = new ArrayList<>(movieCollection);
    return movies;
  }

  @Override
  public Movie addMovies(Movie movie) {
    db.update("insert into movieList (movieName,leadActor) values(?, ?)", movie.getMovieName(), movie.getLeadActor());
    Movie savedMovie = db.queryForObject("select * from movieList where movieName = ? and leadActor = ?",  new MovieRowMapper() , movie.getMovieName(), movie.getLeadActor());
    return savedMovie;

  }

  @Override
  public Movie getMovieById(int movieId) {
    try {
      Movie movie = db.queryForObject("select * from movieList where movieId=? ", new MovieRowMapper(), movieId);

      return movie;

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

  }

  @Override
  public Movie updateMovie(int movieId, Movie movie) {

    if (movie.getLeadActor() != null) {
      db.update("update movieList set leadActor = ? where movieId = ?", movie.getLeadActor(), movieId);

    }
    if (movie.getMovieName() != null) {
      db.update("update movieList set movieName = ? where movieId = ?", movie.getMovieName(), movieId);
    }
    return getMovieById(movieId);
  }

  @Override
  public void deleteMovie(int movieId) {
    db.update("delete from movieList where movieId=?", movieId);
  }

}