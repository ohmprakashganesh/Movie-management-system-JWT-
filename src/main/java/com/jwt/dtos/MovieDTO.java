package com.jwt.dtos;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import jakarta.persistence.Column;



@Data
public class MovieDTO {
    

    private Integer mid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private String studio;

    private MultipartFile file;
 
    private String posterUrl;

    private Integer releaseYear;
    
    
   private Set<String> movieCast;



}

