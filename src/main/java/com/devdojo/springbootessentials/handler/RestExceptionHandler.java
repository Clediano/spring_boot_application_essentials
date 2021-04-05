package com.devdojo.springbootessentials.handler;

import com.devdojo.springbootessentials.exception.BadRequestException;
import com.devdojo.springbootessentials.exception.BadRequestExceptionDetails;
import com.devdojo.springbootessentials.exception.ExceptionDetails;
import com.devdojo.springbootessentials.exception.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestHandler(BadRequestException exception) {
        return new ResponseEntity<BadRequestExceptionDetails>(
                BadRequestExceptionDetails
                        .builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .title("Bad Request Exception, check the documentation")
                        .details(exception.getClass().getName())
                        .developerMessage(exception.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails
                        .builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .title("Bad Request Exception, Invalid Fields")
                        .details(exception.getClass().getName())
                        .developerMessage("Check the field(s) error")
                        .fields(fields)
                        .fieldsMessage(fieldMessage)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, @Nullable Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        ExceptionDetails exceptionDetails = ExceptionDetails
                .builder()
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .title(exception.getCause().getMessage())
                .details(exception.getClass().getName())
                .developerMessage(exception.getMessage())
                .build();

        return new ResponseEntity(exceptionDetails, headers, status);
    }


}
