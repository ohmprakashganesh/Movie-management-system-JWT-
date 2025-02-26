package com.jwt.services;

import java.util.List;


import com.jwt.dtos.MovieDTO;
import com.jwt.entities.Movie;


public interface MovieServices {
   
    MovieDTO postMovie(MovieDTO movie);
    MovieDTO getmovie(Long id);
    List<Movie> getMovies();


}
