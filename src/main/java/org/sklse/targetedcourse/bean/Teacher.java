package org.sklse.targetedcourse.bean;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {

    protected Teacher() {
        assignments = new ArrayList<>();
    }

    //班级id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //电话号码
    private String phoneNumber;

    //密码
    private String password;

    //姓名
    private String name;


    @OneToMany
    private List<Course> courses;//课程列表


    @OneToMany
    private List<Assignment> assignments;  //作业列表

    //班级列表
    @OneToMany
    private List<StuClass> stuClasses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }


    public List<StuClass> getStuClasses() {
        return stuClasses;
    }

    public void setStuClasses(List<StuClass> stuClasses) {
        this.stuClasses = stuClasses;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
