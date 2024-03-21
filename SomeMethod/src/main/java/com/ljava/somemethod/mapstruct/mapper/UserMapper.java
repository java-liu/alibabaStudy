package com.ljava.somemethod.mapstruct.mapper;

import com.ljava.somemethod.mapstruct.UserDTO;
import com.ljava.somemethod.mapstruct.UserEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Named("one")
    UserDTO toDTO(UserEntity entity);

    @Named("two")
    @Mapping(target = "config", source = "userConfig")
    UserDTO toD(UserEntity entity2);

    @Named("three")
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "config", source = "userConfig")
    @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    UserDTO toDT(UserEntity entity3);

    @IterableMapping(qualifiedByName = "one")
    List<UserDTO> toList(List<UserEntity> list);
}
