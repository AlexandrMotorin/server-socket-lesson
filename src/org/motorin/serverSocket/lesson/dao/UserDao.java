package org.motorin.serverSocket.lesson.dao;

import org.motorin.serverSocket.lesson.entity.User;

import java.util.List;

public class UserDao implements Dao<Integer, User>{



    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
