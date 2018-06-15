package rest.service.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.entity.User;
import rest.entity.controller.UserAuth;
import rest.entity.controller.UserAuthResponse;
import rest.repository.DepartmentRepository;
import rest.service.UserService;
import rest.service.controller.UserAuthResponseService;

@Service
public class UserAuthResponseServiceImpl implements UserAuthResponseService {

    @Autowired
    UserService userService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public UserAuthResponse findByAuth(UserAuth userAuth) {

        UserAuthResponse userAuthResponse;

        User user = userService.findByLoginAndPassword(userAuth.getLogin(), userAuth.getPassword());

        if(user != null) {
            userAuthResponse = new UserAuthResponse();
            userAuthResponse.setName(user.getLastName() + " " + user.getName() + " " + user.getSecondName());
            userAuthResponse.setDepartment(departmentRepository.findById(user.getDepartment()).getName());
            userAuthResponse.setMobile(user.getMobile());
            userAuthResponse.setEmail(user.getLogin() + "@bgnsk.ru");
            return userAuthResponse;
        }

        return null;
    }
}
