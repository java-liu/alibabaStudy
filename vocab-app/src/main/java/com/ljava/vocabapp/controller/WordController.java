package com.ljava.vocabapp.controller;

import com.ljava.vocabapp.annotation.PermitAll;
import com.ljava.vocabapp.dto.WordDTO;
import com.ljava.vocabapp.entity.Word;
import com.ljava.vocabapp.service.WordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/words")
public class WordController {

    @Autowired
    private WordService wordService;

    @Operation(summary = "新增一个单词")
    @PostMapping("/addWord")
    @PermitAll
    public String addWord(@Parameter(description = "要添加的单词对象")@RequestBody WordDTO wordDTO){
        wordService.addWord(wordDTO); // 传递转换后的实体类
        return "success";
    }

    @Operation(summary = "获取所有单词", description = "返回用户收藏的所有单词列表")
    @ApiResponse(responseCode = "200", description = "成功获取单词列表",
            content = @Content(schema = @Schema(implementation = Word.class)))
    @GetMapping("/list")
    @PermitAll
    public List<Word> getAllWords(){
        return wordService.getAllWords();
    }
}
