package com.ljava.vocabapp.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljava.vocabapp.dto.WordDTO;
import com.ljava.vocabapp.entity.Word;
import com.ljava.vocabapp.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WordService extends ServiceImpl<WordMapper, Word> {

    @Autowired
    private WordMapper wordMapper;
    public boolean addWord(WordDTO wordDTO) {
        // 将 WordDTO 转换为 Word 实体类
        Word word = new Word();
        word.setChinese(wordDTO.getChinese());
        word.setEnglish(wordDTO.getEnglish());
        word.setExample(wordDTO.getExample());
        word.setAddedTime(LocalDateTime.now());
        word.setUserId(wordDTO.getUserId());
        return wordMapper.insert(word) == 1;
    }
}
