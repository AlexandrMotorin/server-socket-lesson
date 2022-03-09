package org.motorin.serverSocket.lesson.mapper;

public interface Mapper<F, T>{
    T mapFrom(F object);
}
