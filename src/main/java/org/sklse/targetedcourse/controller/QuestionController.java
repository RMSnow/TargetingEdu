package org.sklse.targetedcourse.controller;

import io.swagger.annotations.ApiOperation;
import org.sklse.targetedcourse.bean.Question;
import org.sklse.targetedcourse.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(value = "question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @ApiOperation(value = "获取全部试题")
    @GetMapping(value = "getAllQuestions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();

    }
    @ApiOperation(value = "根据题号数组查询对应的题目")
    @GetMapping(value = "findAllByNumberIn")
    public List<Question> findAllByNumberIn(String[] array) {

        if (array!=null){
            return questionRepository.findAllByNumberIn(array);
        }
        return null;

    }




}
