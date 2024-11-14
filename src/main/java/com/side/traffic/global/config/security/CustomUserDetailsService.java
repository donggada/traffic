package com.side.traffic.global.config.security;


import com.side.traffic.domain.user.repositorty.UserRepository;
import com.side.traffic.global.exception.ApiException;
import com.side.traffic.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    sa

    @Override
    public UserDetails loadUserByUsername(String loginId) {
        return new CustomUserDetails(
                userRepository.findTokenCheckByLoginId(loginId)
                        .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND))
        ); // UserDetails 구현체 반환
    }
}

