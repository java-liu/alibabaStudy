package com.ljava.vocabapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljava.vocabapp.entity.Word;


public interface WordMapper extends BaseMapper<Word> {
    // 这里可以定义一些自定义的查询方法
    //List<Word> findWordsByUserId(Long userId);
}

