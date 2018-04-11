package org.sklse.targetedcourse.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LJ on 2017/7/19.
 */
@Entity
@Table(name = "Guardian")
public class Guardian {
    protected Guardian() {
        students = new ArrayList<Student>();
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "guardianUid", length = 36)
    private String guardianUid;
    private String phoneNumber;
    private String name;
    private String password;
    private Date registerTime;
    private String wechat;
    private String career;
    private String address;


    public String getCurrentChildUid() {
        return currentChildUid;
    }

    public void setCurrentChildUid(String currentChildUid) {
        this.currentChildUid = currentChildUid;
    }

    //    当前孩子
    private String currentChildUid;
    //    孩子们
    @OneToMany
    private List<Student> students;


    public String getGuardianUid() {
        return guardianUid;
    }

    public void setGuardianUid(String guardianUid) {
        guardianUid = guardianUid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
