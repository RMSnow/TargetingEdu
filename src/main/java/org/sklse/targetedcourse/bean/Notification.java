package org.sklse.targetedcourse.bean;

import javax.persistence.Entity;


@Entity
public class Notification extends Feedback{
    private String content;//通知的内容

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
