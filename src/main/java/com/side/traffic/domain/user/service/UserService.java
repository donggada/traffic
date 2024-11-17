package com.side.traffic.domain.user.service;


import com.side.traffic.domain.user.dto.request.LoginRequest;
import com.side.traffic.domain.user.dto.request.UserRegisterRequest;
import com.side.traffic.domain.user.dto.response.LoginResponse;
import com.side.traffic.domain.user.dto.response.RegisterResponse;
import com.side.traffic.domain.user.dto.response.UserResponse;
import com.side.traffic.domain.user.entity.User;
import com.side.traffic.domain.user.repositorty.UserRepository;
import com.side.traffic.global.config.JwtTokenProvider;
import com.side.traffic.global.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.side.traffic.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = null;
    private final JwtTokenProvider jwtTokenProvider;

    public RegisterResponse register(UserRegisterRequest userRegisterRequest) {
        if (userRepository.findByLoginId(userRegisterRequest.getLoginId()).isPresent()) {
            throw DUPLICATE_LOGIN_ID.build();
        }

        String encode = passwordEncoder.encode(userRegisterRequest.getPassword());

        return RegisterResponse.of(userRepository.save(User.of(userRegisterRequest, encode)).getLoginId());
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByLoginId(loginRequest.getLoginId()).orElseThrow(() -> INVALID_LOGIN_ID.build());

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw INVALID_PASSWORD.build();
        }

        return LoginResponse.of(user.getLoginId(), jwtTokenProvider.generateToken(user.getLoginId()));
    }

    @Transactional(readOnly = true)
    public UserResponse findUser(User user) {
        return UserResponse.of(
                userRepository.findByLoginId(user.getLoginId())
                        .orElseThrow(() -> INVALID_PASSWORD.build())
        );
    }

}
