package com.uala.examen.exception;

import com.uala.examen.dto.generic.ExceptionResponseDTO;
import com.uala.examen.exception.generic.GenericException;
import com.uala.examen.message.ErrorMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Set;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Integer BAD_REQUEST = HttpStatus.BAD_REQUEST.value();

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleException(Exception ex) {

        if(ex instanceof GenericException) {
            return this.handleUserException((GenericException) ex);
        }
        if(ex instanceof NoResourceFoundException) {
            return this.handleNoResourceFoundException();
        }
        if(ex instanceof ConstraintViolationException) {
            return this.handleConstraintViolationException((ConstraintViolationException) ex);
        }
        if(ex instanceof MethodArgumentTypeMismatchException) {
            return this.handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex);
        }
        if(ex instanceof MethodArgumentNotValidException) {
            return this.handleMethodArgumentNotValidException((MethodArgumentNotValidException) ex);
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorMessage.INTERNAL_SERVER_ERROR.getMessage()));
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUserException(GenericException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ExceptionResponseDTO(e.getHttpStatus().value(), e.getMessage()));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleNoResourceFoundException() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponseDTO(BAD_REQUEST, ErrorMessage.ENDPOINT_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponseDTO> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder messages = new StringBuilder();

        for (ConstraintViolation<?> violation : violations) {
            messages.append(violation.getMessage()).append(". ");
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponseDTO(BAD_REQUEST, messages.toString()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponseDTO(BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();

        result.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorMessage.append(fieldName).append(": ").append(message).append(". ");
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponseDTO(BAD_REQUEST, errorMessage.toString()));
    }

}
