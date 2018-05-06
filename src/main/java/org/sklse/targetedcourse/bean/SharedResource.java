package org.sklse.targetedcourse.bean;

import javax.persistence.Entity;


@Entity
public class SharedResource extends Feedback {
    private String sharedResource;

    public String getSharedResource() {
        return sharedResource;
    }

    public void setSharedResource(String sharedResource) {
        this.sharedResource = sharedResource;
    }
}
