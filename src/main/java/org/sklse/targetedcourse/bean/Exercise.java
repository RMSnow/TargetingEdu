package org.sklse.targetedcourse.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Exercise")
public class Exercise {
    @Id
    private String exerciseUid;
    //试题列表
    @Column(name = "questionList")
    private String questionList;
    //试题总数
    @Column(name = "count")
    private String count;
    //章节数
    @Column(name="chapterId")
    private String chapterId;
    //课程名称
    @Column(name = "courseName")
    private String courseName;
    //作业名称
    @Column(name="homeworkName")
    private String homeworkName;
    private String pdfUrl;

    public String getExerciseUid() {
        return exerciseUid;
    }

    public void setExerciseUid(String exerciseUid) {
        this.exerciseUid = exerciseUid;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getQuestionList() {
        return questionList;
    }

    public void setQuestionList(String questionList) {
        this.questionList = questionList;
    }
}
