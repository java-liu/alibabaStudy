package com.sanshao.offer;

import lombok.Getter;

/**
 * @Description: java 枚举类,六个国家
 * @Author: Liuys
 * @CreateDate: 2021/3/31 16:39
 * @Version: 1.0
 */
public enum CountryEnum {
    ONE(1,"齐",100),TWO(2,"楚",101),THREE(3,"燕",102),FOUR(4,"赵",103),FIVE(5,"魏",104),SIX(6,"韩",105);

    @Getter private Integer code;
    @Getter private String country;
    @Getter private Integer age;
    CountryEnum(Integer code, String country, Integer age) {
        this.code = code;
        this.country = country;
        this.age = age;
    }
    public static CountryEnum forEach_countryEnum(int index){
        CountryEnum[] array = CountryEnum.values();
        for (CountryEnum countryEnum : array)
        {
            if(index == countryEnum.getCode())
            {
                return countryEnum;
            }
        }
        return null;
    }
}
