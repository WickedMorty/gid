package rest.service.impl;

import rest.entity.User;
import rest.repository.UserRepository;
import rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Integer check(String md5) {
        User user = userRepository.findByMd5(md5);
        if(user != null) {
            return user.getId();
        }else{
            return -1;
        }
    }

    @Override
    public List<User> findAll() {
        User user = new User();
        user.setName("1212");
        userRepository.save(user);
        return userRepository.findAll();
    }

    @Override
    public User findByid(Integer id) {
        User user = new User();
        user = userRepository.findOne(id);
        user.setPassword("");
        return user;
    }
}
