package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.StuClassStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StuClassStudentRepository extends JpaRepository<StuClassStudent, String> {
    @Query("select p.classNumber from StuClassStudent p where p.studentId=?1")
    List<Long> findStuClassByStudent(String studentId);



}
