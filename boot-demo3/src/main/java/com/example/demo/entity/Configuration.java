package com.example.demo.entity;

import lombok.Data;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.example.demo.entity
 * @date 2019/10/9 17:58
 */
@Data
public class Configuration {
    private String id;
    private String configCode;
    private String configValue;
    private String miaoshu;

    public Configuration() {
    }
    public Configuration(String id, String configCode, String configValue, String miaoshu) {
        this.id = id;
        this.configCode = configCode;
        this.configValue = configValue;
        this.miaoshu = miaoshu;
    }
}
