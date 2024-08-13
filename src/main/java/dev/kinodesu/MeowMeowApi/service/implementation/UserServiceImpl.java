package dev.kinodesu.MeowMeowApi.service.implementation;

import dev.kinodesu.MeowMeowApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
}
