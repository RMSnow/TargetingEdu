package org.sklse.targetedcourse.bean;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "Answer")
public class Answer {
    //    题目序号
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "AnswerUid", length = 36)
    private String AnswerUid;

    public String getAnswerUid() {
        return AnswerUid;
    }

    public void setAnswerUid(String uid) {
        this.AnswerUid = uid;
    }

    //    在试题中的题号
    private int ordinal;
    //    题库id
    private String question_id;
    //    学生答案
    private String answer;
    //    得分
    private String score;
    //    单题评价
    private String remark;

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
