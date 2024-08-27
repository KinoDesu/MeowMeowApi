package dev.kinodesu.MeowMeowApi.mapper.impl;

import dev.kinodesu.MeowMeowApi.mapper.MeowUserMapper;
import dev.kinodesu.MeowMeowApi.model.user.DTOMeowUser;
import dev.kinodesu.MeowMeowApi.model.user.MeowUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MeowUserMapperImpl implements MeowUserMapper {

    @Override
    public MeowUser mapToEntity(DTOMeowUser userDto) {
        MeowUser meowUser = new MeowUser();

        meowUser.setId(userDto.id());
        meowUser.setName(userDto.name());
        meowUser.setEmail(userDto.email());
        meowUser.setDocumentNumber(userDto.documentNumber());
        meowUser.setPhoneNumber(userDto.phoneNumber());
        meowUser.setActive(userDto.active());

        return meowUser;
    }

    @Override
    public DTOMeowUser mapToDto(MeowUser userEntity) {

        return new DTOMeowUser(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getDocumentNumber(),
                userEntity.getPhoneNumber(),
                userEntity.getActive()
        );
    }

    @Override
    public List<MeowUser> mapAllToEntity(List<DTOMeowUser> userDtoList) {

        if (userDtoList == null) {
            return Collections.emptyList();
        }

        List<MeowUser> list = new ArrayList<>(userDtoList.size());
        userDtoList.forEach(meowUser -> list.add(mapToEntity(meowUser))
        );

        return list;
    }

    @Override
    public List<DTOMeowUser> mapAllToDto(List<MeowUser> userEntityList) {

        if (userEntityList == null) {
            return Collections.emptyList();
        }

        List<DTOMeowUser> list = new ArrayList<>(userEntityList.size());
        userEntityList.forEach(meowUser -> list.add(mapToDto(meowUser))
        );

        return list;
    }
}
