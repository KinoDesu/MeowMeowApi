package dev.kinodesu.MeowMeowApi.service.implementation;

import dev.kinodesu.MeowMeowApi.model.user.MeowUser;
import dev.kinodesu.MeowMeowApi.repository.UserRepository;
import dev.kinodesu.MeowMeowApi.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public MeowUser getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public List<MeowUser> getUserList() {
        return userRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Page<MeowUser> getUserPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<MeowUser> getFilteredUserPage(Pageable pageable, Map<String, String> filters) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFilteredUserPage'");
    }
}
