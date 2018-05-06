package org.sklse.targetedcourse.bean;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Feedback {

    @Id
    private String id;
    private String teacherId;
    private String studentId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

}
