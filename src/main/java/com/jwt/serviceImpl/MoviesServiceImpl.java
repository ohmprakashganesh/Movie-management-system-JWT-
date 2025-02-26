package com.jwt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.dtos.MovieDTO;
import com.jwt.entities.Movie;
import com.jwt.repositories.MovieRepo;
import com.jwt.services.MovieServices;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class MoviesServiceImpl implements MovieServices {

@Autowired
private final MovieRepo movieRepo;

public Movie dtoTMovie(MovieDTO dto){
    Movie movie= new Movie();
    movie.setTitle(dto.getTitle());
    movie.setDirector(dto.getDirector());
    movie.setStudio(dto.getStudio());
    movie.setPoster(dto.getPoster());
    movie.setPosterUrl(dto.getPosterUrl());
    movie.setReleaseYear(dto.getReleaseYear());
    movie.setMovieCast(dto.getMovieCast());
    return movie;

}
public MovieDTO movieToDTO(Movie movie) {
    // Create a new instance of MovieDTO
    MovieDTO dto = new MovieDTO();
    // Set values for all fields in MovieDTO using the corresponding values from Movie entity
    dto.setTitle(movie.getTitle());
    dto.setMid(movie.getMid());
    dto.setDirector(movie.getDirector());
    dto.setStudio(movie.getStudio());
    dto.setPoster(movie.getPoster());
    dto.setPosterUrl(movie.getPosterUrl());
    dto.setReleaseYear(movie.getReleaseYear());
    dto.setMovieCast(movie.getMovieCast());
    // Return the populated MovieDTO object
    return dto;
}

    @Override
    public MovieDTO postMovie(MovieDTO movie) {
        //convert to movie to save in db
        Movie obj=dtoTMovie(movie);
       
        //saving in database
         movieRepo.save(obj);

         //converting saved data into dto for response
         MovieDTO dto= movieToDTO(obj);

         //return the response
         return dto;
    }




    @Override
    public MovieDTO getmovie(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getmovie'");
    }

    @Override
    public List<Movie> getMovies() {
        throw new UnsupportedOperationException("Unimplemented method 'getMovies'");
    }
    
}
