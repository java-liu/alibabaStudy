package com.ljava.vocabapp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class UserEntity {

    private Long id;
    private String username;
    private String password;
    private String roles; // 角色信息，可能是逗号分隔的字符串

    private Integer enabled; // 用户是否启用，1表示启用，0表示禁用
}
