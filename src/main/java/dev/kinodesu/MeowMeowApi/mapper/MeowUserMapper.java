package dev.kinodesu.MeowMeowApi.mapper;

import dev.kinodesu.MeowMeowApi.model.user.DTOMeowUser;
import dev.kinodesu.MeowMeowApi.model.user.MeowUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MeowUserMapper {
    MeowUser mapToEntity(DTOMeowUser userDto);

    DTOMeowUser mapToDto(MeowUser userEntity);

    List<MeowUser> mapAllToEntity(List<DTOMeowUser> userDto);

    List<DTOMeowUser> mapAllToDto(List<MeowUser> userEntity);
}
