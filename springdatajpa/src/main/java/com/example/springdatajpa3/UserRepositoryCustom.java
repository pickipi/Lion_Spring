package com.example.springdatajpa3;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findUsersByName(String name);
    List<User> findUsersDynamically(String name, String email);
}
