package com.ljava.somemethod.test;

import com.ljava.somemethod.mapstruct.UserDTO;
import com.ljava.somemethod.mapstruct.UserEntity;
import com.ljava.somemethod.mapstruct.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {

    @Test
    public void testToString() {

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("张三");
        user.setAge(18);
        user.setAddress("北京房山");
        user.setUserConfig("{\"isTrue\":\t true\"}");
        System.out.println(user.toString());

        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        UserDTO userDTO = userMapper.toDTO(user);
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getAge(), userDTO.getAge());
        assertEquals(user.getAddress(), userDTO.getAddress());
        System.out.println(userDTO.toString());
    }

    @Test
    public void testToString2() {

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("张三");
        user.setAge(18);
        user.setAddress("北京房山");
        user.setUserConfig("{\"isTrue\":\t true\"}");
        System.out.println(user.toString());

        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        UserDTO userDTO = userMapper.toD(user);
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getAge(), userDTO.getAge());
        assertEquals(user.getAddress(), userDTO.getAddress());
        System.out.println(userDTO.toString());
    }
    @Test
    public void testToString3() {

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("张三");
        user.setAge(18);
        user.setAddress("北京房山");
        user.setUserConfig("{\"isTrue\":\t true\"}");
        user.setCreateTime(LocalDateTime.now());
        System.out.println(user.toString());

        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        UserDTO userDTO = userMapper.toDT(user);
        System.out.println(userDTO.toString());
    }

    @Test
    public void testToString4() {

        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("张三");
        user.setAge(18);
        user.setAddress("北京房山");
        user.setUserConfig("{\"isTrue\":\t true\"}");
        System.out.println(user.toString());
        List<UserEntity> list = new ArrayList<>();
        list.add(user);

        UserMapper userMapper = Mappers.getMapper(UserMapper.class);
        List<UserDTO> userDTO = userMapper.toList(list);
        System.out.println(userDTO.toString());
    }
}
