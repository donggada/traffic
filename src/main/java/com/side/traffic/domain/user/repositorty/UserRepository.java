package com.side.traffic.domain.user.repositorty;


import com.side.traffic.domain.user.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static com.side.traffic.global.config.redis.CacheConfig.TOKEN_CACHE;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginId(String loginId);

    @Cacheable(cacheNames = TOKEN_CACHE, key = "'user:' + #loginId")
    Optional<User> findTokenCheckByLoginId(String loginId);

}
