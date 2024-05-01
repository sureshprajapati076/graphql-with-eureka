package com.graphql.sp04440.exception;

import org.springframework.graphql.client.GraphQlClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookException {

    @ExceptionHandler(GraphQlClientException.class)
    public ProblemDetail handleException(GraphQlClientException ex){
        System.out.println(ex.getLocalizedMessage());
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"ERROR in QUERY");
    }

}
