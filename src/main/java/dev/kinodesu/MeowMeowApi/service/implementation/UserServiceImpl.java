package dev.kinodesu.MeowMeowApi.service.implementation;

import dev.kinodesu.MeowMeowApi.repository.UserRepository;
import dev.kinodesu.MeowMeowApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
}
