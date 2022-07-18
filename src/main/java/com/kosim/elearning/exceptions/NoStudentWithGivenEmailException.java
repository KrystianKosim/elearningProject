package com.kosim.elearning.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NoStudentWithGivenEmailException extends Exception {
    private final HttpStatus httpStatus;

    public NoStudentWithGivenEmailException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
