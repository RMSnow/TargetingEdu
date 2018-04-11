package org.sklse.targetedcourse.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Question")
public class Question {
    @Id
    private String number;

    @Column(columnDefinition = "TEXT")
    private String title;



    //    题类：高考真题 常考题 模拟题
    private String category;

    //    题型：单选题 填空题
    private String type;

    //    教程版本
    private String editionID;

    //    章节
    private String chapterID;

    //    答案
    private String answer;

    //    知识点
    private String knowledgeList;

    //    试题引用次数
    private int referCount;

    //    学生提交作业次数
    private int validSubmitCount;

    //    错误次数
    private int errorCount;

    //难度
    private String difficulty;
    //子试题
    @OneToMany
    private List<Question> childQuestionList;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEditionID() {
        return editionID;
    }

    public void setEditionID(String editionID) {
        this.editionID = editionID;
    }

    public String getChapterID() {
        return chapterID;
    }

    public void setChapterID(String chapterID) {
        this.chapterID = chapterID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getKnowledgeList() {
        return knowledgeList;
    }

    public void setKnowledgeList(String knowledgeList) {
        this.knowledgeList = knowledgeList;
    }

    public int getReferCount() {
        return referCount;
    }

    public void setReferCount(int referCount) {
        this.referCount = referCount;
    }

    public int getValidSubmitCount() {
        return validSubmitCount;
    }

    public void setValidSubmitCount(int validSubmitCount) {
        this.validSubmitCount = validSubmitCount;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<Question> getChildQuestionList() {
        return childQuestionList;
    }

    public void setChildQuestionList(List<Question> childQuestionList) {
        this.childQuestionList = childQuestionList;
    }
}
