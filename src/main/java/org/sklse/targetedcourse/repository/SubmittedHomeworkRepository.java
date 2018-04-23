package org.sklse.targetedcourse.repository;

import org.sklse.targetedcourse.bean.SubmittedHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubmittedHomeworkRepository extends JpaRepository<SubmittedHomework, String> {


    SubmittedHomework findByUid(String uid);

    List<SubmittedHomework> findAllByStudentUid(String StudentUid);

    SubmittedHomework findByAssignmentUid(String assignmentUid);

    List<SubmittedHomework> findAllByAssignmentUid(String assignmentUid);

    @Modifying
    @Query(value = "update SubmittedHomework sh set isChecked = false where assignmentUid=?1 ")
    void updateByAssignmentUid(String assignmentUid);

}
