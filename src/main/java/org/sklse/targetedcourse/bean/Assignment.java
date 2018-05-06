package org.sklse.targetedcourse.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Assignment")
public class Assignment {

    Assignment() {
        committedNumber = 0;
        createTime = new Date();
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "assignmentUid", length = 36)
    private String assignmentUid;

    private long teacherUid; //老师

    private String title; //标题

    private int committedNumber;// 已提交人数

    private int studentCount; //学生人数

    @Temporal(TemporalType.DATE)
    private Date createTime;

    @Temporal(TemporalType.DATE)
    private Date deadline;

    private String classList;    //    班级列表

    private String studentList; //学生列表

    private String chapter; //章节

    private String subject; //课程

    private String type; //类型

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addSubmitCount() {
        committedNumber++;
    }

    //试题列表
    @Column(name = "questionList")
    private String questionList;

    public String getQuestionList() {
        return questionList;
    }

    public void setQuestionList(String questionList) {
        this.questionList = questionList;
    }

    @OneToMany
    private List<Exercise> exerciseList; //  包含的练习题

    @OneToMany
    private List<SharedResource> sharedResourceList; //资源列表

    public String getAssignmentUid() {
        return assignmentUid;
    }

    public void setAssignmentUid(String assignmentUid) {
        this.assignmentUid = assignmentUid;
    }

    public long getTeacherUid() {
        return teacherUid;
    }

    public void setTeacherUid(long teacherUid) {
        this.teacherUid = teacherUid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCommittedNumber() {
        return committedNumber;
    }

    public void setCommittedNumber(int committedNumber) {
        this.committedNumber = committedNumber;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getClassList() {
        return classList;
    }

    public void setClassList(String classList) {
        this.classList = classList;
    }

    public String getStudentList() {
        return studentList;
    }

    public void setStudentList(String studentList) {
        this.studentList = studentList;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public List<SharedResource> getSharedResourceList() {
        return sharedResourceList;
    }

    public void setSharedResourceList(List<SharedResource> sharedResourceList) {
        this.sharedResourceList = sharedResourceList;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
