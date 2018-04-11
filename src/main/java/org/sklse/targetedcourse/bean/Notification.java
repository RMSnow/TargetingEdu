package org.sklse.targetedcourse.bean;

import javax.persistence.Entity;

/**
 * Created by LJ on 2017/7/19.
 */
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
