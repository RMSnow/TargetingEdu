package org.sklse.targetedcourse.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String role;
}
