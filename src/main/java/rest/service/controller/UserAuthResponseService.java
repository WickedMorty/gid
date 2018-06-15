package rest.service.controller;

import rest.entity.controller.UserAuth;
import rest.entity.controller.UserAuthResponse;

public interface UserAuthResponseService {
    UserAuthResponse findByAuth(UserAuth userAuth);
}
