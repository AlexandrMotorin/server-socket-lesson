package org.motorin.serverSocket.lesson.exception;

import lombok.Getter;
import org.motorin.serverSocket.lesson.validator.Error;

import java.util.List;

public class ValidationException extends RuntimeException{

    @Getter
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
