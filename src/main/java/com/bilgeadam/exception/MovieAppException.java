package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class MovieAppException  extends RuntimeException{

    private ErrorType errorType;

    public MovieAppException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType=errorType;
    }


    public MovieAppException(ErrorType errorType,String customMessage){
        super(customMessage);
        this.errorType=errorType;
    }

}
