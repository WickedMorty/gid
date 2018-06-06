package rest.controller;

import rest.entity.User;
import rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/authorization/{mdsumm}")
    public Integer auth(@PathVariable final String mdsumm) {
        return userService.check(mdsumm);
    }

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable final Integer id) {
        return userService.findByid(id);
    }
}
