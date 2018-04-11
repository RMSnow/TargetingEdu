package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.StuClass;
import org.sklse.targetedcourse.bean.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Alison on 2017/4/15.
 */

public interface TeacherRepository extends JpaRepository<Teacher,String> {

    Teacher findByPhoneNumber(String phoneNumber);


}
