package com.side.traffic;

import com.side.traffic.domain.user.dto.request.UserRegisterRequest;
import com.side.traffic.domain.user.entity.User;
import com.side.traffic.domain.user.repositorty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableCaching
@RequiredArgsConstructor
public class TrafficApplication  {
	private final UserRepository userRepository;


	public static void main(String[] args) {
		SpringApplication.run(TrafficApplication.class, args);

	}

//	@Override
//	public void run(ApplicationArguments args) {
//		makeTestUser();
//	}

	private void makeTestUser() {
		UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
		userRegisterRequest.setUsername("Tester");
		userRegisterRequest.setLoginId("test123");
		userRegisterRequest.setPassword("password123");
		userRepository.save(User.of(userRegisterRequest, "password123"));
	}


}
