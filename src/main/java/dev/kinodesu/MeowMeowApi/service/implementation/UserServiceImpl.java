package dev.kinodesu.MeowMeowApi.service.implementation;

import dev.kinodesu.MeowMeowApi.mapper.MeowUserMapper;
import dev.kinodesu.MeowMeowApi.model.user.DTOMeowUser;
import dev.kinodesu.MeowMeowApi.model.user.MeowUser;
import dev.kinodesu.MeowMeowApi.repository.UserRepository;
import dev.kinodesu.MeowMeowApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MeowUserMapper mapper;

    @Override
    public DTOMeowUser getUserById(Long id) {
        return mapper.mapToDto(userRepository.getReferenceById(id));
    }

    @Override
    public List<DTOMeowUser> getUserList() {
        return mapper.mapAllToDto(userRepository.findAllByOrderByIdDesc());
    }

    @Override
    public Page<DTOMeowUser> getUserPage(Pageable pageable) {
        Page<MeowUser> entityPage = userRepository.findAll(pageable);

        return entityPage.map(mapper::mapToDto);
    }

    @Override
    public Page<DTOMeowUser> getFilteredUserPage(Pageable pageable, Map<String, String> filters) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFilteredUserPage'");
    }

    @Override
    public DTOMeowUser postUser(DTOMeowUser newMeowUser) {
        MeowUser meowUser = userRepository.save(mapper.mapToEntity(newMeowUser));

        return mapper.mapToDto(meowUser);
    }

    @Override
    public void putUserById(Long id, DTOMeowUser newMeowUser) {
        MeowUser meowUser = mapper.mapToEntity(newMeowUser);
        meowUser.setId(id);
        userRepository.save(meowUser);
    }

    @Override
    public void changeUserStatusById(Long id) {
        MeowUser meowUser = userRepository.getReferenceById(id);
        meowUser.setActive(!meowUser.getActive());
        userRepository.save(meowUser);
    }
}
