package com.recipes.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecipeUniqueException.class)
    public final ResponseEntity<ExceptionDetails> handleRecipeUniqueException(
            final RecipeUniqueException ex,
            final HttpServletRequest request
            ){
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                HttpStatus.FOUND,ex.getLocalizedMessage()
        );
        return new ResponseEntity<>(exceptionDetails,HttpStatus.FOUND);
    }

    @ExceptionHandler(RecipeValidationException.class)
    public final ResponseEntity<ExceptionDetails> handleRecipeValidationException(
            final RecipeValidationException ex,
            final HttpServletRequest request
    ){
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                HttpStatus.PRECONDITION_REQUIRED,ex.getLocalizedMessage()
        );
        return new ResponseEntity<>(exceptionDetails,HttpStatus.PRECONDITION_REQUIRED);
    }

}

