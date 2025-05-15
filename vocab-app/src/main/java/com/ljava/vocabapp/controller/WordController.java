package com.ljava.vocabapp.controller;

import com.ljava.vocabapp.dto.WordDTO;
import com.ljava.vocabapp.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/words")
public class WordController {

    @Autowired
    private WordService wordService;

    @PostMapping("/addWord")
    public String addWord(@RequestBody WordDTO wordDTO){
        wordService.addWord(wordDTO); // 传递转换后的实体类
        return "success";
    }
}
