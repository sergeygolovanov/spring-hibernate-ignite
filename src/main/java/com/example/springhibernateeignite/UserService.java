package com.example.springhibernateeignite;

import java.util.UUID;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(initialDelay = 3_000, fixedDelay = 10_000)
    public void insert() {
        final User user = new User();
        user.setLoginName(UUID.randomUUID().toString());
        user.setPassword(UUID.randomUUID().toString());
        User newUser = userRepository.save(user);
        log.info("add new with id={}", newUser.getId());
    }

    @Scheduled(initialDelay = 5_000, fixedDelay = 10_000)
    public void show() {
        log.info("> {}", userRepository.findAll().stream()
                .map(u -> u.getId().toString())
                .collect(Collectors.joining(","))
        );
    }

}
