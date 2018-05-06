package org.sklse.targetedcourse.bean;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Entity   //考试反馈
public class EaxmFeedback extends Feedback {

    private String eaxmId;
    @OneToMany
    private List<Matrics> matrics;

    public String getEaxmId() {
        return eaxmId;
    }

    public void setEaxmId(String eaxmId) {
        this.eaxmId = eaxmId;
    }

    public List<Matrics> getMatrics() {
        return matrics;
    }


    public void setMatrics(List<Matrics> matrics) {
        this.matrics = matrics;
    }
}
