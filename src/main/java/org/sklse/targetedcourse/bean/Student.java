package org.sklse.targetedcourse.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Student")
public class Student {

    protected Student() {
        submittedHomeworks = new ArrayList<SubmittedHomework>();
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "studentUid", length = 36)
    private String studentUid;

    private String name;
    private String sex;
    private String birthday;


    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    //学号or身份证号
    private String studentNumber;
    private String school;
    private String grade;//年级
    private String stuclass;//班级
    private String province;//省份
    private String city;//城市
    private String parentTel;
    private String parentWechat;

    @OneToMany
    private List<SubmittedHomework> submittedHomeworks;


    public String getStudentUid() {
        return studentUid;
    }

    public void setStudentUid(String studentUid) {
        this.studentUid = studentUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getParentTel() {
        return parentTel;
    }

    public void setParentTel(String parentTel) {
        this.parentTel = parentTel;
    }

    public String getParentWechat() {
        return parentWechat;
    }

    public void setParentWechat(String parentWechat) {
        this.parentWechat = parentWechat;
    }

    public List<SubmittedHomework> getSubmittedHomeworks() {
        return submittedHomeworks;
    }

    public void setSubmittedHomeworks(List<SubmittedHomework> submittedHomeworks) {
        this.submittedHomeworks = submittedHomeworks;
    }
}