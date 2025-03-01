package com.jwt.excepctions;

public class MovieNotFoundExcepction extends RuntimeException {
    public MovieNotFoundExcepction(String message){
        super(message);
    }
}
