package com.bms.fakestoreapp.core.exceptions;

import com.bms.fakestoreapp.core.exceptions.detais.BusinessExceptionDetail;
import com.bms.fakestoreapp.core.exceptions.detais.InternalServerExceptionDetail;
import com.bms.fakestoreapp.core.exceptions.detais.NotFoundExceptionDetail;
import com.bms.fakestoreapp.core.exceptions.detais.RequestExceptionDetail;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatusCode status,
                                                                  @NotNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(new NotFoundExceptionDetail(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetail> handleBusinessException(BusinessException ex) {
        return new ResponseEntity<>(new BusinessExceptionDetail(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<ProblemDetail> handleRequestNotPermitted(RequestNotPermitted ex) {
        return new ResponseEntity<>(new RequestExceptionDetail(ex.getMessage()), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception ex) {
        return new ResponseEntity<>(new InternalServerExceptionDetail(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
