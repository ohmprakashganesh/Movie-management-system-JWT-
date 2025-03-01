package com.jwt.excepctions;

public class EmptyFileExcepction extends RuntimeException  {

    public EmptyFileExcepction(String message){
        super(message);
    }
    
}
