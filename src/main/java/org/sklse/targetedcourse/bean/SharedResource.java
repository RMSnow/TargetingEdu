package org.sklse.targetedcourse.bean;

import javax.persistence.Entity;

/**
 * Created by LJ on 2017/7/19.
 */
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
