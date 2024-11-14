package com.side.traffic;

import com.side.traffic.domain.user.dto.request.UserRegisterRequest;
import com.side.traffic.domain.user.entity.User;
import com.side.traffic.domain.user.repositorty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
@EnableCaching
@RequiredArgsConstructor
public class TrafficApplication implements ApplicationRunner {

	private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;
	private final UserRepository userRepository;


	public static void main(String[] args) {
		SpringApplication.run(TrafficApplication.class, args);

	}

	@Override
	public void run(ApplicationArguments args) {
		UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
		userRegisterRequest.setUsername("Tester");
		userRegisterRequest.setLoginId("test123");
		userRegisterRequest.setPassword("password123");
		userRepository.save(User.of(userRegisterRequest, "password123"));

	}



}
