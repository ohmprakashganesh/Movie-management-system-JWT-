package com.jwt.dtos;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;



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


public MovieDTO(Integer mid, String title, String director, String studio, MultipartFile file, String posterUrl,
        Integer releaseYear, Set<String> movieCast) {
    this.mid = mid;
    this.title = title;
    this.director = director;
    this.studio = studio;
    this.file = file;
    this.posterUrl = posterUrl;
    this.releaseYear = releaseYear;
    this.movieCast = movieCast;
}


public MovieDTO() {
}


public Integer getMid() {
    return mid;
}


public void setMid(Integer mid) {
    this.mid = mid;
}


public String getTitle() {
    return title;
}


public void setTitle(String title) {
    this.title = title;
}


public String getDirector() {
    return director;
}


public void setDirector(String director) {
    this.director = director;
}


public String getStudio() {
    return studio;
}


public void setStudio(String studio) {
    this.studio = studio;
}


public MultipartFile getFile() {
    return file;
}


public void setFile(MultipartFile file) {
    this.file = file;
}


public String getPosterUrl() {
    return posterUrl;
}


public void setPosterUrl(String posterUrl) {
    this.posterUrl = posterUrl;
}


public Integer getReleaseYear() {
    return releaseYear;
}


public void setReleaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
}


public Set<String> getMovieCast() {
    return movieCast;
}


public void setMovieCast(Set<String> movieCast) {
    this.movieCast = movieCast;
}


@Override
public String toString() {
    return "MovieDTO [mid=" + mid + ", title=" + title + ", director=" + director + ", studio=" + studio + ", file="
            + file + ", posterUrl=" + posterUrl + ", releaseYear=" + releaseYear + ", movieCast=" + movieCast
            + ", getDirector()=" + getDirector() + ", getFile()=" + getFile() + ", getMid()=" + getMid()
            + ", getMovieCast()=" + getMovieCast() + ", getPosterUrl()=" + getPosterUrl() + ", getReleaseYear()="
            + getReleaseYear() + ", getStudio()=" + getStudio() + ", getTitle()=" + getTitle() + ", hashCode()="
            + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
}


@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    MovieDTO other = (MovieDTO) obj;
    if (mid == null) {
        if (other.mid != null)
            return false;
    } else if (!mid.equals(other.mid))
        return false;
    if (title == null) {
        if (other.title != null)
            return false;
    } else if (!title.equals(other.title))
        return false;
    if (director == null) {
        if (other.director != null)
            return false;
    } else if (!director.equals(other.director))
        return false;
    if (studio == null) {
        if (other.studio != null)
            return false;
    } else if (!studio.equals(other.studio))
        return false;
    if (file == null) {
        if (other.file != null)
            return false;
    } else if (!file.equals(other.file))
        return false;
    if (posterUrl == null) {
        if (other.posterUrl != null)
            return false;
    } else if (!posterUrl.equals(other.posterUrl))
        return false;
    if (releaseYear == null) {
        if (other.releaseYear != null)
            return false;
    } else if (!releaseYear.equals(other.releaseYear))
        return false;
    if (movieCast == null) {
        if (other.movieCast != null)
            return false;
    } else if (!movieCast.equals(other.movieCast))
        return false;
    return true;
}


@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((mid == null) ? 0 : mid.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((director == null) ? 0 : director.hashCode());
    result = prime * result + ((studio == null) ? 0 : studio.hashCode());
    result = prime * result + ((file == null) ? 0 : file.hashCode());
    result = prime * result + ((posterUrl == null) ? 0 : posterUrl.hashCode());
    result = prime * result + ((releaseYear == null) ? 0 : releaseYear.hashCode());
    result = prime * result + ((movieCast == null) ? 0 : movieCast.hashCode());
    return result;
}



}

