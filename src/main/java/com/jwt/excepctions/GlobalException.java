package com.jwt.excepctions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MovieNotFoundExcepction.class)
    public ProblemDetail handleMovieNotFoundException(MovieNotFoundExcepction ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(FileExistExcepction.class)
    public ProblemDetail handleFileNotExistExc(FileExistExcepction ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(EmptyFileExcepction.class)
    public ProblemDetail handleEmptyFileExc(EmptyFileExcepction ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    
}
