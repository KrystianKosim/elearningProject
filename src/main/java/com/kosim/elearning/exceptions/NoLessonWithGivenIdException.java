package com.kosim.elearning.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class NoLessonWithGivenIdException extends Exception {
    private final HttpStatus httpStatus;

    public NoLessonWithGivenIdException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
