package com.jwt.controllers;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jwt.dtos.MovieDTO;
import com.jwt.excepctions.EmptyFileExcepction;
import com.jwt.services.MovieServices;

import lombok.AllArgsConstructor;




@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class MovieController {

    private final MovieServices movieServices;

 
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/add",method=RequestMethod.POST, consumes =MediaType.MULTIPART_FORM_DATA_VALUE )
     public ResponseEntity<MovieDTO> AddMovie(@ModelAttribute MovieDTO movie  ){
    

        if(movie.getFile().isEmpty()){
            throw new EmptyFileExcepction("file is empty");
        }
     MovieDTO dto= movieServices.postMovie(movie) ; 
    System.out.println("running add poster controller" );
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @RequestMapping("/getMovie/{mid}")
    public ResponseEntity<MovieDTO> requestMethodName(@PathVariable Integer mid) {
        return new ResponseEntity<>(movieServices.getmovie(mid),HttpStatus.OK);
    }

       @GetMapping("/all")
    public ResponseEntity<Page<MovieDTO>> getAllMoviesHandler(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size",defaultValue="4") int  size,
        @RequestParam(value="sortBy", defaultValue="asc") String sortBy,
        @RequestParam(value = "direction", defaultValue = "title")String direction) {

        return ResponseEntity.ok(movieServices.getMovies(page, size,sortBy,direction));
    }

    
    @PutMapping("/update/{mid}")
    public ResponseEntity<MovieDTO> updateMovieHandler(@PathVariable Integer mid, @ModelAttribute MovieDTO movieDTO) throws IOException {
        return ResponseEntity.ok(movieServices.updateMovie(mid, movieDTO));
    }

    @DeleteMapping("/delete/{mid}")
    public ResponseEntity<String> deleteMovieHandler(@PathVariable Integer mid) throws IOException {
        return ResponseEntity.ok(movieServices.deleteMovie(mid));
    }

  
    


  


    
}
