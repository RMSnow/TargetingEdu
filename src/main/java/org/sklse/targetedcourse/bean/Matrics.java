package org.sklse.targetedcourse.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by MIC on 2017/8/11.
 */
@Entity
@Table(name = "matrics")
public class Matrics {
    @Id
    @Column(name = "id")
    private String id;
    private String name;//知识、态度、习惯、能力
    private int score;//得分
    private String tags;//标签
    private String remark;//评语

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
