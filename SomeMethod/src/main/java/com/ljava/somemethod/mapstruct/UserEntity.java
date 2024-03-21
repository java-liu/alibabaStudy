package com.ljava.somemethod.mapstruct;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserEntity {
    private Long id;
    private String name;
    private Integer age;
    private String address;

    private String userConfig;

    private LocalDate birthday;
    private LocalDateTime createTime;
}
