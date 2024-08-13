package dev.kinodesu.MeowMeowApi.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.kinodesu.MeowMeowApi.model.user.MeowUser;

@Service
public interface UserService {

    MeowUser getUserById(Long id);

    List<MeowUser> getUserList();

    Page<MeowUser> getUserPage(Pageable pageable);

    Page<MeowUser> getFilteredUserPage(Pageable pageable, Map<String, String> filters);

}
