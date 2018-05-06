package org.sklse.targetedcourse.bean;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Entity     //课堂反馈
public class LearningReport extends Feedback{
    private String courseId;
    @OneToMany
    private List<Matrics> matrics;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<Matrics> getMatrics() {
        return matrics;
    }

    public void setMatrics(List<Matrics> matrics) {
        this.matrics = matrics;
    }
}
