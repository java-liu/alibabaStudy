package com.ljava.vocabapp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@TableName("sys_user")
public class UserEntity implements UserDetails {

    private Long id;
    private String username;
    private String password;
    @TableField(exist = false)
    private String roles = "ADMIN,USER"; // 角色信息，可能是逗号分隔的字符串

    @TableField(exist = false)
    private boolean accountNonExpired = true;
    @TableField(exist = false)
    private boolean accountNonLocked = true;
    @TableField(exist = false)
    private boolean credentialsNonExpired = true;
    private boolean enabled; // 用户是否启用，1表示启用，0表示禁用

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles));
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
