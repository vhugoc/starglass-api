package com.starglass.api.controllers.authentication;

import com.starglass.api.domain.user.User;
import com.starglass.api.domain.user.UserService;
import com.starglass.api.infra.rest.RestURL;
import com.starglass.api.infra.security.TokenService;
import com.starglass.api.infra.service.BaseServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestURL.AUTH)
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    UserService userService,
                                    TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping(RestURL.AUTH_LOGIN)
    public ResponseEntity<BaseServiceResponse> login(@RequestBody User.Builder userBuilder) {
        User user = userBuilder.build();
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        BaseServiceResponse<String> response = BaseServiceResponse.<String>builder()
                .withStatusCode(HttpStatus.OK)
                .withData(token).build();

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping(RestURL.AUTH_REGISTER)
    public ResponseEntity<BaseServiceResponse> register(@RequestBody User.Builder userBuilder) {
       BaseServiceResponse<User> register = this.userService.register(userBuilder);
        return ResponseEntity.status(register.getStatusCode()).body(register);
    }

}
