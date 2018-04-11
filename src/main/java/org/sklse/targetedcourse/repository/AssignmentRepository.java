package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, String> {


    public Assignment findByTitle(String title);


    public List<Assignment> findAllByTitle(String title);


    public Assignment findByAssignmentUid(String uid);


//    public List<Assignment> findAllByClassID(String class_id);

//    @Query("select p from Assignment p,StuClassStudent s where s.classNumber=p.classID and s.studentId=?1")
//    List<Assignment> findByStudentId(String studentId);


}
