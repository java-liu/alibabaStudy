package com.ljava.vocabapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljava.vocabapp.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    UserEntity findByUsername(String username);
}
