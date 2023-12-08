package com.starglass.api.domain.user;

import com.starglass.api.infra.exception.custom.RestException;
import com.starglass.api.infra.security.TokenService;
import com.starglass.api.infra.service.BaseServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final TokenService tokenService;

    @Autowired
    public UserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public BaseServiceResponse<User> getProfile() {
        User authenticatedUser = this.tokenService.getAuthenticatedUserFromContext();
        Optional<User> user =  this.userRepository.findById(authenticatedUser.getId());

        if (user.isEmpty()) throw new RestException("Unauthorized", HttpStatus.UNAUTHORIZED);

        return BaseServiceResponse.<User>builder().withStatusCode(HttpStatus.OK).withData(user.get()).build();
    }

    public BaseServiceResponse<User> register(User.Builder userBuilder) {
        if (this.userRepository.findByEmail(userBuilder.getEmail()) != null)
            return BaseServiceResponse.<User>builder().withStatusCode(HttpStatus.BAD_REQUEST).withMessage("This email already exists").build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userBuilder.getPassword());
        User.Builder newUser = userBuilder.withPassword(encryptedPassword).withIsActive(true);

        User saved = this.userRepository.save(newUser.build());

        return BaseServiceResponse.<User>builder()
                .withStatusCode(HttpStatus.CREATED)
                .withData(saved).build();
    }

}
