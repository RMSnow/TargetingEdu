package org.sklse.targetedcourse.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by MIC on 2017/8/12.
 */
@Entity
@Table(name="clollectiongroup")
public class CollectionGroup {
    @Id
    private String id;
    private String name;
    private String studentId;
    @OneToMany
    private List<CollectedQuestion> collections;

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

    public List<CollectedQuestion> getCollections() {
        return collections;
    }

    public void setCollections(List<CollectedQuestion> collections) {
        this.collections = collections;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}

