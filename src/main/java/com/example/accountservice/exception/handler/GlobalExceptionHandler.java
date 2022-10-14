package com.example.accountservice.exception.handler;//package com.edu.springjwt.exception.handler;
//
//import com.edu.springjwt.exception.HomeBusinessError;
//import com.edu.springjwt.exception.HomeException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(HomeException.class)
//    public ResponseEntity<?> handler(HomeException ex){
//        return new ResponseEntity<>(new HomeBusinessError(ex.getError().getCode(), ex.getMessage()), ex.getError().getHttpStatus());
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handlerEx(Exception ex){
//        return new ResponseEntity<>(new HomeBusinessError(ex.getMessage()), HttpStatus.BAD_REQUEST);
//    }
//}
