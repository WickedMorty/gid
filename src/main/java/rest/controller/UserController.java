package rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.entity.User;
import rest.entity.controller.UserAuth;
import rest.entity.controller.UserAuthResponse;
import rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import rest.service.controller.UserAuthResponseService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserAuthResponseService userAuthResponseService;

    //Устаревшая
    @GetMapping("/authorization/{mdsumm}")
    public Integer auth(@PathVariable final String mdsumm) {
        return userService.check(mdsumm);
    }

    //Устаревшая
    @GetMapping("/all")
    public List<User> getAllUser() {
        return userService.findAll();
    }

    //Устаревшая
    @GetMapping("/{id}")
    public User getById(@PathVariable final Integer id) {
        return userService.findByid(id);
    }

    //Используют: ГидПоНовостройкам
    @PostMapping("login")
    public ResponseEntity<UserAuthResponse> login(@RequestBody UserAuth userAuth) {
        if(userAuth == null) {
            return new ResponseEntity<>(new UserAuthResponse(), HttpStatus.UNAUTHORIZED);
        }

        UserAuthResponse userAuthResponse = userAuthResponseService.findByAuth(userAuth);

        if(userAuthResponse == null) {
            return new ResponseEntity<>(userAuthResponse, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(userAuthResponse, HttpStatus.OK);
    }
}
