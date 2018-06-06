package rest.service;

import rest.entity.User;

import java.util.List;

public interface UserService {

    Integer check(String md5);

    List<User> findAll();

    User findByid(Integer id);
}
