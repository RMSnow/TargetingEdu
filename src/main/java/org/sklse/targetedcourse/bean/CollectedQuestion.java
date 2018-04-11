package org.sklse.targetedcourse.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by MIC on 2017/8/11.
 */
@Entity
@Table(name = "collectedquestion")
public class CollectedQuestion {
    @Id

    private String id;
    //属于哪一个收藏夹
    private String groupId;
    private String studentId;
    //试题
    private  String questionId;
    //答案
    private String answer;
    private String pictures;
    private String audios;
    //该题评价
    private String remark;
    //错题来源
    private String source;
    //错误原因
    private String cause;
    //掌握程度
    private int level;
    //复习次数
    private int reviewcount;
    //上一次复习时间
    private Date lastreviewtime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getAudios() {
        return audios;
    }

    public void setAudios(String audios) {
        this.audios = audios;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getReviewcount() {
        return reviewcount;
    }

    public void setReviewcount(int reviewcount) {
        this.reviewcount = reviewcount;
    }

    public Date getLastreviewtime() {
        return lastreviewtime;
    }

    public void setLastreviewtime(Date lastreviewtime) {
        this.lastreviewtime = lastreviewtime;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}

