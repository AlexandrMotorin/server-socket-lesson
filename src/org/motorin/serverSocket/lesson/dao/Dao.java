package org.motorin.serverSocket.lesson.dao;

import java.util.List;

public interface Dao<K,T>{

    List<T> findAll();
    T findById(K id);
    void deleteById(K id);
    T save(T t);
}
