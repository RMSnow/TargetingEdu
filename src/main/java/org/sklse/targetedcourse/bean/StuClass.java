package org.sklse.targetedcourse.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Alison on 2017/4/13.
 */
@Entity
@Table(name = "class")
public class StuClass {

    /**
     * Instantiates a new Stu class.
     */
    protected StuClass() {

    }


    public String getClassUid() {
        return classUid;
    }

    public void setClassUid(String classUid) {
        this.classUid = classUid;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "classUid", length = 36)
    private String classUid;

    @Column(name = "stu_count")
    private int stu_count;

    @Column(name = "title")
    private String title;

    @Column(name = "grade")
    private String grade;


    @Temporal(TemporalType.DATE)
    @Column(name = "start_time")
    private Date start_time;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_time")
    private Date end_time;

    @Column(name = "status")
    private int status;

    @Column(name = "comment")
    private String comment;

    @OneToMany
    private List<Student> students;




    @OneToMany
    private List<Assignment> assignmentList;


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Gets students.
     *
     * @return the students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Sets students.
     *
     * @param students the students
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public List<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    /**
     * Gets stu count.
     *
     * @return the stu count
     */
    public int getStu_count() {
        return stu_count;
    }

    /**
     * Sets stu count.
     *
     * @param stu_count the stu count
     */
    public void setStu_count(int stu_count) {
        this.stu_count = stu_count;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public Date getStart_time() {
        return start_time;
    }

    /**
     * Sets start time.
     *
     * @param start_time the start time
     */
    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public Date getEnd_time() {
        return end_time;
    }

    /**
     * Sets end time.
     *
     * @param end_time the end time
     */
    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }


}
