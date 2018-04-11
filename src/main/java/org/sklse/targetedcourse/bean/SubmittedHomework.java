package org.sklse.targetedcourse.bean;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "submitHomework")
public class SubmittedHomework {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uid", length = 36)
    private String uid;

    //初始化
    public SubmittedHomework() {
        submitCount = 0;
        answers = new ArrayList<Answer>();
        isChecked=false;
        isSubmitted=false;
    }


    private int score; //分数
    private String comment; //老师评语

    private boolean isSubmitted;//是否提交

    private boolean isChecked; //是否批改


    private int submitCount; //提交次数
    //提交学生的id
    private String studentUid;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    private String studentName;


    //    对于发布作业的id
    private String assignmentUid;
    //提交时间

    @Temporal(TemporalType.DATE)
    private Date submitTime;
    //作业图片
    private String pictureUrl;
    //音频信息
    private String audioUrl;
    //完成作业时长
    private int duration;
    //做作业专注度
    private int concentration;
    //非独立完成作业题号
    private String nonindependent;
    //题目总数
    private int questionCount;
    @OneToMany
    private List<Answer> answers;

   private String checkResult;

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(int submitCount) {
        this.submitCount = submitCount;
    }

    public String getStudentUid() {
        return studentUid;
    }

    public void setStudentUid(String studentUid) {
        this.studentUid = studentUid;
    }

    public String getAssignmentUid() {
        return assignmentUid;
    }

    public void setAssignmentUid(String assignmentUid) {
        this.assignmentUid = assignmentUid;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getConcentration() {
        return concentration;
    }

    public void setConcentration(int concentration) {
        this.concentration = concentration;
    }

    public String getNonindependent() {
        return nonindependent;
    }

    public void setNonindependent(String nonindependent) {
        this.nonindependent = nonindependent;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addSubmitCount(){
        this.submitCount++;
    }
}
