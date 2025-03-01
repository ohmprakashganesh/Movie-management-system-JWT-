package com.jwt.services;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.jwt.dtos.MovieDTO;


public interface MovieServices {
   
    MovieDTO postMovie(MovieDTO movie);
    MovieDTO getmovie(Integer id);
    Page<MovieDTO> getMovies(int page, int size,String sortBy,String direction);


    MovieDTO updateMovie(Integer movieId, MovieDTO movieDto) throws IOException;

    String deleteMovie(Integer movieId) throws IOException;




}
