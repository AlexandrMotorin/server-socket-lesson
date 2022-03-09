package org.motorin.serverSocket.lesson.validator;

public interface Validator<T> {
    ValidationResult isValid(T t);
}
