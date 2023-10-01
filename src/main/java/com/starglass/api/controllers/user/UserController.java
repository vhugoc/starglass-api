package com.starglass.api.controllers.user;

import com.starglass.api.domain.user.User;
import com.starglass.api.domain.user.UserService;
import com.starglass.api.infra.rest.RestURL;
import com.starglass.api.infra.service.BaseServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestURL.USER)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(RestURL.USER_PROFILE)
    public ResponseEntity<BaseServiceResponse<User>> getProfile() {
        BaseServiceResponse<User> user = userService.getProfile();
        return ResponseEntity.status(user.getStatusCode()).body(user);
    }

}
