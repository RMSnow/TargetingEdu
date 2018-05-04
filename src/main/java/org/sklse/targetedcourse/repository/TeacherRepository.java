package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    Teacher findByPhoneNumber(String phoneNumber);
}
