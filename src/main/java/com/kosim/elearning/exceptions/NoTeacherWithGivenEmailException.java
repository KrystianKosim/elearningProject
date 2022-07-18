package com.kosim.elearning.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NoTeacherWithGivenEmailException extends Exception {
    private final HttpStatus httpStatus;

    public NoTeacherWithGivenEmailException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
