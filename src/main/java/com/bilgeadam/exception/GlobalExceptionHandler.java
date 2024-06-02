package com.bilgeadam.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRunTimeException(RuntimeException e){
        log.error("hata mesajı: "+e.getMessage());
        ErrorMessage errorMessage=creteErrorMessage(ErrorType.UNEXPECTED_ERROR,e);
        return  new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MovieAppException.class)
    public ResponseEntity<ErrorMessage> handleMovieAppException(MovieAppException e){
        ErrorType errorType=e.getErrorType();
        HttpStatus httpStatus=errorType.getHttpStatus();
        ErrorMessage errorMessage=creteErrorMessage(errorType,e);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
    ErrorType errorType=ErrorType.BAD_REQUEST;
        List<String> fileds=new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e->fileds.add(e.getField()+": "+e.getDefaultMessage()));
        ErrorMessage errorMessage=creteErrorMessage(errorType,ex);
        errorMessage.setFields(fileds);
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }



    private ErrorMessage creteErrorMessage(ErrorType errorType,Exception e){
        log.error("Hata oluştu: "+ e.getMessage());
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(e.getMessage())
                .build();

    }
}
