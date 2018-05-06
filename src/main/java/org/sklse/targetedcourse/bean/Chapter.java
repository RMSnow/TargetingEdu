package org.sklse.targetedcourse.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Chapter")
public class Chapter {


    //章节ID
    @Id
    private String chapterUid;
    //章节名
    private String name;
    //章节内容？介绍？
    private String content;

    //练习题
//    @OneToMany
//    private List<Exercise> exerciseList;


    public String getChapterID() {
        return chapterUid;
    }

    public void setChapterID(String chapterID) {
        this.chapterUid = chapterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
