package com.jwt.excepctions;

public class FileExistExcepction  extends RuntimeException  {
    
    public FileExistExcepction(String message){
        super(message);
    }

     
}