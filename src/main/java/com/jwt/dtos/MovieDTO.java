package com.jwt.dtos;

import java.util.Set;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    
    private Integer mid;

    @NotBlank(message = "please inset title")
    private String title;

    @NotBlank(message = "please inset director")
    private String director;

    @NotBlank(message = "please inset studio")
    private String studio;

    @NotBlank(message = "please inset poster")
    private String poster;

    @NotBlank(message = "please inset url")
    private String posterUrl;

    private Integer releaseYear;
     
   private Set<String> movieCast;

}

