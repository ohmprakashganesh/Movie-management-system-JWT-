package com.jwt.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.jwt.dtos.MovieDTO;
import com.jwt.entities.Movie;
import com.jwt.excepctions.EmptyFileExcepction;
import com.jwt.excepctions.FileExistExcepction;
import com.jwt.repositories.MovieRepo;
import com.jwt.serviceImpl.fileservice.FileService;
import com.jwt.services.MovieServices;



@Service
public class MoviesServiceImpl implements MovieServices {

    private final MovieRepo movieRepo;
    private final FileService fileService;
    
    @Value("${project.posters}") 
    private String path;

    @Value("${base.url}")
    private String baseUrl;


    private String uploadedFileName = "hello";  // Default value
    
    public MoviesServiceImpl(MovieRepo movieRepo, FileService fileService) {
        this.movieRepo = movieRepo;
        this.fileService = fileService;
    }




    @Override
    public MovieDTO postMovie(MovieDTO movie) {
        //handle the file
    if(movie.getFile().isEmpty()){
     throw new EmptyFileExcepction("empty file");
}

 if(Files.exists(Paths.get(path+File.separator+movie.getFile().getOriginalFilename()))){
 throw new FileExistExcepction("already exist");
}

  try {
     uploadedFileName= fileService.uploadFile(path,movie.getFile());
} catch (IOException e) {
    System.out.println(e.getMessage());
    e.printStackTrace();
}

        //convert to movie to save in db
        Movie obj=dtoToMovie(movie,uploadedFileName);
       
        //saving in database
         movieRepo.save(obj);

         //converting saved data into dto for response
         MovieDTO dto= movieToDTO(obj);

         //return the response
         return dto;
    }

    @Override
    public MovieDTO getmovie(Integer mid) {
      Optional <Movie> optional=movieRepo.findById(mid);
      if(optional.isPresent()){
       Movie movie= optional.get();
       return movieToDTO(movie);
       
      }else{
        return  null;
      }  
    }


    @Override
    public Page< MovieDTO> getMovies(int page, int size,String sortBy,String direction) {
      Sort sort=direction.equals("desc")? Sort.by(sortBy).descending(): Sort.by(sortBy).ascending();
      var Pageable=PageRequest.of(page, size,sort);

     Page<Movie> movies=  movieRepo.findAll(Pageable);     
      return movies.map(this::movieToDTO);   
 }


    public Movie dtoToMovie(MovieDTO dto, String poster){
        Movie movie= new Movie();
        movie.setTitle(dto.getTitle());
        movie.setDirector(dto.getDirector());
        movie.setStudio(dto.getStudio());
        movie.setPosterUrl(poster);
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
        dto.setPosterUrl(movie.getPosterUrl());
        dto.setReleaseYear(movie.getReleaseYear());
        dto.setMovieCast(movie.getMovieCast());
        // Return the populated MovieDTO object
        return dto;
    }




    @Override
    public MovieDTO updateMovie(Integer movieId, MovieDTO movieDto) throws IOException {
      Optional  < Movie> optional= movieRepo.findById(movieId);
      if(optional.isPresent()){
       Movie movie=optional.get();
       movie.setTitle(movieDto.getTitle());
       movie.setDirector(movieDto.getDirector());
       movie.setStudio(movieDto.getStudio());
       movie.setMovieCast(movieDto.getMovieCast());
       movie.setPosterUrl(movieDto.getFile().getOriginalFilename());
       movie.setReleaseYear(movieDto.getReleaseYear());
        
       Movie updated= movieRepo.save(movie);
       return movieToDTO(updated);

      }else{
        return null;
      }

    }




    @Override
    public String deleteMovie(Integer movieId) throws IOException {
        Optional<Movie> optional= movieRepo.findById(movieId);
        if(optional.isPresent()){
            movieRepo.deleteById(movieId);
            return "successfully deleted";
        }
        else{
            return "not found movie";
        }
          
    }





    
}
