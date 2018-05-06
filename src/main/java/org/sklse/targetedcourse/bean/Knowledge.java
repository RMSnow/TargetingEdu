package org.sklse.targetedcourse.bean;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Knowledge")
public class Knowledge {
    @Id
    private String knowledgeUid;
    private String content;




    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
