package com.kosim.elearning.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoStudentWithGivenEmailException.class)
    public final ResponseEntity<String> handleException(NoStudentWithGivenEmailException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
    }

    @ExceptionHandler(NoLessonWithGivenIdException.class)
    public final ResponseEntity<String> handleException(NoLessonWithGivenIdException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
    }

    @ExceptionHandler(NoTeacherWithGivenEmailException.class)
    public final ResponseEntity<String> handleException(NoTeacherWithGivenEmailException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
    }
}
