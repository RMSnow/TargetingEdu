package org.sklse.targetedcourse.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "stu_class")
public class StuClassStudent implements Serializable{

    private static final long serialVersionUID=1L;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Id

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "number", length = 36)
    private String number;
    public long getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(long classNumber) {
        this.classNumber = classNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Column(name = "class_number")
    private long classNumber;

    @Column(name = "student_id")
    private String studentId;

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StuClassStudent){
            StuClassStudent stuClassStudent=(StuClassStudent)obj;
            if(this.classNumber==stuClassStudent.getClassNumber()&&this.studentId.equals(stuClassStudent.getStudentId())){
                return true;
            }
        }
        return false;
    }
}
