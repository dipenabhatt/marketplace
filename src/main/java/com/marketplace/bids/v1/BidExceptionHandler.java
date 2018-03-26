package com.marketplace.bids.v1;

import common.ex.EntityCreateException;
import common.ex.NotFoundException;
import common.ex.ValidationException;
import common.util.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by dipen.bhatt on 3/25/18.
 */

@RestControllerAdvice(assignableTypes = BidController.class)
public class BidExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> notFound(NotFoundException ex,WebRequest webRequest) {
        return new ResponseEntity(new ApiError("Data not Found"),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleExceptionInternal(ValidationException ex,WebRequest webRequest) {
        return new ResponseEntity(new ApiError(ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityCreateException.class)
    public ResponseEntity<ApiError> handleExceptionInternal(EntityCreateException ex,WebRequest webRequest) {
        return new ResponseEntity(new ApiError(ex.getMessage()),HttpStatus.BAD_REQUEST);
    }

}
