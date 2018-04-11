package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.StuClassStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by guolanqing on 2017/7/13.
 */
public interface StuClassStudentRepository extends JpaRepository<StuClassStudent, String> {
    @Query("select p.classNumber from StuClassStudent p where p.studentId=?1")
    List<Long> findStuClassByStudent(String studentId);



}
