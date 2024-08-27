package dev.kinodesu.MeowMeowApi.service;

import dev.kinodesu.MeowMeowApi.model.user.DTOMeowUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    DTOMeowUser getUserById(Long id);

    List<DTOMeowUser> getUserList();

    Page<DTOMeowUser> getUserPage(Pageable pageable);

    Page<DTOMeowUser> getFilteredUserPage(Pageable pageable, Map<String, String> filters);

    DTOMeowUser postUser(DTOMeowUser newMeowUser);

    void putUserById(Long id, DTOMeowUser newMeowUser);

    void changeUserStatusById(Long id);
}
