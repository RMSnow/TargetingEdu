package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.SubmittedHomework;
import org.sklse.targetedcourse.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
